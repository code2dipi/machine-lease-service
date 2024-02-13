package com.dipi.machineleaseservice.service;

import com.dipi.machineleaseservice.dto.EquipmentDto;
import com.dipi.machineleaseservice.exception.BusinessException;
import com.dipi.machineleaseservice.exception.EquipmentException;
import com.dipi.machineleaseservice.exception.LesseeNotFoundException;
import com.dipi.machineleaseservice.model.Equipment;
import com.dipi.machineleaseservice.model.Lessee;
import com.dipi.machineleaseservice.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final LesseeService lesseeService;
    private static final Logger LOG = LoggerFactory.getLogger(EquipmentService.class);


    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository, LesseeService lesseeService) {
        this.equipmentRepository = equipmentRepository;
        this.lesseeService = lesseeService;
    }




    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Equipment createEquipment(EquipmentDto equipmentDto) {
        try {
            Equipment equipment = new Equipment();

            // Map equipmentDto properties to equipment
            mapEquipmentDtoToEquipment(equipment, equipmentDto);

            // Set Lessee for Equipment based on Lessee ID

            Lessee lessee = lesseeService.getLesseeByName(equipmentDto.getLesseeName());
            if (lessee == null) {
                throw new LesseeNotFoundException("Lessee not found with name: " + equipmentDto.getLesseeName());
            }
            equipment.setLessee(lessee);
            return equipmentRepository.save(equipment);

        } catch (Exception e) {
            LOG.error("Error occured while creating equipment", e.getMessage());
            throw new EquipmentException("Failed to create equipment: " + e);
        }

    }

    @Transactional
    public Equipment updateEquipment(Long id, EquipmentDto equipmentDto) {
        try {
            Equipment existingEquipment = equipmentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Equipment not found with id: " + id));

            // Map equipmentDto properties to existingEquipment
            mapEquipmentDtoToEquipment(existingEquipment, equipmentDto);

            // Set Lessee for Equipment based on Lessee ID
            Lessee lessee = lesseeService.getLesseeById(equipmentDto.getLesseeId());
            existingEquipment.setLessee(lessee);

            return equipmentRepository.save(existingEquipment);
        } catch (Exception e) {
            LOG.info("An error occurred while updating equipment: " + e.getMessage());
            throw new EquipmentException("Failed to update equipment with id: " + id);
        }
    }

    // Delete an Equipment by ID

    @Deprecated
    public void deleteEquipment(Long id) {
        // Check if the Equipment exists
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
        if (equipmentOptional.isPresent()) {
            Equipment equipment = equipmentOptional.get();

            // Perform any necessary pre-delete validations
            if (isEquipmentDeletionAllowed(equipment)) {
                // Delete the Equipment from the database
                equipmentRepository.delete(equipment);
            } else {
                throw new BusinessException("Equipment deletion is not allowed due to ongoing leases or other conditions.");
            }
        } else {
            // Handle the case where the Equipment does not exist
            throw new EntityNotFoundException("Equipment not found with id: " + id);
        }
    }

    public void deleteEquipmentAndDetachLesseeReference(Long equipmentId) {
        if (!equipmentRepository.existsById(equipmentId)) {
            throw new EntityNotFoundException("Equipment not found with id: " + equipmentId);
        }
        // equipmentRepository.detachLesseeReference(equipmentId);
        // equipmentRepository.deleteById(equipmentId);
        equipmentRepository.deleteEquipmentAndDetachLesseeReference(equipmentId);
    }

    @Deprecated
    private boolean isEquipmentDeletionAllowed(Equipment equipment) {
        // Example: Check if the equipment is not currently leased
        return equipment.getLessee() == null;
    }

    private void mapEquipmentDtoToEquipment(Equipment equipment, EquipmentDto equipmentDto) {
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
