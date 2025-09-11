package by.vanzoneway.dbrestapi.api.dto.passengercredentials.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PassengerCredentialsCreateRequest(
        @NotBlank String cardNumber,
        @NotBlank String holderName,
        @NotNull LocalDate expirationDate,
        @NotBlank String iban,
        @NotNull Long passengerId
) {
}