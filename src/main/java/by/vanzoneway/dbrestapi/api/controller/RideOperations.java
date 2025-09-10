package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.ride.filter.RideFilter;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.response.RideResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface RideOperations {

    @PostMapping
    RideResponse createRide(@Valid RideCreateRequest request);

    @PutMapping
    RideResponse updateRide(@Valid RideUpdateRequest request);

    @DeleteMapping("/{rideId}")
    void deleteRide(@PathVariable Long rideId);

    @GetMapping
    List<RideResponse> findAllRides();

    @GetMapping("/{rideId}")
    RideResponse findRide(@PathVariable Long rideId);

    @GetMapping("/filter")
    List<RideResponse> findRidesWithFilter(@ModelAttribute RideFilter filter);
}