package by.vanzoneway.dbrestapi.api.controller;

import java.util.List;

public interface BonusPassengerOperations {

    void addPassengerToBonus(Long bonusId, Long passengerId);

    void removePassengerFromBonus(Long bonusId, Long passengerId);

    List<Long> getPassengerBonuses(Long passengerId);

    List<Long> getBonusPassengers(Long bonusId);
}
