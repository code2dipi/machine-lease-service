package com.dipi.machineleaseservice.repository;

import com.dipi.machineleaseservice.model.Lessee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "lessees", path = "lessees")

public interface LesseeRepository extends JpaRepository<Lessee, Long> {

   //@Query("SELECT l FROM Lessee l WHERE l.lesseeName = ?1")
   Optional<Lessee>  findBylesseeName(String lesseeName);

}
