package com.dipi.machineleaseservice.controller;

import com.dipi.machineleaseservice.dto.EquipmentDto;
import com.dipi.machineleaseservice.model.Equipment;
import com.dipi.machineleaseservice.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController

@RequestMapping("api/equipments")
public class EquipmentController {
    private static final Logger LOG = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    private EquipmentService equipmentService;

    // create new equipment

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody EquipmentDto equipmentDto) throws Exception {
       try {
           Equipment createdEquipment = equipmentService.createEquipment(equipmentDto);
           if (createdEquipment != null) {
               return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
           }
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }catch(Exception e){
           LOG.error("Error occured while creating equipment", e);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id){
        Equipment equipment=equipmentService.getEquipmentById(id);
        return equipment!=null? new ResponseEntity<>(equipment, HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> allEquipment = equipmentService.getAllEquipments();
        return new ResponseEntity<>(allEquipment, HttpStatus.OK);}

    @PutMapping("/{id}")

    public ResponseEntity<Equipment> updateEquipmentById(@PathVariable Long id ,@RequestBody EquipmentDto equipmentDto) throws Exception {
        Equipment updatedEquipment = equipmentService.updateEquipment(id, equipmentDto);
        return updatedEquipment != null ? new ResponseEntity<>(updatedEquipment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipmentAndDetachLesseeReference(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
