package com.dipi.machineleaseservice.service;

import com.dipi.machineleaseservice.dto.LesseeDto;
import com.dipi.machineleaseservice.model.Lessee;
import com.dipi.machineleaseservice.repository.LesseeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LesseeService {
    private final LesseeRepository leseeRepository;

    @Autowired
    public LesseeService(LesseeRepository leseeRepository) {
        this.leseeRepository = leseeRepository;
    }

    public List<Lessee> getAllLessees() {
        return leseeRepository.findAll();

    }

    public Lessee getLesseeById(Long id) {
        return leseeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Lessee not found with this id: " + id));
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

    public void deleteLessee(Long id) {
        leseeRepository.deleteById(id);

    }
}
