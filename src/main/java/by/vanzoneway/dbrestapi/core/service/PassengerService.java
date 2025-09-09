package by.vanzoneway.dbrestapi.core.service;

import by.vanzoneway.dbrestapi.api.dto.passenger.filter.PassengerFilter;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.request.PassengerUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passenger.response.PassengerResponse;

import java.util.List;

public interface PassengerService {

    /**
     * Создает нового пассажира
     *
     * @param request данные для создания пассажира
     * @return созданный пассажир
     */
    PassengerResponse createPassenger(PassengerCreateRequest request);

    /**
     * Обновляет данные пассажира
     *
     * @param request данные для обновления пассажира
     * @return обновленный пассажир
     * @throws jakarta.persistence.EntityNotFoundException если пассажир не найден
     */
    PassengerResponse updatePassenger(PassengerUpdateRequest request);

    /**
     * Удаляет пассажира по ID
     *
     * @param passengerId ID пассажира
     * @throws jakarta.persistence.EntityNotFoundException если пассажир не найден
     */
    void deletePassenger(Long passengerId);

    /**
     * Возвращает всех пассажиров
     *
     * @return список всех пассажиров
     */
    List<PassengerResponse> findAllPassengers();

    /**
     * Возвращает пассажиров с применением фильтра
     *
     * @param filter параметры фильтрации
     * @return отфильтрованный список пассажиров
     */
    List<PassengerResponse> findPassengersWithFilter(PassengerFilter filter);

    PassengerResponse findPassenger(Long passengerId);
}