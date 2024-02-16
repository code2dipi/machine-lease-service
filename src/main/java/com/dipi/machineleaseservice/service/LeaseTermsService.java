package com.dipi.machineleaseservice.service;

import com.dipi.machineleaseservice.dto.LeaseTermsDto;
import com.dipi.machineleaseservice.model.Lease;
import com.dipi.machineleaseservice.model.LeaseTerms;
import com.dipi.machineleaseservice.repository.LeaseTermsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseTermsService {

    @Autowired
    private final LeaseTermsRepository leaseTermsRepository;

    public LeaseTermsService(LeaseTermsRepository leaseTermsRepository) {
        this.leaseTermsRepository = leaseTermsRepository;
    }

    @Transactional
    public LeaseTerms createLeaseTermsForLease(Lease lease) {
        LeaseTerms leaseTerms=new LeaseTerms();
        leaseTerms.setLease(lease);
        return leaseTermsRepository.save(leaseTerms);
    }
    @Transactional
    public void setOtherLeaseTermsProperties(LeaseTerms leaseTerms, LeaseTermsDto leaseTermsDto){
        leaseTerms.setTermType(leaseTermsDto.termType());
        leaseTerms.setStartDate(leaseTermsDto.startDate());
        leaseTerms.setEndDate(leaseTermsDto.endDate());
        leaseTerms.setDurationMonths(leaseTermsDto.durationMonths());
        leaseTerms.setDescription(leaseTermsDto.description());
        // Save updated lease terms properties
        leaseTermsRepository.save(leaseTerms);
    }

    }
