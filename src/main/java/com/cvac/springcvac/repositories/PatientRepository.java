package com.cvac.springcvac.repositories;

import com.cvac.springcvac.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {
}
