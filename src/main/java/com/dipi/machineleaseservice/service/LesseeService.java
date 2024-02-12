package com.dipi.machineleaseservice.service;

import com.dipi.machineleaseservice.dto.LesseeDto;
import com.dipi.machineleaseservice.model.Lessee;
import com.dipi.machineleaseservice.repository.EquipmentRepository;
import com.dipi.machineleaseservice.repository.LesseeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LesseeService {
    private final LesseeRepository leseeRepository;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public LesseeService(LesseeRepository leseeRepository,  EquipmentRepository equipmentRepository) {
        this.leseeRepository = leseeRepository;
        this.equipmentRepository = equipmentRepository;
    }
    public List<Lessee> getAllLessees() {
        return leseeRepository.findAll();

    }

    public Lessee getLesseeById(Long id) {
        return leseeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Lessee not found with this id: " + id));
    }

    public Lessee getLesseeByName(String name){
        return leseeRepository.findBylesseeName(name).orElseThrow(()->new IllegalArgumentException("Lessee not found with this name: " + name));
    }

    public Lessee createLessee(LesseeDto lesseeDto) {
         Lessee lessee=convertLeesseDtoIntoEntity(lesseeDto);
       return leseeRepository.save(lessee);
    }
    public Lessee updateLessee(Long lesseeId, LesseeDto lesseeDto) {
        Optional<Lessee> optionalLessee = leseeRepository.findById(lesseeId);
        if(optionalLessee.isPresent()){
            final Lessee lessee = getLessee(lesseeDto, optionalLessee);
            return leseeRepository.save(lessee);
        }else{
            throw new IllegalArgumentException("Lessee not found with this id: " + lesseeId);
        }
    }

    private static Lessee getLessee(LesseeDto lesseeDto, Optional<Lessee> optionalLessee) {
        Lessee lessee = optionalLessee.get();
        lessee.setCompanyName(lesseeDto.getCompanyName());
        lessee.setLesseeName(lesseeDto.getLesseeName());
        lessee.setContactPerson(lesseeDto.getContactPerson());
        lessee.setContactEmail(lesseeDto.getContactEmail());
        lessee.setContactPhone(lesseeDto.getContactPhone());
        lessee.setAddress(lesseeDto.getAddress());
        lessee.setTaxIdNumber(lesseeDto.getTaxIdNumber());
        lessee.setAccountRepresentative(lesseeDto.getAccountRepresentative());
        return lessee;
    }

    private Lessee convertLeesseDtoIntoEntity(LesseeDto lesseeDto) {
        try{
            if(lesseeDto!=null){
                Lessee lessee = new Lessee();
                lessee.setCompanyName(lesseeDto.getCompanyName());
                lessee.setLesseeName(lesseeDto.getLesseeName());
                lessee.setContactPerson(lesseeDto.getContactPerson());
                lessee.setContactEmail(lesseeDto.getContactEmail());
                lessee.setContactPhone(lesseeDto.getContactPhone());
                lessee.setAddress(lesseeDto.getAddress());
                lessee.setTaxIdNumber(lesseeDto.getTaxIdNumber());
                lessee.setAccountRepresentative(lesseeDto.getAccountRepresentative());
                lesseeDto.setFinancialInstitution(lesseeDto.getFinancialInstitution());
                return lessee;
            }
        }catch(Exception e){ e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public void deleteLesseeAndCorrespondingEquipmentReferences(Long lesseeId) {
        Optional<Lessee> lessee = leseeRepository.findById(lesseeId);
        lessee.ifPresent(l -> {
            // Remove the references to the Lessee from associated Equipment entities using lambda expression

            l.getLeasedEquipments().forEach(e -> {
                e.setLessee(null);
                equipmentRepository.save(e);
            });
            // Delete the Lessee
            leseeRepository.delete(l);
        });
    }
}

