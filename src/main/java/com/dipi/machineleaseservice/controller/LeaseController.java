package com.dipi.machineleaseservice.controller;

import com.dipi.machineleaseservice.dto.LeaseDto;
import com.dipi.machineleaseservice.dto.LeasePaymentsDto;
import com.dipi.machineleaseservice.dto.LeaseTermsDto;
import com.dipi.machineleaseservice.model.Lease;
import com.dipi.machineleaseservice.service.EquipmentService;
import com.dipi.machineleaseservice.service.LeaseService;
import com.dipi.machineleaseservice.service.LesseeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/leases")
public class LeaseController {
    private static final Logger LOG= LoggerFactory.getLogger(LeaseController.class);

    private final LeaseService leaseService;

    @Autowired
    public LeaseController(LeaseService leaseService){
        this.leaseService = leaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<Lease> createLease(@RequestBody LeaseDto leaseDto, @RequestBody LeasePaymentsDto leasePaymentsDto, @RequestBody LeaseTermsDto leaseTermsDto) throws IOException {
        try{
            if(leaseDto!=null && leasePaymentsDto!=null && leaseTermsDto!=null){
                LOG.info("LeaseDto: " + leaseDto.toString());
                LOG.info("LeasePaymentsDto: " + leasePaymentsDto.toString());
                LOG.info("LeaseTermsDto: " + leaseTermsDto.toString());
                Lease lease = leaseService.createLeaseWithPaymentsAndTerms(leaseDto, leasePaymentsDto, leaseTermsDto);
                return new ResponseEntity<>(lease, HttpStatus.CREATED);
            }
        }catch(Exception e){
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
