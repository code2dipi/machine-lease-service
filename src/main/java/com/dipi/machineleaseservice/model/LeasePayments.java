package com.dipi.machineleaseservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lease_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeasePayments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne()
    @JoinColumn(name = "lease_id")
    private Lease lease;

    private LocalDate paymentDate;

    private BigDecimal amountPaid; // total amount paid

}

