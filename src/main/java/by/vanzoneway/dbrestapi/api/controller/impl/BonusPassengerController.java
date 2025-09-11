package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.BonusPassengerOperations;
import by.vanzoneway.dbrestapi.core.service.BonusPassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bonus-passengers")
@RequiredArgsConstructor
public class BonusPassengerController implements BonusPassengerOperations {

    private final BonusPassengerService bonusPassengerService;

    @Override
    @PostMapping("/{bonusId}/passengers/{passengerId}")
    public void addPassengerToBonus(@PathVariable Long bonusId, @PathVariable Long passengerId) {
        bonusPassengerService.addPassengerToBonus(bonusId, passengerId);
    }

    @Override
    @DeleteMapping("/{bonusId}/passengers/{passengerId}")
    public void removePassengerFromBonus(@PathVariable Long bonusId, @PathVariable Long passengerId) {
        bonusPassengerService.removePassengerFromBonus(bonusId, passengerId);
    }

    @Override
    @GetMapping("/passengers/{passengerId}/bonuses")
    public List<Long> getPassengerBonuses(@PathVariable Long passengerId) {
        return bonusPassengerService.getPassengerBonuses(passengerId);
    }

    @Override
    @GetMapping("/{bonusId}/passengers")
    public List<Long> getBonusPassengers(@PathVariable Long bonusId) {
        return bonusPassengerService.getBonusPassengers(bonusId);
    }
}