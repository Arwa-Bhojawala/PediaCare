package com.cvac.springcvac.repositories;

import com.cvac.springcvac.models.HealthRecord;
import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.models.PatientInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthRecordRepository extends CrudRepository<HealthRecord, String> {
    @Query("SELECT hr FROM HealthRecord hr WHERE hr.patient = :patient")
    List<HealthRecord> findByPatient(@Param("patient") Patient patient);
}
