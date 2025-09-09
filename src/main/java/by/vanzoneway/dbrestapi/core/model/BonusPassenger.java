package by.vanzoneway.dbrestapi.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "bonus_passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(BonusPassenger.BonusPassengerId.class)
public class BonusPassenger {

    @Id
    @ManyToOne
    @JoinColumn(name = "bonus_id")
    private Bonus bonus;

    @Id
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BonusPassengerId implements Serializable {
        private Long bonus;
        private Long passenger;
    }
}