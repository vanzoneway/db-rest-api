package by.vanzoneway.dbrestapi.core.repository;

import by.vanzoneway.dbrestapi.core.model.DriverRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRatingRepository extends JpaRepository<DriverRating, Long>,
        JpaSpecificationExecutor<DriverRating> {
}
