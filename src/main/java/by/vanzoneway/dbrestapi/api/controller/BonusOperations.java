package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.response.BonusResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface BonusOperations {

    BonusResponse createBonus(@Valid BonusCreateRequest request);

    BonusResponse updateBonus(@Valid BonusUpdateRequest request);

    void deleteBonus(Long bonusId);

    List<BonusResponse> findAllBonuses();

    BonusResponse findBonus(Long bonusId);
}
