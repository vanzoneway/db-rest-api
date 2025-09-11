package by.vanzoneway.dbrestapi.api.dto.passengercredentials.request;

import java.time.LocalDate;

public record PassengerCredentialsUpdateRequest(
        Long id,
        String cardNumber,
        String holderName,
        LocalDate expirationDate,
        String iban,
        Long passengerId
) {
}