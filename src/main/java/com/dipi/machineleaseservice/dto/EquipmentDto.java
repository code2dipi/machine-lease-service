package com.dipi.machineleaseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {
    private String equipmentName;
    private String manufacturer;
    private String modelNumber;
    private String serialNumber;
    private LocalDate purchaseDate;
    private Double price;
    private String category;
    private String description;

    private Long lesseeId; // The ID of the Lessee associated with the Equipment
}
