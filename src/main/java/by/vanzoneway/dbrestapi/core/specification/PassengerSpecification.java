package by.vanzoneway.dbrestapi.core.specification;

import by.vanzoneway.dbrestapi.api.dto.passenger.filter.PassengerFilter;
import by.vanzoneway.dbrestapi.core.model.Passenger;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PassengerSpecification {

    public static Specification<Passenger> withFilter(PassengerFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Фильтр по ID (точное совпадение)
            if (filter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
            }

            // Фильтр по ID (LIKE запрос - преобразуем ID в строку для поиска)
            if (filter.getId() != null && !filter.getId().isEmpty()) {
                // Преобразуем числовой ID в строку для LIKE поиска
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.toString(root.get("id")), // Конвертируем ID в строку
                        "%" + filter.getId() + "%"
                ));
            }

            // Фильтр по имени (LIKE запрос, нечувствительный к регистру)
            if (filter.getFullName() != null && !filter.getFullName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("fullName")),
                        "%" + filter.getFullName().toLowerCase() + "%"
                ));
            }

            // Фильтр по email (LIKE запрос, нечувствительный к регистру)
            if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")),
                        "%" + filter.getEmail().toLowerCase() + "%"
                ));
            }

            // Фильтр по номеру телефона (LIKE запрос)
            if (filter.getPhoneNumber() != null && !filter.getPhoneNumber().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        root.get("phoneNumber"),
                        "%" + filter.getPhoneNumber() + "%"
                ));
            }

            // Фильтр по минимальному рейтингу
            if (filter.getMinRating() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("averageRating"), filter.getMinRating()
                ));
            }

            // Фильтр по максимальному рейтингу
            if (filter.getMaxRating() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("averageRating"), filter.getMaxRating()
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}