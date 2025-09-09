package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.passenger.filter.PassengerFilter;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.response.PassengerResponse;
import by.vanzoneway.dbrestapi.core.mapper.PassengerMapper;
import by.vanzoneway.dbrestapi.core.model.Passenger;
import by.vanzoneway.dbrestapi.core.repository.PassengerRepository;
import by.vanzoneway.dbrestapi.core.service.PassengerService;
import by.vanzoneway.dbrestapi.core.specification.PassengerSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Override
    public PassengerResponse createPassenger(PassengerCreateRequest request) {
        Passenger passenger = passengerMapper.toEntity(request);
        Passenger savedPassenger = passengerRepository.save(passenger);
        log.info("Created passenger with id: {}", savedPassenger.getId());

        return passengerMapper.toResponse(savedPassenger);
    }

    @Override
    public PassengerResponse updatePassenger(PassengerUpdateRequest request) {
        Passenger passenger = passengerRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + request.id()));

        passengerMapper.updateEntity(request, passenger);
        Passenger updatedPassenger = passengerRepository.save(passenger);
        log.info("Updated passenger with id: {}", updatedPassenger.getId());

        return passengerMapper.toResponse(updatedPassenger);
    }

    @Override
    public void deletePassenger(Long passengerId) {
        if (!passengerRepository.existsById(passengerId)) {
            throw new EntityNotFoundException("Passenger not found with id: " + passengerId);
        }

        passengerRepository.deleteById(passengerId);
        log.info("Deleted passenger with id: {}", passengerId);
    }

    @Override
    public List<PassengerResponse> findAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengerMapper.toResponseList(passengers);
    }

    @Override
    public List<PassengerResponse> findPassengersWithFilter(PassengerFilter filter) {
        Specification<Passenger> spec = PassengerSpecification.withFilter(filter);
        List<Passenger> passengers = passengerRepository.findAll(spec);
        return passengerMapper.toResponseList(passengers);
    }

    @Override
    public PassengerResponse findPassenger(Long passengerId) {
        return passengerMapper.toResponse(passengerRepository.findById(passengerId).orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + passengerId)));
    }
}