package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.passenger.filter.PassengerFilter;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.response.PassengerResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface PassengerOperations {

    PassengerResponse createPassenger(@Valid PassengerCreateRequest passengerCreateRequest);

    PassengerResponse updatePassenger(@Valid PassengerUpdateRequest passengerUpdateRequest);

    void deletePassenger(Long passengerId);

    List<PassengerResponse> findAllPassengers();

    PassengerResponse findPassenger(Long passengerId);

    List<PassengerResponse> findPassengersWithFilter(PassengerFilter passengerFilter);
}