package com.cvac.springcvac.repositories;

import com.cvac.springcvac.models.HealthRecord;
import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.models.VaccineRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRecordRepository extends CrudRepository<VaccineRecord, String> {
    @Query("SELECT vr FROM VaccineRecord vr WHERE vr.patient = :patient")
    List<VaccineRecord> findByPatient(@Param("patient") Patient patient);
}
