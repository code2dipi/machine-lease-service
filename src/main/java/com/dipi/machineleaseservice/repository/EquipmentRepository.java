package com.dipi.machineleaseservice.repository;

import com.dipi.machineleaseservice.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "equipments", path = "equipments")

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
