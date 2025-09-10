package by.vanzoneway.dbrestapi.core.service;

import by.vanzoneway.dbrestapi.api.dto.passengerrating.filter.PassengerRatingFilter;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.request.PassengerRatingUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengerrating.response.PassengerRatingResponse;

import java.util.List;

public interface PassengerRatingService {

    /**
     * Создает новый рейтинг пассажира
     *
     * @param request данные для создания рейтинга
     * @return созданный рейтинг
     */
    PassengerRatingResponse createPassengerRating(PassengerRatingCreateRequest request);

    /**
     * Обновляет данные рейтинга пассажира
     *
     * @param request данные для обновления рейтинга
     * @return обновленный рейтинг
     * @throws jakarta.persistence.EntityNotFoundException если рейтинг не найден
     */
    PassengerRatingResponse updatePassengerRating(PassengerRatingUpdateRequest request);

    /**
     * Удаляет рейтинг пассажира по ID
     *
     * @param passengerRatingId ID рейтинга
     * @throws jakarta.persistence.EntityNotFoundException если рейтинг не найден
     */
    void deletePassengerRating(Long passengerRatingId);

    /**
     * Возвращает все рейтинги пассажиров
     *
     * @return список всех рейтингов
     */
    List<PassengerRatingResponse> findAllPassengerRatings();

    /**
     * Возвращает рейтинги пассажиров с применением фильтра
     *
     * @param filter параметры фильтрации
     * @return отфильтрованный список рейтингов
     */
    List<PassengerRatingResponse> findPassengerRatingsWithFilter(PassengerRatingFilter filter);

    /**
     * Возвращает рейтинг пассажира по ID
     *
     * @param passengerRatingId ID рейтинга
     * @return найденный рейтинг
     * @throws jakarta.persistence.EntityNotFoundException если рейтинг не найден
     */
    PassengerRatingResponse findPassengerRating(Long passengerRatingId);
}