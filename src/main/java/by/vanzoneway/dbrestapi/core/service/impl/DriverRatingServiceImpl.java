package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.driverrating.filter.DriverRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.response.DriverRatingResponse;
import by.vanzoneway.dbrestapi.core.mapper.DriverRatingMapper;
import by.vanzoneway.dbrestapi.core.model.Driver;
import by.vanzoneway.dbrestapi.core.model.DriverRating;
import by.vanzoneway.dbrestapi.core.repository.DriverRatingRepository;
import by.vanzoneway.dbrestapi.core.repository.DriverRepository;
import by.vanzoneway.dbrestapi.core.service.DriverRatingService;
import by.vanzoneway.dbrestapi.core.specification.DriverRatingSpecification;
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
public class DriverRatingServiceImpl implements DriverRatingService {

    private final DriverRatingRepository driverRatingRepository;
    private final DriverRepository driverRepository;
    private final DriverRatingMapper driverRatingMapper;

    @Override
    public DriverRatingResponse createDriverRating(DriverRatingCreateRequest request) {
        Driver driver = driverRepository.findById(request.driverId())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + request.driverId()));

        DriverRating driverRating = driverRatingMapper.toEntity(request);
        driverRating.setCreationDate(LocalDateTime.now());
        driverRating.setModerationStatus("PENDING");
        driverRating.setDriver(driver);

        DriverRating savedDriverRating = driverRatingRepository.save(driverRating);
        log.info("Created driver rating with id: {}", savedDriverRating.getId());

        return driverRatingMapper.toResponse(savedDriverRating);
    }

    @Override
    public DriverRatingResponse updateDriverRating(DriverRatingUpdateRequest request) {
        DriverRating driverRating = driverRatingRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Driver rating not found with id: " + request.id()));

        if (request.driverId() != null) {
            Driver driver = driverRepository.findById(request.driverId())
                    .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + request.driverId()));
            driverRating.setDriver(driver);
        }

        driverRatingMapper.updateEntity(request, driverRating);
        DriverRating updatedDriverRating = driverRatingRepository.save(driverRating);
        log.info("Updated driver rating with id: {}", updatedDriverRating.getId());

        return driverRatingMapper.toResponse(updatedDriverRating);
    }

    @Override
    public void deleteDriverRating(Long driverRatingId) {
        if (!driverRatingRepository.existsById(driverRatingId)) {
            throw new EntityNotFoundException("Driver rating not found with id: " + driverRatingId);
        }

        driverRatingRepository.deleteById(driverRatingId);
        log.info("Deleted driver rating with id: {}", driverRatingId);
    }

    @Override
    public List<DriverRatingResponse> findAllDriverRatings() {
        List<DriverRating> driverRatings = driverRatingRepository.findAll();
        return driverRatingMapper.toResponseList(driverRatings);
    }

    @Override
    public List<DriverRatingResponse> findDriverRatingsWithFilter(DriverRatingFilter filter) {
        Specification<DriverRating> spec = DriverRatingSpecification.withFilter(filter);
        List<DriverRating> driverRatings = driverRatingRepository.findAll(spec);
        return driverRatingMapper.toResponseList(driverRatings);
    }

    @Override
    public DriverRatingResponse findDriverRating(Long driverRatingId) {
        return driverRatingMapper.toResponse(driverRatingRepository.findById(driverRatingId)
                .orElseThrow(() -> new EntityNotFoundException("Driver rating not found with id: " + driverRatingId)));
    }
}