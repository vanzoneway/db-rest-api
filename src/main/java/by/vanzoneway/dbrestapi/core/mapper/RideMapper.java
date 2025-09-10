package by.vanzoneway.dbrestapi.core.mapper;

import by.vanzoneway.dbrestapi.api.dto.ride.request.RideCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.response.RideResponse;
import by.vanzoneway.dbrestapi.core.model.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RideMapper {

    @Mapping(target = "passengerId", source = "passenger.id")
    @Mapping(target = "passengerName", source = "passenger.fullName")
    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "driverName", source = "driver.fullName")
    RideResponse toResponse(Ride ride);

    List<RideResponse> toResponseList(List<Ride> rides);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passenger", ignore = true)
    @Mapping(target = "driver", ignore = true)
    Ride toEntity(RideCreateRequest request);

    @Mapping(target = "passenger", ignore = true)
    @Mapping(target = "driver", ignore = true)
    void updateEntity(RideUpdateRequest request, @MappingTarget Ride ride);
}