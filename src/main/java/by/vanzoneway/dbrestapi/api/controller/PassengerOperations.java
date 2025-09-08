package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.passenger.filter.PassengerFilter;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.response.PassengerResponse;

import java.util.List;

public interface PassengerOperations {

    PassengerResponse createPassenger(PassengerCreateRequest passengerCreateRequest);

    PassengerResponse updatePassenger(PassengerUpdateRequest passengerUpdateRequest);

    void deletePassenger(Long passengerId);

    List<PassengerResponse> findAllPassengers();

    List<PassengerResponse> findPassengersWithFilter(PassengerFilter passengerFilter);
}
