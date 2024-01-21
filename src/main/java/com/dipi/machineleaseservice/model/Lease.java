package com.dipi.machineleaseservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="lease")

public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaseId;

    @ManyToOne()
    @JoinColumn(name="equipment_id")
    private Equipment equipment;

    @ManyToOne()
    @JoinColumn(name="lessee_id")
    private Lessee lessee;

    private LocalDate leaseStartDate;

    private LocalDate leaseEndDate;

    private BigDecimal monthlyRent;

    @Enumerated(EnumType.STRING)
    private LeaseStatus leaseStatus;

    //Establish One-to-One relationship with LeaseTerms
      @OneToMany(mappedBy ="lease",cascade = CascadeType.ALL)
      private List<LeaseTerms> leaseTerms = new ArrayList<>();

    //Establish One-to-Many relationship with LeasePayments
     @OneToMany(mappedBy ="lease",cascade = CascadeType.ALL)
     private List<LeasePayments> leasePayments = new ArrayList<>();
}
