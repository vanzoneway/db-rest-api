package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.DriverRatingOperations;
import by.vanzoneway.dbrestapi.api.dto.driverrating.filter.DriverRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.response.DriverRatingResponse;
import by.vanzoneway.dbrestapi.core.service.DriverRatingService;
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
@RequestMapping("/api/v1/driver-ratings")
@RequiredArgsConstructor
@Validated
public class DriverRatingController implements DriverRatingOperations {

    private final DriverRatingService driverRatingService;

    @PostMapping
    @Override
    public DriverRatingResponse createDriverRating(@Valid @RequestBody DriverRatingCreateRequest request) {
        return driverRatingService.createDriverRating(request);
    }

    @PutMapping
    @Override
    public DriverRatingResponse updateDriverRating(@Valid @RequestBody DriverRatingUpdateRequest request) {
        return driverRatingService.updateDriverRating(request);
    }

    @DeleteMapping("/{driverRatingId}")
    @Override
    public void deleteDriverRating(@PathVariable Long driverRatingId) {
        driverRatingService.deleteDriverRating(driverRatingId);
    }

    @GetMapping
    @Override
    public List<DriverRatingResponse> findAllDriverRatings() {
        return driverRatingService.findAllDriverRatings();
    }

    @GetMapping("/{driverRatingId}")
    @Override
    public DriverRatingResponse findDriverRating(@PathVariable Long driverRatingId) {
        return driverRatingService.findDriverRating(driverRatingId);
    }

    @GetMapping("/filter")
    @Override
    public List<DriverRatingResponse> findDriverRatingsWithFilter(@ModelAttribute DriverRatingFilter filter) {
        return driverRatingService.findDriverRatingsWithFilter(filter);
    }
}
