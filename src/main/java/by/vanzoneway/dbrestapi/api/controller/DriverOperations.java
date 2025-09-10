package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.driver.filter.DriverFilter;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.response.DriverResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface DriverOperations {

    DriverResponse createDriver(@Valid DriverCreateRequest driverCreateRequest);

    DriverResponse updateDriver(@Valid DriverUpdateRequest driverUpdateRequest);

    void deleteDriver(Long driverId);

    List<DriverResponse> findAllDrivers();

    DriverResponse findDriver(Long driverId);

    List<DriverResponse> findDriversWithFilter(DriverFilter driverFilter);
}