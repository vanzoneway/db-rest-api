package by.vanzoneway.dbrestapi.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bonus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToMany(mappedBy = "bonuses")
    @Builder.Default
    private List<Passenger> passengers = new ArrayList<>();
}