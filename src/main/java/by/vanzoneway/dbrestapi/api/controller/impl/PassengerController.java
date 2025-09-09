package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.PassengerOperations;
import by.vanzoneway.dbrestapi.api.dto.passenger.filter.PassengerFilter;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.response.PassengerResponse;
import by.vanzoneway.dbrestapi.core.service.PassengerService;
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
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
@Validated
public class PassengerController implements PassengerOperations {

    private final PassengerService passengerService;

    @PostMapping
    @Override
    public PassengerResponse createPassenger(@Valid @RequestBody PassengerCreateRequest passengerCreateRequest) {
        return passengerService.createPassenger(passengerCreateRequest);
    }

    @PutMapping
    @Override
    public PassengerResponse updatePassenger(@Valid @RequestBody PassengerUpdateRequest passengerUpdateRequest) {
        return passengerService.updatePassenger(passengerUpdateRequest);
    }

    @DeleteMapping("/{passengerId}")
    @Override
    public void deletePassenger(@PathVariable Long passengerId) {
        passengerService.deletePassenger(passengerId);
    }

    @GetMapping
    @Override
    public List<PassengerResponse> findAllPassengers() {
        return passengerService.findAllPassengers();
    }

    @GetMapping("/{passengerId}")
    @Override
    public PassengerResponse findPassenger(@PathVariable Long passengerId) {
        return passengerService.findPassenger(passengerId);
    }

    @GetMapping("/filter")
    @Override
    public List<PassengerResponse> findPassengersWithFilter(@ModelAttribute PassengerFilter passengerFilter) {
        return passengerService.findPassengersWithFilter(passengerFilter);
    }
}