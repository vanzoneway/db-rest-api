package by.vanzoneway.dbrestapi.api.dto.driverrating.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DriverRatingCreateRequest(
        @NotBlank String comment,
        @Min(1) @Max(5) Integer rating,
        @NotNull Long driverId
) {
}