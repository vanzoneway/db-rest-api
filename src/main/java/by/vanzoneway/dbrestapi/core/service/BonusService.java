package by.vanzoneway.dbrestapi.core.service;

import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.response.BonusResponse;

import java.util.List;

public interface BonusService {

    /**
     * Создает новый бонус
     *
     * @param request данные для создания бонуса
     * @return созданный бонус
     */
    BonusResponse createBonus(BonusCreateRequest request);

    /**
     * Обновляет данные бонуса
     *
     * @param request данные для обновления бонуса
     * @return обновленный бонус
     * @throws jakarta.persistence.EntityNotFoundException если бонус не найден
     */
    BonusResponse updateBonus(BonusUpdateRequest request);

    /**
     * Уделяет бонус по ID
     *
     * @param bonusId ID бонуса
     * @throws jakarta.persistence.EntityNotFoundException если бонус не найден
     */
    void deleteBonus(Long bonusId);

    /**
     * Возвращает все бонусы
     *
     * @return список всех бонусов
     */
    List<BonusResponse> findAllBonuses();

    /**
     * Возвращает бонус по ID
     *
     * @param bonusId ID бонуса
     * @return найденный бонус
     * @throws jakarta.persistence.EntityNotFoundException если бонус не найден
     */
    BonusResponse findBonus(Long bonusId);
}