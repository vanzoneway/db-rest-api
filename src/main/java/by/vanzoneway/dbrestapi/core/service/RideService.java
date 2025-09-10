package by.vanzoneway.dbrestapi.core.service;

import by.vanzoneway.dbrestapi.api.dto.ride.filter.RideFilter;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.request.RideUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.ride.response.RideResponse;

import java.util.List;

public interface RideService {

    /**
     * Создает новую поездку
     *
     * @param request данные для создания поездки
     * @return созданная поездка
     */
    RideResponse createRide(RideCreateRequest request);

    /**
     * Обновляет данные поездки
     *
     * @param request данные для обновления поездки
     * @return обновленная поездка
     * @throws jakarta.persistence.EntityNotFoundException если поездка не найдена
     */
    RideResponse updateRide(RideUpdateRequest request);

    /**
     * Удаляет поездку по ID
     *
     * @param rideId ID поездки
     * @throws jakarta.persistence.EntityNotFoundException если поездка не найдена
     */
    void deleteRide(Long rideId);

    /**
     * Возвращает все поездки
     *
     * @return список всех поездок
     */
    List<RideResponse> findAllRides();

    /**
     * Возвращает поездки с применением фильтра
     *
     * @param filter параметры фильтрации
     * @return отфильтрованный список поездок
     */
    List<RideResponse> findRidesWithFilter(RideFilter filter);

    /**
     * Возвращает поездку по ID
     *
     * @param rideId ID поездки
     * @return найденная поездка
     * @throws jakarta.persistence.EntityNotFoundException если поездка не найдена
     */
    RideResponse findRide(Long rideId);
}