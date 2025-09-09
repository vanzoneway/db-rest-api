package by.vanzoneway.dbrestapi.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "driver_rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "moderation_status")
    private String moderationStatus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
}