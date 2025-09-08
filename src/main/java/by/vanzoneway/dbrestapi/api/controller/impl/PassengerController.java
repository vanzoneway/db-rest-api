package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.PassengerOperations;
import by.vanzoneway.dbrestapi.api.dto.passenger.filter.PassengerFilter;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.response.PassengerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
public class PassengerController implements PassengerOperations {

    @Override
    public PassengerResponse createPassenger(PassengerCreateRequest passengerCreateRequest) {
        return null;
    }

    @Override
    public PassengerResponse updatePassenger(PassengerUpdateRequest passengerUpdateRequest) {
        return null;
    }

    @Override
    public void deletePassenger(Long passengerId) {

    }

    @Override
    public List<PassengerResponse> findAllPassengers() {
        return List.of();
    }

    @Override
    public List<PassengerResponse> findPassengersWithFilter(PassengerFilter passengerFilter) {
        return List.of();
    }
}
