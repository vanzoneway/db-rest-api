package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.PassengerCredentialsOperations;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.filter.PassengerCredentialsFilter;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.response.PassengerCredentialsResponse;
import by.vanzoneway.dbrestapi.core.service.PassengerCredentialsService;
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
@RequestMapping("/api/v1/passenger-credentials")
@RequiredArgsConstructor
@Validated
public class PassengerCredentialsController implements PassengerCredentialsOperations {

    private final PassengerCredentialsService passengerCredentialsService;

    @Override
    @PostMapping
    public PassengerCredentialsResponse createPassengerCredentials(@Valid @RequestBody PassengerCredentialsCreateRequest request) {
        return passengerCredentialsService.createPassengerCredentials(request);
    }

    @Override
    @PutMapping
    public PassengerCredentialsResponse updatePassengerCredentials(@Valid @RequestBody PassengerCredentialsUpdateRequest request) {
        return passengerCredentialsService.updatePassengerCredentials(request);
    }

    @Override
    @DeleteMapping("/{credentialsId}")
    public void deletePassengerCredentials(@PathVariable Long credentialsId) {
        passengerCredentialsService.deletePassengerCredentials(credentialsId);
    }

    @Override
    @GetMapping
    public List<PassengerCredentialsResponse> findAllPassengerCredentials() {
        return passengerCredentialsService.findAllPassengerCredentials();
    }

    @Override
    @GetMapping("/{credentialsId}")
    public PassengerCredentialsResponse findPassengerCredentials(@PathVariable Long credentialsId) {
        return passengerCredentialsService.findPassengerCredentials(credentialsId);
    }

    @Override
    @GetMapping("/passenger/{passengerId}")
    public PassengerCredentialsResponse findPassengerCredentialsByPassengerId(@PathVariable Long passengerId) {
        return passengerCredentialsService.findPassengerCredentialsByPassengerId(passengerId);
    }

    @Override
    @GetMapping("/filter")
    public List<PassengerCredentialsResponse> findPassengerCredentialsWithFilter(@ModelAttribute PassengerCredentialsFilter filter) {
        return passengerCredentialsService.findPassengerCredentialsWithFilter(filter);
    }
}
