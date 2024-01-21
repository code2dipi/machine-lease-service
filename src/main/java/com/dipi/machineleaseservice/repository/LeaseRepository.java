package com.dipi.machineleaseservice.repository;

import com.dipi.machineleaseservice.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "leases", path = "Leases")

public interface LeaseRepository extends JpaRepository<Lease, Long> {
}
