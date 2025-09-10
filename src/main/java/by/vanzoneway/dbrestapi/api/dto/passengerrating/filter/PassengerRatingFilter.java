package by.vanzoneway.dbrestapi.api.dto.passengerrating.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRatingFilter {

    private String id;
    private String comment;
    private Integer minRating;
    private Integer maxRating;
    private String moderationStatus;
    private String passengerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}