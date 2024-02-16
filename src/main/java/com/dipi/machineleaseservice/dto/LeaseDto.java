package com.dipi.machineleaseservice.dto;

import com.dipi.machineleaseservice.model.LeaseStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;


public record LeaseDto(Long leaseId, Long equipmentId, Long lesseeId, LocalDate leaseStartDate, LocalDate leaseEndDate,
                       BigDecimal monthlyRent, LeaseStatus leaseStatus) {
}
