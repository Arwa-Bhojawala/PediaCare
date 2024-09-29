package com.cvac.springcvac.repositories;

import com.cvac.springcvac.models.PendingVaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingVaccineRepository extends CrudRepository<PendingVaccine, String> {
}
