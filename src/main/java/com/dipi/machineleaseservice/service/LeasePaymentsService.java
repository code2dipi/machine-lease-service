package com.dipi.machineleaseservice.service;

import com.dipi.machineleaseservice.dto.LeasePaymentsDto;
import com.dipi.machineleaseservice.model.Lease;
import com.dipi.machineleaseservice.model.LeasePayments;
import com.dipi.machineleaseservice.repository.LeasePaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeasePaymentsService {

    @Autowired
    private LeasePaymentsRepository leasePaymentsRepository;

    @Transactional
    public LeasePayments createLeasePaymentsForLease(Lease lease) {
        LeasePayments leasePayments = new LeasePayments();
        leasePayments.setLease(lease);
        return leasePaymentsRepository.save(leasePayments);
    }

    @Transactional
    public void setOtherLeasePaymentsProperties(LeasePayments leasePayments, LeasePaymentsDto leasePaymentsDto) {
        // Set other properties of lease payments based on the DTO
        leasePayments.setPaymentDate(leasePaymentsDto.paymentDate());
        leasePayments.setAmountPaid(leasePaymentsDto.paymentAmount());
        // Save the updated lease payments entity
        leasePaymentsRepository.save(leasePayments);
    }


}
