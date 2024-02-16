package com.dipi.machineleaseservice.service;

import com.dipi.machineleaseservice.dto.LeaseDto;
import com.dipi.machineleaseservice.dto.LeasePaymentsDto;
import com.dipi.machineleaseservice.dto.LeaseTermsDto;
import com.dipi.machineleaseservice.model.*;
import com.dipi.machineleaseservice.repository.LeasePaymentsRepository;
import com.dipi.machineleaseservice.repository.LeaseRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LeaseService {
    private static final Logger LOG= LoggerFactory.getLogger(LeaseService.class);
    private final LeaseRepository leaseRepository;
    private final EquipmentService equipmentService;
    private final LesseeService leeseService;
    private final LeaseTermsService leaseTermsService;
    private final LeasePaymentsService leasePaymentsService;

    public LeaseService(LeaseRepository leaseRepository, EquipmentService equipmentService, LesseeService leeseService, LeaseTermsService leaseTermsService, LeasePaymentsRepository leasePaymentsRepository, LeasePaymentsService leasePaymentsService) {
        this.leaseRepository = leaseRepository;
        this.equipmentService = equipmentService;
        this.leeseService = leeseService;
        this.leaseTermsService = leaseTermsService;
        this.leasePaymentsService = leasePaymentsService;
    }

    @Transactional
    public Lease createLeaseWithPaymentsAndTerms(LeaseDto dto, LeasePaymentsDto leasePaymentsDto, LeaseTermsDto leaseTermsDto) throws IOException {

      Lease lease=new Lease();
      Equipment equipment= equipmentService.getEquipmentById(dto.equipmentId());
      Lessee lessee= leeseService.getLesseeById(dto.lesseeId());

      if(equipment==null){
          throw new IllegalArgumentException("Equipment not found with id: " + dto.equipmentId());
      }
      if(lessee==null){
          throw new IllegalArgumentException("Lessee not found with id: " + dto.lesseeId());
      }

      lease.setEquipment(equipment);
      lease.setLessee(lessee);
      lease.setLeaseStartDate(dto.leaseStartDate());
      lease.setLeaseEndDate(dto.leaseEndDate());
      lease.setMonthlyRent(dto.monthlyRent());
      lease.setLeaseStatus(dto.leaseStatus());
      lease= leaseRepository.save(lease);

      //create Lease payments for the lease

     LeasePayments leasePayments= leasePaymentsService.createLeasePaymentsForLease(lease);
     leasePaymentsService.setOtherLeasePaymentsProperties(leasePayments, leasePaymentsDto);

     //create lease terms for the lease

     LeaseTerms leaseTerms= leaseTermsService.createLeaseTermsForLease(lease);
     leaseTermsService.setOtherLeaseTermsProperties(leaseTerms, leaseTermsDto);

      return lease;
    }

}
