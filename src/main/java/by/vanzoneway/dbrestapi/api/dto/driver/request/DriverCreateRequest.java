package by.vanzoneway.dbrestapi.api.dto.driver.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record DriverCreateRequest(
        @NotBlank String fullName,

        @Email String email,

        String phoneNumber,

        @Min(1) @Max(5) Integer averageRating,

        @NotBlank String driverLicenseCategory
) {
}