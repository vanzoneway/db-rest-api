package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.passengerrating.filter.PassengerRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.response.PassengerRatingResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface PassengerRatingOperations {

    PassengerRatingResponse createPassengerRating(@Valid PassengerRatingCreateRequest passengerRatingCreateRequest);

    PassengerRatingResponse updatePassengerRating(@Valid PassengerRatingUpdateRequest passengerRatingUpdateRequest);

    void deletePassengerRating(Long passengerRatingId);

    List<PassengerRatingResponse> findAllPassengerRatings();

    PassengerRatingResponse findPassengerRating(Long passengerRatingId);

    List<PassengerRatingResponse> findPassengerRatingsWithFilter(PassengerRatingFilter passengerRatingFilter);
}