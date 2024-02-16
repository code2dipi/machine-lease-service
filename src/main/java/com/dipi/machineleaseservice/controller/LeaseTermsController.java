package com.dipi.machineleaseservice.controller;

import com.dipi.machineleaseservice.dto.LeaseTermsDto;
import com.dipi.machineleaseservice.model.LeaseTerms;
import com.dipi.machineleaseservice.service.LeaseTermsService;
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
@RequestMapping("api/lease-terms")
public class LeaseTermsController {
    private final Logger LOG = LoggerFactory.getLogger(LeaseTermsController.class);

    @Autowired
    private LeaseTermsService leaseTermsService;

    @PostMapping("/create")
    public ResponseEntity<LeaseTerms> createLeaseTerms(@RequestBody LeaseTermsDto leaseTermsDto) {
        try {
            if (leaseTermsDto !=null) {
                LOG.info("LeaseTermsDto: " + leaseTermsDto.toString());
                LeaseTerms leaseTerms = leaseTermsService.createLeaseTermsForLease(leaseTermsDto.lease());
                leaseTermsService.setOtherLeaseTermsProperties(leaseTerms, leaseTermsDto);
                return new ResponseEntity<>(leaseTerms, HttpStatus.CREATED);
            }

        }catch (Exception e) {
            LOG.error("Error while creating lease terms for lease: {}", e.getMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
