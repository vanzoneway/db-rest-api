package by.vanzoneway.dbrestapi.core.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "average_rating")
    private Integer averageRating;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "driver_license_category")
    private String driverLicenseCategory;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @Builder.Default
    private List<DriverRating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Ride> rides = new ArrayList<>();
}
