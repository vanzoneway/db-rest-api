package by.vanzoneway.dbrestapi.core.mapper;

import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.response.DriverRatingResponse;
import by.vanzoneway.dbrestapi.core.model.DriverRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverRatingMapper {

    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "driverName", source = "driver.fullName")
    DriverRatingResponse toResponse(DriverRating driverRating);

    List<DriverRatingResponse> toResponseList(List<DriverRating> driverRatings);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "moderationStatus", ignore = true)
    DriverRating toEntity(DriverRatingCreateRequest request);

    @Mapping(target = "creationDate", ignore = true)
    void updateEntity(DriverRatingUpdateRequest request, @MappingTarget DriverRating driverRating);
}