package com.dipi.machineleaseservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="lessee_id",referencedColumnName = "lesseeId",updatable = true)

    //@OnDelete(action = OnDeleteAction.CASCADE)
   // @JsonIgnore
    private Lessee lessee;


}


