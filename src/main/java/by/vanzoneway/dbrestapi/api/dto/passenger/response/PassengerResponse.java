package by.vanzoneway.dbrestapi.api.dto.passenger.response;

public record PassengerResponse(
        Long id,

        String fullName,

        String email,

        String phoneNumber,

        Integer averageRating
) {
}