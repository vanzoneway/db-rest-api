package by.vanzoneway.dbrestapi.core.mapper;

import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.response.DriverResponse;
import by.vanzoneway.dbrestapi.core.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    DriverResponse toResponse(Driver driver);

    List<DriverResponse> toResponseList(List<Driver> drivers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "rides", ignore = true)
    Driver toEntity(DriverCreateRequest request);

    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "rides", ignore = true)
    void updateEntity(DriverUpdateRequest request, @MappingTarget Driver driver);
}