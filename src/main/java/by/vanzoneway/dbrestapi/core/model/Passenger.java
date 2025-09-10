package by.vanzoneway.dbrestapi.core.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "average_rating")
    private Integer averageRating;

    @OneToOne(mappedBy = "passenger", cascade = CascadeType.ALL)
    private PassengerCredentials credentials;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PassengerRating> ratings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "bonus_passenger",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id")
    )
    @Builder.Default
    private List<Bonus> bonuses = new ArrayList<>();

    @OneToMany(mappedBy = "passenger", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Builder.Default
    private List<Ride> rides = new ArrayList<>();
}