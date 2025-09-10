package by.vanzoneway.dbrestapi.api.dto.passengerrating.response;

import java.time.LocalDateTime;

public record PassengerRatingResponse(
        Long id,
        String comment,
        Integer rating,
        LocalDateTime creationDate,
        String moderationStatus,
        Long passengerId,
        String passengerName
) {
}