package com.dipi.machineleaseservice.repository;

import com.dipi.machineleaseservice.model.LeaseTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseTermsRepository extends JpaRepository<LeaseTerms, Long> {
}
