package com.dipi.machineleaseservice.dto;

import com.dipi.machineleaseservice.model.Lease;

import java.time.LocalDate;

public record LeaseTermsDto(
        Long termId,
        Lease lease,
        String termType,
        LocalDate startDate,
        LocalDate endDate,
        Integer durationMonths,
        String description
) {
}
