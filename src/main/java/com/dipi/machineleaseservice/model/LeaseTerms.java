package com.dipi.machineleaseservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name="lease_terms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @ManyToOne
    @JoinColumn(name="lease_id")
    private Lease lease;

    private String termType;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer durationMonths;

    private String description;
}
