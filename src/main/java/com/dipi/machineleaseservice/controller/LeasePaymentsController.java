package com.dipi.machineleaseservice.controller;

import com.dipi.machineleaseservice.dto.LeasePaymentsDto;
import com.dipi.machineleaseservice.model.Lease;
import com.dipi.machineleaseservice.model.LeasePayments;
import com.dipi.machineleaseservice.service.LeasePaymentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lease-payments")
public class LeasePaymentsController {
    private static final  Logger LOG= LoggerFactory.getLogger(LeasePaymentsController.class);

    @Autowired
    private LeasePaymentsService leasePaymentsService;


    @PostMapping("/create")
    public ResponseEntity<LeasePayments> createLeasePayments(@RequestBody LeasePaymentsDto leasePaymentsDto) {
        try{
            if(leasePaymentsDto==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            LOG.info("LeasePaymentsDto: " + leasePaymentsDto.toString());
            LeasePayments leasePayments = leasePaymentsService.createLeasePaymentsForLease(leasePaymentsDto.lease());
            leasePaymentsService.setOtherLeasePaymentsProperties(leasePayments, leasePaymentsDto);
            return new ResponseEntity<>(leasePayments, HttpStatus.CREATED);

        }catch(Exception e){
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    }


