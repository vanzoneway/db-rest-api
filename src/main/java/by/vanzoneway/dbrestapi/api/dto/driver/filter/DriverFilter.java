package by.vanzoneway.dbrestapi.api.dto.driver.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverFilter {

    private String id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private Integer minRating;

    private Integer maxRating;

    private String driverLicenseCategory;
}