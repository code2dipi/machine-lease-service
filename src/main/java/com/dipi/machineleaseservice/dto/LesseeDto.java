package com.dipi.machineleaseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LesseeDto {
    private String companyName;
    private String lesseeName;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private long taxIdNumber;
    private String accountRepresentative;
    private String financialInstitution;
}
