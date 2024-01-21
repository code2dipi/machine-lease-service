package com.dipi.machineleaseservice.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name="equipment")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long equipmentId;


    private String equipmentName="test";


    private String manufacturer;


    private String modelNumber;

    private String serialNumber;
    private LocalDate purchaseDate;
    private Double price;
    private String category;
    private String description;

    @ManyToOne
    @JoinColumn(name="lessee_id")
    private Lessee lessee;
}


