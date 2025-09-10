package by.vanzoneway.dbrestapi.api.dto.ride.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideFilter {
    private String id;
    private String passengerId;
    private String driverId;
    private String departureAddress;
    private String destinationAddress;
    private Double minCost;
    private Double maxCost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}