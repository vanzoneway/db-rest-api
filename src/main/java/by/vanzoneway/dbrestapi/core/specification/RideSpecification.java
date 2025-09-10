package by.vanzoneway.dbrestapi.core.specification;

import by.vanzoneway.dbrestapi.api.dto.ride.filter.RideFilter;
import by.vanzoneway.dbrestapi.core.model.Ride;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RideSpecification {

    public static Specification<Ride> withFilter(RideFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Фильтр по ID (LIKE запрос - преобразуем ID в строку для поиска)
            if (filter.getId() != null && !filter.getId().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.toString(root.get("id")),
                        "%" + filter.getId() + "%"
                ));
            }

            // Фильтр по ID пассажира
            if (filter.getPassengerId() != null && !filter.getPassengerId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("passenger").get("id"), Long.parseLong(filter.getPassengerId())
                ));
            }

            // Фильтр по ID водителя
            if (filter.getDriverId() != null && !filter.getDriverId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("driver").get("id"), Long.parseLong(filter.getDriverId())
                ));
            }

            // Фильтр по адресу отправления (LIKE запрос, нечувствительный к регистру)
            if (filter.getDepartureAddress() != null && !filter.getDepartureAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("departureAddress")),
                        "%" + filter.getDepartureAddress().toLowerCase() + "%"
                ));
            }

            // Фильтр по адресу назначения (LIKE запрос, нечувствительный к регистру)
            if (filter.getDestinationAddress() != null && !filter.getDestinationAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("destinationAddress")),
                        "%" + filter.getDestinationAddress().toLowerCase() + "%"
                ));
            }

            // Фильтр по минимальной стоимости
            if (filter.getMinCost() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("cost"), filter.getMinCost()
                ));
            }

            // Фильтр по максимальной стоимости
            if (filter.getMaxCost() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("cost"), filter.getMaxCost()
                ));
            }

            // Фильтр по дате создания (начальная дата)
            if (filter.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("creationDate"), filter.getStartDate()
                ));
            }

            // Фильтр по дате создания (конечная дата)
            if (filter.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("creationDate"), filter.getEndDate()
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}