package by.vanzoneway.dbrestapi.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "car_insurance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insurance_company")
    private String insuranceCompany;

    @Column(name = "expiration_period")
    private LocalDate expirationPeriod;

    @Column(name = "coverage_terms")
    private String coverageTerms;

    @Column(name = "insurance_premium")
    private Double insurancePremium;

    @OneToOne
    @JoinColumn(name = "car_id", unique = true)
    private Car car;
}