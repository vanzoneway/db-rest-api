package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.passengercredentials.filter.PassengerCredentialsFilter;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.response.PassengerCredentialsResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface PassengerCredentialsOperations {

    PassengerCredentialsResponse createPassengerCredentials(@Valid PassengerCredentialsCreateRequest request);

    PassengerCredentialsResponse updatePassengerCredentials(@Valid PassengerCredentialsUpdateRequest request);

    void deletePassengerCredentials(Long credentialsId);

    List<PassengerCredentialsResponse> findAllPassengerCredentials();

    PassengerCredentialsResponse findPassengerCredentials(Long credentialsId);

    PassengerCredentialsResponse findPassengerCredentialsByPassengerId(Long passengerId);

    List<PassengerCredentialsResponse> findPassengerCredentialsWithFilter(PassengerCredentialsFilter filter);
}