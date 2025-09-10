package by.vanzoneway.dbrestapi.core.mapper;

import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.response.PassengerRatingResponse;
import by.vanzoneway.dbrestapi.core.model.PassengerRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerRatingMapper {

    PassengerRatingMapper INSTANCE = Mappers.getMapper(PassengerRatingMapper.class);

    @Mapping(target = "passengerId", source = "passenger.id")
    @Mapping(target = "passengerName", source = "passenger.fullName")
    PassengerRatingResponse toResponse(PassengerRating passengerRating);

    List<PassengerRatingResponse> toResponseList(List<PassengerRating> passengerRatings);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "moderationStatus", ignore = true)
    PassengerRating toEntity(PassengerRatingCreateRequest request);

    @Mapping(target = "creationDate", ignore = true)
    void updateEntity(PassengerRatingUpdateRequest request, @MappingTarget PassengerRating passengerRating);
}