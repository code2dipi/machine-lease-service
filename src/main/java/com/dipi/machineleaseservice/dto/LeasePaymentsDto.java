package com.dipi.machineleaseservice.dto;

import com.dipi.machineleaseservice.model.Lease;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LeasePaymentsDto(
    Long paymentId,
    Lease lease,
    LocalDate paymentDate,
    BigDecimal paymentAmount) {
}
