package com.dipi.machineleaseservice.repository;

import com.dipi.machineleaseservice.model.LeasePayments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeasePaymentsRepository extends JpaRepository<LeasePayments, Long> {
}
