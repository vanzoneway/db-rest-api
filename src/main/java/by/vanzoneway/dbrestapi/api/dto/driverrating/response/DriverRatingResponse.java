package by.vanzoneway.dbrestapi.api.dto.driverrating.response;

import java.time.LocalDateTime;

public record DriverRatingResponse(
        Long id,
        String comment,
        Integer rating,
        LocalDateTime creationDate,
        String moderationStatus,
        Long driverId,
        String driverName
) {
}