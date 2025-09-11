package by.vanzoneway.dbrestapi.core.mapper;

import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.response.PassengerCredentialsResponse;
import by.vanzoneway.dbrestapi.core.model.PassengerCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerCredentialsMapper {

    @Mapping(target = "passengerId", source = "passenger.id")
    @Mapping(target = "passengerName", source = "passenger.fullName")
    PassengerCredentialsResponse toResponse(PassengerCredentials credentials);

    List<PassengerCredentialsResponse> toResponseList(List<PassengerCredentials> credentials);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passenger", ignore = true)
    PassengerCredentials toEntity(PassengerCredentialsCreateRequest request);

    @Mapping(target = "passenger", ignore = true)
    void updateEntity(PassengerCredentialsUpdateRequest request, @MappingTarget PassengerCredentials credentials);
}