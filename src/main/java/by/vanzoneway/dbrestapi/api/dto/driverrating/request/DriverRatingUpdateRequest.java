package by.vanzoneway.dbrestapi.api.dto.driverrating.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record DriverRatingUpdateRequest(
        Long id,
        String comment,
        @Min(1) @Max(5) Integer rating,
        String moderationStatus,
        Long driverId
) {
}