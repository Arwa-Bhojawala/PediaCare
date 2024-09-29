package com.cvac.springcvac.controllers.apis;

import com.cvac.springcvac.models.HealthRecord;
import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.models.VaccineRecord;
import com.cvac.springcvac.repositories.HealthRecordRepository;
import com.cvac.springcvac.repositories.VaccineRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/api/vaccine-record")
@CrossOrigin(origins = "http://localhost:63342")
public class VaccineRecordController {
    private final VaccineRecordRepository vaccineRecordRepository;

    @Autowired
    public VaccineRecordController(VaccineRecordRepository vaccineRecordRepository) {
        this.vaccineRecordRepository = vaccineRecordRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addVaccineRecord(@RequestBody VaccineRecord vaccineRecord) {
        try {
            if (Objects.isNull(vaccineRecord)) {
                throw new RuntimeException("Health Record cannot be null");
            }

            vaccineRecordRepository.save(vaccineRecord);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("/get-by-patient")
    public ResponseEntity<Object> getVaccineRecordsByPatient(@RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(vaccineRecordRepository.findByPatient(patient));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
