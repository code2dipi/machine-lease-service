package com.dipi.machineleaseservice.controller;

import com.dipi.machineleaseservice.dto.EquipmentDto;
import com.dipi.machineleaseservice.model.Equipment;
import com.dipi.machineleaseservice.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

@RequestMapping("api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    // create new equipment

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody EquipmentDto equipmentDto) throws Exception {
        Equipment createdEquipment = equipmentService.createEquipment(equipmentDto);
        return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
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
        return ResponseEntity.ok().body(allEquipment);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Equipment> updateEquipmentById(@PathVariable Long id ,@RequestBody EquipmentDto equipmentDto) throws Exception {
        Equipment updatedEquipment = equipmentService.updateEquipment(id, equipmentDto);
        return updatedEquipment != null ? new ResponseEntity<>(updatedEquipment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
