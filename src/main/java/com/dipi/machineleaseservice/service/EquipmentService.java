package com.dipi.machineleaseservice.service;

import com.dipi.machineleaseservice.dto.EquipmentDto;
import com.dipi.machineleaseservice.model.Equipment;
import com.dipi.machineleaseservice.model.Lessee;
import com.dipi.machineleaseservice.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private LesseeService lesseeService; // Inject the LesseeService



    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public Equipment createEquipment(EquipmentDto equipmentDto) {
        Equipment equipment = new Equipment();
        // Map equipmentDto properties to equipment
        mapEquipmentDtoToEquipment(equipment,equipmentDto);
        System.out.println(equipment);
        // Set Lessee for Equipment based on Lessee ID
        Lessee lessee = lesseeService.getLesseeById(equipmentDto.getLesseeId());
        equipment.setLessee(lessee);
        return equipmentRepository.save(equipment);
    }
    public Equipment updateEquipment(Long id, EquipmentDto equipmentDto) {
        Equipment existingEquipment = equipmentRepository.findById(id).orElse(null);
        if (existingEquipment != null) {
            // Map equipmentDto properties to existingEquipment
            mapEquipmentDtoToEquipment(existingEquipment,equipmentDto);

            // Set Lessee for Equipment based on Lessee ID
            Lessee lessee = lesseeService.getLesseeById(equipmentDto.getLesseeId());
            existingEquipment.setLessee(lessee);

            return equipmentRepository.save(existingEquipment);
        }
        return null;
    }

    // Delete an Equipment by ID

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }

    private void mapEquipmentDtoToEquipment(Equipment equipment,EquipmentDto equipmentDto) {
        equipment.setEquipmentName(equipmentDto.getEquipmentName());
        equipment.setManufacturer(equipmentDto.getManufacturer());
        equipment.setModelNumber(equipmentDto.getModelNumber());
        equipment.setSerialNumber(equipmentDto.getSerialNumber());
        equipment.setPurchaseDate(equipmentDto.getPurchaseDate());
        equipment.setPrice(equipmentDto.getPrice());
        equipment.setCategory(equipmentDto.getCategory());
        equipment.setDescription(equipmentDto.getDescription());
    }


}
