package by.vanzoneway.dbrestapi.core.service;

import java.util.List;

public interface BonusPassengerService {

    /**
     * Добавляет пассажира к бонусу
     *
     * @param bonusId     ID бонуса
     * @param passengerId ID пассажира
     */
    void addPassengerToBonus(Long bonusId, Long passengerId);

    /**
     * Удаляет пассажира из бонуса
     *
     * @param bonusId     ID бонуса
     * @param passengerId ID пассажира
     */
    void removePassengerFromBonus(Long bonusId, Long passengerId);

    /**
     * Получает все бонусы пассажира
     *
     * @param passengerId ID пассажира
     * @return список бонусов
     */
    List<Long> getPassengerBonuses(Long passengerId);

    /**
     * Получает всех пассажиров бонуса
     *
     * @param bonusId ID бонуса
     * @return список пассажиров
     */
    List<Long> getBonusPassengers(Long bonusId);
}
