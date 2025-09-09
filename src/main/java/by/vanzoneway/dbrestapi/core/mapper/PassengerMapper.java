package by.vanzoneway.dbrestapi.core.mapper;

import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.response.PassengerResponse;
import by.vanzoneway.dbrestapi.core.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);

    PassengerResponse toResponse(Passenger passenger);

    List<PassengerResponse> toResponseList(List<Passenger> passengers);

    @Mapping(target = "id", ignore = true)
    Passenger toEntity(PassengerCreateRequest request);

    void updateEntity(PassengerUpdateRequest request, @MappingTarget Passenger passenger);
}