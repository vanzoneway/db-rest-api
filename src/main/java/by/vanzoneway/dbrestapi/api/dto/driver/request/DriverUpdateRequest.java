package by.vanzoneway.dbrestapi.api.dto.driver.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record DriverUpdateRequest(
        Long id,

        String fullName,

        String email,

        String phoneNumber,

        @Min(1) @Max(5) Integer averageRating,

        String driverLicenseCategory
) {
}
