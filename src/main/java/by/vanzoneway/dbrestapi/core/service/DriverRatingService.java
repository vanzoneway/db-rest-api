package by.vanzoneway.dbrestapi.core.service;


import by.vanzoneway.dbrestapi.api.dto.driverrating.filter.DriverRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.request.DriverRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driverrating.response.DriverRatingResponse;

import java.util.List;

public interface DriverRatingService {

    /**
     * Создает новый рейтинг водителя
     *
     * @param request данные для создания рейтинга
     * @return созданный рейтинг
     */
    DriverRatingResponse createDriverRating(DriverRatingCreateRequest request);

    /**
     * Обновляет данные рейтинга водителя
     *
     * @param request данные для обновления рейтинга
     * @return обновленный рейтинг
     * @throws jakarta.persistence.EntityNotFoundException если рейтинг не найден
     */
    DriverRatingResponse updateDriverRating(DriverRatingUpdateRequest request);

    /**
     * Удаляет рейтинг водителя по ID
     *
     * @param driverRatingId ID рейтинга
     * @throws jakarta.persistence.EntityNotFoundException если рейтинг не найден
     */
    void deleteDriverRating(Long driverRatingId);

    /**
     * Возвращает все рейтинги водителей
     *
     * @return список всех рейтингов
     */
    List<DriverRatingResponse> findAllDriverRatings();

    /**
     * Возвращает рейтинги водителей с применением фильтра
     *
     * @param filter параметры фильтрации
     * @return отфильтрованный список рейтингов
     */
    List<DriverRatingResponse> findDriverRatingsWithFilter(DriverRatingFilter filter);

    /**
     * Возвращает рейтинг водителя по ID
     *
     * @param driverRatingId ID рейтинга
     * @return найденный рейтинг
     * @throws jakarta.persistence.EntityNotFoundException если рейтинг не найден
     */
    DriverRatingResponse findDriverRating(Long driverRatingId);
}