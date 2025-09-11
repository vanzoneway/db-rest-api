package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.BonusOperations;
import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.response.BonusResponse;
import by.vanzoneway.dbrestapi.core.service.BonusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bonuses")
@RequiredArgsConstructor
@Validated
public class BonusController implements BonusOperations {

    private final BonusService bonusService;

    @Override
    @PostMapping
    public BonusResponse createBonus(@Valid @RequestBody BonusCreateRequest request) {
        return bonusService.createBonus(request);
    }

    @Override
    @PutMapping
    public BonusResponse updateBonus(@Valid @RequestBody BonusUpdateRequest request) {
        return bonusService.updateBonus(request);
    }

    @Override
    @DeleteMapping("/{bonusId}")
    public void deleteBonus(@PathVariable Long bonusId) {
        bonusService.deleteBonus(bonusId);
    }

    @Override
    @GetMapping
    public List<BonusResponse> findAllBonuses() {
        return bonusService.findAllBonuses();
    }

    @Override
    @GetMapping("/{bonusId}")
    public BonusResponse findBonus(@PathVariable Long bonusId) {
        return bonusService.findBonus(bonusId);
    }
}
