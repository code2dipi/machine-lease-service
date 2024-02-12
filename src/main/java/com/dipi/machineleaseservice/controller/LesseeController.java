package com.dipi.machineleaseservice.controller;

import com.dipi.machineleaseservice.dto.LesseeDto;
import com.dipi.machineleaseservice.model.Lessee;
import com.dipi.machineleaseservice.service.LesseeService;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessees")
public class LesseeController {
    @Autowired
    private LesseeService lesseeService;
    private static final Logger LOG = LoggerFactory.getLogger(LesseeController.class);

    // Create a new Lessee
    @PostMapping
    public ResponseEntity<Lessee> createLessee(@RequestBody LesseeDto lesseeDto) {
        Lessee createdLessee = lesseeService.createLessee(lesseeDto);
        return new ResponseEntity<>(createdLessee, HttpStatus.CREATED);
    }

    // Get all Lessees
    @GetMapping
    public ResponseEntity<List<Lessee>> getAllLessees() {
        List<Lessee> lessees = lesseeService.getAllLessees();
        return new ResponseEntity<>(lessees, HttpStatus.OK);
    }

    // Get a Lessee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Lessee> getLesseeById(@PathVariable Long id) {
        Lessee lessee = lesseeService.getLesseeById(id);
        return new ResponseEntity<>(lessee, HttpStatus.OK);
    }

    // Update a Lessee by ID
    @PutMapping("/{id}")
    public ResponseEntity<Lessee> updateLessee(@PathVariable Long id, @RequestBody LesseeDto lesseeDto) {

        try{
            Lessee updatedLessee = lesseeService.updateLessee(id, lesseeDto);
            return new ResponseEntity<>(updatedLessee, HttpStatus.OK);
        }catch(Exception e){
            LOG.warn("Failed to update" + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a Lessee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessee(@PathVariable Long id) {
        try{
            lesseeService.deleteLesseeAndCorrespondingEquipmentReferences(id);

        }catch(Exception e){
            LOG.warn("Failed to delete" + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
