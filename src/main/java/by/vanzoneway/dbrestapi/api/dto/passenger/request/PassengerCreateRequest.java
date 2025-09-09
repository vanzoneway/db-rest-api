package by.vanzoneway.dbrestapi.api.dto.passenger.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PassengerCreateRequest(
        @NotBlank String fullName,

        @Email String email,

        String phoneNumber,

        @Min(1) @Max(5) Integer averageRating
) {}
