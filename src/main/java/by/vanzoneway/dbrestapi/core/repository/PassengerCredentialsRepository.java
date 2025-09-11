package by.vanzoneway.dbrestapi.core.repository;

import by.vanzoneway.dbrestapi.core.model.PassengerCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerCredentialsRepository extends JpaRepository<PassengerCredentials, Long>, JpaSpecificationExecutor<PassengerCredentials> {

    Optional<PassengerCredentials> findByPassengerId(Long passengerId);

    boolean existsByPassengerId(Long passengerId);
}