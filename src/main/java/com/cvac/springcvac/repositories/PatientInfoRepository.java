package com.cvac.springcvac.repositories;

import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.models.PatientInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientInfoRepository extends CrudRepository<PatientInfo, String> {

    @Query("SELECT pi FROM PatientInfo pi WHERE pi.patient = :patient")
    Optional<PatientInfo> findByPatient(@Param("patient") Patient patient);
}
