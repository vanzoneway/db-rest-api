package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.PassengerRatingOperations;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.filter.PassengerRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.response.PassengerRatingResponse;
import by.vanzoneway.dbrestapi.core.service.PassengerRatingService;
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
@RequestMapping("/api/v1/passenger-ratings")
@RequiredArgsConstructor
@Validated
public class PassengerRatingController implements PassengerRatingOperations {

    private final PassengerRatingService passengerRatingService;

    @PostMapping
    @Override
    public PassengerRatingResponse createPassengerRating(@Valid @RequestBody PassengerRatingCreateRequest passengerRatingCreateRequest) {
        return passengerRatingService.createPassengerRating(passengerRatingCreateRequest);
    }

    @PutMapping
    @Override
    public PassengerRatingResponse updatePassengerRating(@Valid @RequestBody PassengerRatingUpdateRequest passengerRatingUpdateRequest) {
        return passengerRatingService.updatePassengerRating(passengerRatingUpdateRequest);
    }

    @DeleteMapping("/{passengerRatingId}")
    @Override
    public void deletePassengerRating(@PathVariable Long passengerRatingId) {
        passengerRatingService.deletePassengerRating(passengerRatingId);
    }

    @GetMapping
    @Override
    public List<PassengerRatingResponse> findAllPassengerRatings() {
        return passengerRatingService.findAllPassengerRatings();
    }

    @GetMapping("/{passengerRatingId}")
    @Override
    public PassengerRatingResponse findPassengerRating(@PathVariable Long passengerRatingId) {
        return passengerRatingService.findPassengerRating(passengerRatingId);
    }

    @GetMapping("/filter")
    @Override
    public List<PassengerRatingResponse> findPassengerRatingsWithFilter(@ModelAttribute PassengerRatingFilter passengerRatingFilter) {
        return passengerRatingService.findPassengerRatingsWithFilter(passengerRatingFilter);
    }
}