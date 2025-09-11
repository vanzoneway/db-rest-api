package by.vanzoneway.dbrestapi.core.service;

import by.vanzoneway.dbrestapi.api.dto.passengercredentials.filter.PassengerCredentialsFilter;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.request.PassengerCredentialsUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.passengercredentials.response.PassengerCredentialsResponse;

import java.util.List;

public interface PassengerCredentialsService {

    /**
     * Создает новые учетные данные пассажира
     *
     * @param request данные для создания учетных данных
     * @return созданные учетные данные
     */
    PassengerCredentialsResponse createPassengerCredentials(PassengerCredentialsCreateRequest request);

    /**
     * Обновляет учетные данные пассажира
     *
     * @param request данные для обновления учетных данных
     * @return обновленные учетные данные
     * @throws jakarta.persistence.EntityNotFoundException если учетные данные не найдены
     */
    PassengerCredentialsResponse updatePassengerCredentials(PassengerCredentialsUpdateRequest request);

    /**
     * Удаляет учетные данные пассажира по ID
     *
     * @param credentialsId ID учетных данных
     * @throws jakarta.persistence.EntityNotFoundException если учетные данные не найдены
     */
    void deletePassengerCredentials(Long credentialsId);

    /**
     * Возвращает все учетные данные пассажиров
     *
     * @return список всех учетных данных
     */
    List<PassengerCredentialsResponse> findAllPassengerCredentials();

    /**
     * Возвращает учетные данные с применением фильтра
     *
     * @param filter параметры фильтрации
     * @return отфильтрованный список учетных данных
     */
    List<PassengerCredentialsResponse> findPassengerCredentialsWithFilter(PassengerCredentialsFilter filter);

    /**
     * Возвращает учетные данные по ID
     *
     * @param credentialsId ID учетных данных
     * @return найденные учетные данные
     * @throws jakarta.persistence.EntityNotFoundException если учетные данные не найдены
     */
    PassengerCredentialsResponse findPassengerCredentials(Long credentialsId);

    /**
     * Возвращает учетные данные по ID пассажира
     *
     * @param passengerId ID пассажира
     * @return найденные учетные данные
     * @throws jakarta.persistence.EntityNotFoundException если учетные данные не найдены
     */
    PassengerCredentialsResponse findPassengerCredentialsByPassengerId(Long passengerId);
}
