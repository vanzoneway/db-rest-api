package by.vanzoneway.dbrestapi.api.dto.ride.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record RideCreateRequest(
        @NotNull Long passengerId,
        @NotNull Long driverId,
        @NotBlank String departureAddress,
        @NotBlank String destinationAddress,
        @Positive Double cost,
        LocalDateTime creationDate
) {
}
