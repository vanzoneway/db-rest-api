package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.driver.filter.DriverFilter;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.response.DriverResponse;
import by.vanzoneway.dbrestapi.core.mapper.DriverMapper;
import by.vanzoneway.dbrestapi.core.model.Driver;
import by.vanzoneway.dbrestapi.core.repository.DriverRepository;
import by.vanzoneway.dbrestapi.core.service.DriverService;
import by.vanzoneway.dbrestapi.core.specification.DriverSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public DriverResponse createDriver(DriverCreateRequest request) {
        Driver driver = driverMapper.toEntity(request);
        Driver savedDriver = driverRepository.save(driver);
        log.info("Created driver with id: {}", savedDriver.getId());

        return driverMapper.toResponse(savedDriver);
    }

    @Override
    public DriverResponse updateDriver(DriverUpdateRequest request) {
        Driver driver = driverRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + request.id()));

        driverMapper.updateEntity(request, driver);
        Driver updatedDriver = driverRepository.save(driver);
        log.info("Updated driver with id: {}", updatedDriver.getId());

        return driverMapper.toResponse(updatedDriver);
    }

    @Override
    public void deleteDriver(Long driverId) {
        if (!driverRepository.existsById(driverId)) {
            throw new EntityNotFoundException("Driver not found with id: " + driverId);
        }

        driverRepository.deleteById(driverId);
        log.info("Deleted driver with id: {}", driverId);
    }

    @Override
    public List<DriverResponse> findAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return driverMapper.toResponseList(drivers);
    }

    @Override
    public List<DriverResponse> findDriversWithFilter(DriverFilter filter) {
        Specification<Driver> spec = DriverSpecification.withFilter(filter);
        List<Driver> drivers = driverRepository.findAll(spec);
        return driverMapper.toResponseList(drivers);
    }

    @Override
    public DriverResponse findDriver(Long driverId) {
        return driverMapper.toResponse(driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + driverId)));
    }
}