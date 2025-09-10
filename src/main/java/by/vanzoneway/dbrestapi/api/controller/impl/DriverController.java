package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.DriverOperations;
import by.vanzoneway.dbrestapi.api.dto.driver.filter.DriverFilter;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.response.DriverResponse;
import by.vanzoneway.dbrestapi.core.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
@Validated
public class DriverController implements DriverOperations {

    private final DriverService driverService;

    @PostMapping
    @Override
    public DriverResponse createDriver(@Valid @RequestBody DriverCreateRequest driverCreateRequest) {
        return driverService.createDriver(driverCreateRequest);
    }

    @PutMapping
    @Override
    public DriverResponse updateDriver(@Valid @RequestBody DriverUpdateRequest driverUpdateRequest) {
        return driverService.updateDriver(driverUpdateRequest);
    }

    @DeleteMapping("/{driverId}")
    @Override
    public void deleteDriver(@PathVariable Long driverId) {
        driverService.deleteDriver(driverId);
    }

    @GetMapping
    @Override
    public List<DriverResponse> findAllDrivers() {
        return driverService.findAllDrivers();
    }

    @GetMapping("/{driverId}")
    @Override
    public DriverResponse findDriver(@PathVariable Long driverId) {
        return driverService.findDriver(driverId);
    }

    @GetMapping("/filter")
    @Override
    public List<DriverResponse> findDriversWithFilter(@ModelAttribute DriverFilter driverFilter) {
        return driverService.findDriversWithFilter(driverFilter);
    }
}