package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.passengerrating.filter.PassengerRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.response.PassengerRatingResponse;
import by.vanzoneway.dbrestapi.core.mapper.PassengerRatingMapper;
import by.vanzoneway.dbrestapi.core.model.Passenger;
import by.vanzoneway.dbrestapi.core.model.PassengerRating;
import by.vanzoneway.dbrestapi.core.repository.PassengerRatingRepository;
import by.vanzoneway.dbrestapi.core.repository.PassengerRepository;
import by.vanzoneway.dbrestapi.core.service.PassengerRatingService;
import by.vanzoneway.dbrestapi.core.specification.PassengerRatingSpecification;
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
public class PassengerRatingServiceImpl implements PassengerRatingService {

    private final PassengerRatingRepository passengerRatingRepository;
    private final PassengerRepository passengerRepository;
    private final PassengerRatingMapper passengerRatingMapper;

    @Override
    public PassengerRatingResponse createPassengerRating(PassengerRatingCreateRequest request) {
        Passenger passenger = passengerRepository.findById(request.passengerId())
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + request.passengerId()));

        PassengerRating passengerRating = passengerRatingMapper.toEntity(request);
        passengerRating.setCreationDate(LocalDateTime.now());
        passengerRating.setModerationStatus("PENDING"); // default status
        passengerRating.setPassenger(passenger);

        PassengerRating savedPassengerRating = passengerRatingRepository.save(passengerRating);
        log.info("Created passenger rating with id: {}", savedPassengerRating.getId());

        return passengerRatingMapper.toResponse(savedPassengerRating);
    }

    @Override
    public PassengerRatingResponse updatePassengerRating(PassengerRatingUpdateRequest request) {
        PassengerRating passengerRating = passengerRatingRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Passenger rating not found with id: " + request.id()));

        if (request.passengerId() != null) {
            Passenger passenger = passengerRepository.findById(request.passengerId())
                    .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + request.passengerId()));
            passengerRating.setPassenger(passenger);
        }

        passengerRatingMapper.updateEntity(request, passengerRating);
        PassengerRating updatedPassengerRating = passengerRatingRepository.save(passengerRating);
        log.info("Updated passenger rating with id: {}", updatedPassengerRating.getId());

        return passengerRatingMapper.toResponse(passengerRating);
    }

    @Override
    public void deletePassengerRating(Long passengerRatingId) {
        if (!passengerRatingRepository.existsById(passengerRatingId)) {
            throw new EntityNotFoundException("Passenger rating not found with id: " + passengerRatingId);
        }

        passengerRatingRepository.deleteById(passengerRatingId);
        log.info("Deleted passenger rating with id: {}", passengerRatingId);
    }

    @Override
    public List<PassengerRatingResponse> findAllPassengerRatings() {
        List<PassengerRating> passengerRatings = passengerRatingRepository.findAll();
        return passengerRatingMapper.toResponseList(passengerRatings);
    }

    @Override
    public List<PassengerRatingResponse> findPassengerRatingsWithFilter(PassengerRatingFilter filter) {
        Specification<PassengerRating> spec = PassengerRatingSpecification.withFilter(filter);
        List<PassengerRating> passengerRatings = passengerRatingRepository.findAll(spec);
        return passengerRatingMapper.toResponseList(passengerRatings);
    }

    @Override
    public PassengerRatingResponse findPassengerRating(Long passengerRatingId) {
        return passengerRatingMapper.toResponse(passengerRatingRepository.findById(passengerRatingId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger rating not found with id: " + passengerRatingId)));
    }
}