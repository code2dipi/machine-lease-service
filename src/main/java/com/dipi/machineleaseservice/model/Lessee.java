package com.dipi.machineleaseservice.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="lessee")

public class Lessee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lesseeId;

    private String companyName;

    private String lesseeName;

    private String contactPerson;

    private String contactEmail;

    private String contactPhone;

    private String address;

    private long taxIdNumber;

    private String accountRepresentative;

    private String financialInstitution;

    // Establish One-to-Many relationship with Lease
    @OneToMany(mappedBy ="lessee",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Lease> leases = new ArrayList<>();

}
