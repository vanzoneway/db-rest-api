package by.vanzoneway.dbrestapi.core.repository;

import by.vanzoneway.dbrestapi.core.model.PassengerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRatingRepository extends JpaRepository<PassengerRating, Long>, JpaSpecificationExecutor<PassengerRating> {
}
