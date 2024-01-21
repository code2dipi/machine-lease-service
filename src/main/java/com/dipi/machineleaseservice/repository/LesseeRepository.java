package com.dipi.machineleaseservice.repository;

import com.dipi.machineleaseservice.model.Lessee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "lessees", path = "lessees")

public interface LesseeRepository extends JpaRepository<Lessee, Long> {
}
