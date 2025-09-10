package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.ride.filter.RideFilter;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.response.RideResponse;
import by.vanzoneway.dbrestapi.core.mapper.RideMapper;
import by.vanzoneway.dbrestapi.core.model.Driver;
import by.vanzoneway.dbrestapi.core.model.Passenger;
import by.vanzoneway.dbrestapi.core.model.Ride;
import by.vanzoneway.dbrestapi.core.repository.DriverRepository;
import by.vanzoneway.dbrestapi.core.repository.PassengerRepository;
import by.vanzoneway.dbrestapi.core.repository.RideRepository;
import by.vanzoneway.dbrestapi.core.service.RideService;
import by.vanzoneway.dbrestapi.core.specification.RideSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;
    private final RideMapper rideMapper;

    @Override
    public RideResponse createRide(RideCreateRequest request) {
        Passenger passenger = passengerRepository.findById(request.passengerId())
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + request.passengerId()));

        Driver driver = driverRepository.findById(request.driverId())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + request.driverId()));

        Ride ride = rideMapper.toEntity(request);
        ride.setPassenger(passenger);
        ride.setDriver(driver);

        if (ride.getCreationDate() == null) {
            ride.setCreationDate(LocalDateTime.now());
        }

        Ride savedRide = rideRepository.save(ride);
        log.info("Created ride with id: {}", savedRide.getId());

        return rideMapper.toResponse(savedRide);
    }

    @Override
    public RideResponse updateRide(RideUpdateRequest request) {
        Ride ride = rideRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Ride not found with id: " + request.id()));

        if (request.passengerId() != null) {
            Passenger passenger = passengerRepository.findById(request.passengerId())
                    .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + request.passengerId()));
            ride.setPassenger(passenger);
        }

        if (request.driverId() != null) {
            Driver driver = driverRepository.findById(request.driverId())
                    .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + request.driverId()));
            ride.setDriver(driver);
        }

        rideMapper.updateEntity(request, ride);
        Ride updatedRide = rideRepository.save(ride);
        log.info("Updated ride with id: {}", updatedRide.getId());

        return rideMapper.toResponse(updatedRide);
    }

    @Override
    public void deleteRide(Long rideId) {
        if (!rideRepository.existsById(rideId)) {
            throw new EntityNotFoundException("Ride not found with id: " + rideId);
        }

        rideRepository.deleteById(rideId);
        log.info("Deleted ride with id: {}", rideId);
    }

    @Override
    public List<RideResponse> findAllRides() {
        List<Ride> rides = rideRepository.findAll();
        return rideMapper.toResponseList(rides);
    }

    @Override
    public List<RideResponse> findRidesWithFilter(RideFilter filter) {
        Specification<Ride> spec = RideSpecification.withFilter(filter);
        List<Ride> rides = rideRepository.findAll(spec);
        return rideMapper.toResponseList(rides);
    }

    @Override
    public RideResponse findRide(Long rideId) {
        return rideMapper.toResponse(rideRepository.findById(rideId)
                .orElseThrow(() -> new EntityNotFoundException("Ride not found with id: " + rideId)));
    }
}