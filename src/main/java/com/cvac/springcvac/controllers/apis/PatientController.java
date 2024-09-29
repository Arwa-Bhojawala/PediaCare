package com.cvac.springcvac.controllers.apis;

import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.models.PatientInfo;
import com.cvac.springcvac.repositories.PatientInfoRepository;
import com.cvac.springcvac.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/api/patient")
@CrossOrigin(origins = "http://localhost:63342")
public class PatientController {

    private final PatientInfoRepository patientInfoRepository;

    @Autowired
    public PatientController(PatientInfoRepository patientInfoRepository) {
        this.patientInfoRepository = patientInfoRepository;
    }

    @PostMapping("/add-data")
    public ResponseEntity<Object> addPatient(@RequestBody PatientInfo patientInfo) {
        try {
            if (Objects.isNull(patientInfo)) {
                throw new RuntimeException("Request payload cannot be null");
            }

            Optional<PatientInfo> patientInfoOptional = patientInfoRepository.findByPatient(patientInfo.getPatient());

            patientInfoOptional.ifPresent(info -> patientInfo.setPatientInfoId(info.getPatientInfoId()));

            patientInfoRepository.save(patientInfo);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("/get-patient-info")
    public ResponseEntity<Object> getPatientInfo(@RequestBody Patient patient) {
        try {
            Optional<PatientInfo> patientInfo = patientInfoRepository.findByPatient(patient);

            return ResponseEntity.ok(patientInfo.orElse(BaseUtils.defaultPatientInfo(patient)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Collection<PatientInfo>> getAllPatients() {
        return ResponseEntity.ok(StreamSupport.stream(patientInfoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()));
    }
}
