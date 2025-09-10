package by.vanzoneway.dbrestapi.api.dto.ride.request;

import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record RideUpdateRequest(
        Long id,
        Long passengerId,
        Long driverId,
        String departureAddress,
        String destinationAddress,
        @Positive Double cost,
        LocalDateTime creationDate
) {
}
