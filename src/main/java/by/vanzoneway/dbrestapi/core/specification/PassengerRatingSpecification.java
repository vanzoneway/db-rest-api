package by.vanzoneway.dbrestapi.core.specification;

import by.vanzoneway.dbrestapi.api.dto.passengerrating.filter.PassengerRatingFilter;
import by.vanzoneway.dbrestapi.core.model.PassengerRating;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PassengerRatingSpecification {

    public static Specification<PassengerRating> withFilter(PassengerRatingFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Фильтр по ID (LIKE запрос - преобразуем ID в строку для поиска)
            if (filter.getId() != null && !filter.getId().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.toString(root.get("id")),
                        "%" + filter.getId() + "%"
                ));
            }

            // Фильтр по комментарию (LIKE запрос, нечувствительный к регистру)
            if (filter.getComment() != null && !filter.getComment().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("comment")),
                        "%" + filter.getComment().toLowerCase() + "%"
                ));
            }

            // Фильтр по минимальному рейтингу
            if (filter.getMinRating() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("rating"), filter.getMinRating()
                ));
            }

            // Фильтр по максимальному рейтингу
            if (filter.getMaxRating() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("rating"), filter.getMaxRating()
                ));
            }

            // Фильтр по статусу модерации
            if (filter.getModerationStatus() != null && !filter.getModerationStatus().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("moderationStatus"), filter.getModerationStatus()
                ));
            }

            // Фильтр по ID пассажира
            if (filter.getPassengerId() != null && !filter.getPassengerId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("passenger").get("id"), Long.parseLong(filter.getPassengerId())
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
