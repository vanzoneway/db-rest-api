package by.vanzoneway.dbrestapi.core.mapper;

import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.response.BonusResponse;
import by.vanzoneway.dbrestapi.core.model.Bonus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BonusMapper {

    BonusResponse toResponse(Bonus bonus);

    List<BonusResponse> toResponseList(List<Bonus> bonuses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passengers", ignore = true)
    Bonus toEntity(BonusCreateRequest request);

    @Mapping(target = "passengers", ignore = true)
    void updateEntity(BonusUpdateRequest request, @MappingTarget Bonus bonus);
}
