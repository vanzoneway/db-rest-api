package by.vanzoneway.dbrestapi.core.service;

import by.vanzoneway.dbrestapi.api.dto.driver.filter.DriverFilter;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.request.DriverUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.driver.response.DriverResponse;

import java.util.List;

public interface DriverService {

    /**
     * Создает нового водителя
     *
     * @param request данные для создания водителя
     * @return созданный водитель
     */
    DriverResponse createDriver(DriverCreateRequest request);

    /**
     * Обновляет данные водителя
     *
     * @param request данные для обновления водителя
     * @return обновленный водитель
     * @throws jakarta.persistence.EntityNotFoundException если водитель не найден
     */
    DriverResponse updateDriver(DriverUpdateRequest request);

    /**
     * Удаляет водителя по ID
     *
     * @param driverId ID водителя
     * @throws jakarta.persistence.EntityNotFoundException если водитель не найден
     */
    void deleteDriver(Long driverId);

    /**
     * Возвращает всех водителей
     *
     * @return список всех водителей
     */
    List<DriverResponse> findAllDrivers();

    /**
     * Возвращает водителей с применением фильтра
     *
     * @param filter параметры фильтрации
     * @return отфильтрованный список водителей
     */
    List<DriverResponse> findDriversWithFilter(DriverFilter filter);

    /**
     * Возвращает водителя по ID
     *
     * @param driverId ID водителя
     * @return найденный водитель
     * @throws jakarta.persistence.EntityNotFoundException если водитель не найден
     */
    DriverResponse findDriver(Long driverId);
}