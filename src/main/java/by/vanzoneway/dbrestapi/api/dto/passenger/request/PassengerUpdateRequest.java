package by.vanzoneway.dbrestapi.api.dto.passenger.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PassengerUpdateRequest(
        Long id,

        String fullName,

        String email,

        String phoneNumber,

        @Min(1) @Max(5) Integer averageRating
) {}