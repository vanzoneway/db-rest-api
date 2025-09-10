package by.vanzoneway.dbrestapi.api.dto.driver.response;

public record DriverResponse(

        Long id,

        String fullName,

        String email,

        String phoneNumber,

        Integer averageRating,

        String driverLicenseCategory
) {
}
