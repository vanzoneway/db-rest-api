package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.passengercredentials.filter.PassengerCredentialsFilter;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.response.PassengerCredentialsResponse;
import by.vanzoneway.dbrestapi.core.exception.PassengerAlreadyHaveCredentials;
import by.vanzoneway.dbrestapi.core.mapper.PassengerCredentialsMapper;
import by.vanzoneway.dbrestapi.core.model.Passenger;
import by.vanzoneway.dbrestapi.core.model.PassengerCredentials;
import by.vanzoneway.dbrestapi.core.repository.PassengerCredentialsRepository;
import by.vanzoneway.dbrestapi.core.repository.PassengerRepository;
import by.vanzoneway.dbrestapi.core.service.PassengerCredentialsService;
import by.vanzoneway.dbrestapi.core.specification.PassengerCredentialsSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassengerCredentialsServiceImpl implements PassengerCredentialsService {

    private final PassengerCredentialsRepository passengerCredentialsRepository;
    private final PassengerRepository passengerRepository;
    private final PassengerCredentialsMapper passengerCredentialsMapper;

    @Override
    @Transactional
    public PassengerCredentialsResponse createPassengerCredentials(PassengerCredentialsCreateRequest request) {
        Passenger passenger = passengerRepository.findById(request.passengerId())
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + request.passengerId()));

        // Проверяем, нет ли уже учетных данных у этого пассажира
        if (passenger.getCredentials() != null) {
            throw new PassengerAlreadyHaveCredentials("Passenger already has credentials");
        }

        PassengerCredentials credentials = passengerCredentialsMapper.toEntity(request);
        credentials.setPassenger(passenger);

        PassengerCredentials savedCredentials = passengerCredentialsRepository.save(credentials);

        // Обновляем связь на стороне пассажира
        passenger.setCredentials(savedCredentials);
        passengerRepository.save(passenger);

        log.info("Created passenger credentials with id: {}", savedCredentials.getId());

        return passengerCredentialsMapper.toResponse(savedCredentials);
    }

    @Override
    @Transactional
    public PassengerCredentialsResponse updatePassengerCredentials(PassengerCredentialsUpdateRequest request) {
        PassengerCredentials credentials = passengerCredentialsRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Passenger credentials not found with id: " + request.id()));

        if (request.passengerId() != null) {
            Passenger passenger = passengerRepository.findById(request.passengerId())
                    .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + request.passengerId()));
            credentials.setPassenger(passenger);
        }

        passengerCredentialsMapper.updateEntity(request, credentials);
        PassengerCredentials updatedCredentials = passengerCredentialsRepository.save(credentials);
        log.info("Updated passenger credentials with id: {}", updatedCredentials.getId());

        return passengerCredentialsMapper.toResponse(updatedCredentials);
    }

    @Override
    @Transactional
    public void deletePassengerCredentials(Long credentialsId) {
        PassengerCredentials credentials = passengerCredentialsRepository.findById(credentialsId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger credentials not found with id: " + credentialsId));

        // Удаляем связь с пассажиром
        if (credentials.getPassenger() != null) {
            credentials.getPassenger().setCredentials(null);
        }

        passengerCredentialsRepository.delete(credentials);
        log.info("Deleted passenger credentials with id: {}", credentialsId);
    }

    @Override
    public List<PassengerCredentialsResponse> findAllPassengerCredentials() {
        List<PassengerCredentials> credentials = passengerCredentialsRepository.findAll();
        return passengerCredentialsMapper.toResponseList(credentials);
    }

    @Override
    public List<PassengerCredentialsResponse> findPassengerCredentialsWithFilter(PassengerCredentialsFilter filter) {
        Specification<PassengerCredentials> spec = PassengerCredentialsSpecification.withFilter(filter);
        List<PassengerCredentials> credentials = passengerCredentialsRepository.findAll(spec);
        return passengerCredentialsMapper.toResponseList(credentials);
    }

    @Override
    public PassengerCredentialsResponse findPassengerCredentials(Long credentialsId) {
        return passengerCredentialsMapper.toResponse(passengerCredentialsRepository.findById(credentialsId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger credentials not found with id: " + credentialsId)));
    }

    @Override
    public PassengerCredentialsResponse findPassengerCredentialsByPassengerId(Long passengerId) {
        return passengerCredentialsMapper.toResponse(passengerCredentialsRepository.findByPassengerId(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger credentials not found for passenger id: " + passengerId)));
    }
}