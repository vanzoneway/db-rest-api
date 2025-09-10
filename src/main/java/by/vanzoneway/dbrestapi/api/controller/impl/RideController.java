package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.RideOperations;
import by.vanzoneway.dbrestapi.api.dto.ride.filter.RideFilter;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.response.RideResponse;
import by.vanzoneway.dbrestapi.core.service.RideService;
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
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
@Validated
public class RideController implements RideOperations {

    private final RideService rideService;

    @Override
    @PostMapping
    public RideResponse createRide(@Valid @RequestBody RideCreateRequest request) {
        return rideService.createRide(request);
    }

    @Override
    @PutMapping
    public RideResponse updateRide(@Valid @RequestBody RideUpdateRequest request) {
        return rideService.updateRide(request);
    }

    @Override
    @DeleteMapping("/{rideId}")
    public void deleteRide(@PathVariable Long rideId) {
        rideService.deleteRide(rideId);
    }

    @Override
    @GetMapping
    public List<RideResponse> findAllRides() {
        return rideService.findAllRides();
    }

    @Override
    @GetMapping("/{rideId}")
    public RideResponse findRide(@PathVariable Long rideId) {
        return rideService.findRide(rideId);
    }

    @Override
    @GetMapping("/filter")
    public List<RideResponse> findRidesWithFilter(@ModelAttribute RideFilter filter) {
        return rideService.findRidesWithFilter(filter);
    }
}