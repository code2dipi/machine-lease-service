package com.dipi.machineleaseservice.repository;

import com.dipi.machineleaseservice.model.Equipment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "equipments", path = "equipments")

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Equipment  e SET e.lessee=null WHERE e.equipmentId  = :equipmentId")
    void detachLesseeReference(Long equipmentId);

   @Override
    void deleteById(Long equipmentId);
    // Custom delete method that detaches the reference before deletion
    default void deleteEquipmentAndDetachLesseeReference(Long equipmentId){
        detachLesseeReference(equipmentId);
        deleteById(equipmentId);
    }

}
