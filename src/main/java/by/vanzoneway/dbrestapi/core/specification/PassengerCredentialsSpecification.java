package by.vanzoneway.dbrestapi.core.specification;

import by.vanzoneway.dbrestapi.api.dto.passengercredentials.filter.PassengerCredentialsFilter;
import by.vanzoneway.dbrestapi.core.model.PassengerCredentials;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PassengerCredentialsSpecification {

    public static Specification<PassengerCredentials> withFilter(PassengerCredentialsFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Фильтр по ID (LIKE запрос - преобразуем ID в строку для поиска)
            if (filter.getId() != null && !filter.getId().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.toString(root.get("id")),
                        "%" + filter.getId() + "%"
                ));
            }

            // Фильтр по номеру карты (LIKE запрос)
            if (filter.getCardNumber() != null && !filter.getCardNumber().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        root.get("cardNumber"),
                        "%" + filter.getCardNumber() + "%"
                ));
            }

            // Фильтр по имени держателя (LIKE запрос, нечувствительный к регистру)
            if (filter.getHolderName() != null && !filter.getHolderName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("holderName")),
                        "%" + filter.getHolderName().toLowerCase() + "%"
                ));
            }

            // Фильтр по IBAN (LIKE запрос)
            if (filter.getIban() != null && !filter.getIban().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        root.get("iban"),
                        "%" + filter.getIban() + "%"
                ));
            }

            // Фильтр по ID пассажира
            if (filter.getPassengerId() != null && !filter.getPassengerId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("passenger").get("id"), Long.parseLong(filter.getPassengerId())
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}