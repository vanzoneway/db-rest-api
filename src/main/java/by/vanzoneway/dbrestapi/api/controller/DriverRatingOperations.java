package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.driverrating.filter.DriverRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.response.DriverRatingResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DriverRatingOperations {

    DriverRatingResponse createDriverRating(@Valid DriverRatingCreateRequest request);

    DriverRatingResponse updateDriverRating(@Valid DriverRatingUpdateRequest request);

    void deleteDriverRating(@PathVariable Long driverRatingId);

    List<DriverRatingResponse> findAllDriverRatings();

    DriverRatingResponse findDriverRating(@PathVariable Long driverRatingId);

    List<DriverRatingResponse> findDriverRatingsWithFilter(@ModelAttribute DriverRatingFilter filter);

}
