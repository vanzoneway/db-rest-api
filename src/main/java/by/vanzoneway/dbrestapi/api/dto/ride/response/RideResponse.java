package by.vanzoneway.dbrestapi.api.dto.ride.response;

import java.time.LocalDateTime;

public record RideResponse(
        Long id,
        Long passengerId,
        String passengerName,
        Long driverId,
        String driverName,
        String departureAddress,
        String destinationAddress,
        Double cost,
        LocalDateTime creationDate
) {
}