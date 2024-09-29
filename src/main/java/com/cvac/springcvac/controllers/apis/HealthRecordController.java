package com.cvac.springcvac.controllers.apis;

import com.cvac.springcvac.models.HealthRecord;
import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.repositories.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/api/health-record")
@CrossOrigin(origins = "http://localhost:63342")
public class HealthRecordController {

    private final HealthRecordRepository healthRecordRepository;

    @Autowired
    public HealthRecordController(HealthRecordRepository healthRecordRepository) {
        this.healthRecordRepository = healthRecordRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addHealthRecord(@RequestBody HealthRecord healthRecord) {
        try {
            if (Objects.isNull(healthRecord)) {
                throw new RuntimeException("Health Record cannot be null");
            }

            healthRecordRepository.save(healthRecord);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("/get-by-patient")
    public ResponseEntity<Object> getHealthRecordsByPatient(@RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(healthRecordRepository.findByPatient(patient));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
