package by.vanzoneway.dbrestapi.api.dto.passengercredentials.response;

import java.time.LocalDate;

public record PassengerCredentialsResponse(
        Long id,
        String cardNumber,
        String holderName,
        LocalDate expirationDate,
        String iban,
        Long passengerId,
        String passengerName
) {
}