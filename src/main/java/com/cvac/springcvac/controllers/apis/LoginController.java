package com.cvac.springcvac.controllers.apis;

import com.cvac.springcvac.auth.AuthUtils;
import com.cvac.springcvac.models.Cookies;
import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.models.enums.LoginStatus;
import com.cvac.springcvac.models.rest.AuthRequestPayload;
import com.cvac.springcvac.models.rest.LoginResponse;
import com.cvac.springcvac.repositories.CookieRepository;
import com.cvac.springcvac.repositories.PatientRepository;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:63342")
public class LoginController {

    private final PatientRepository patientRepository;
    private final CookieRepository cookieRepository;

    @Autowired
    public LoginController(PatientRepository patientRepository, CookieRepository cookieRepository) {
        this.patientRepository = patientRepository;
        this.cookieRepository = cookieRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody AuthRequestPayload request) {
        try {
            if (StringUtils.isNullOrEmpty(request.getUsername()) || StringUtils.isNullOrEmpty(request.getPassword())) {
                throw new RuntimeException("Patient username/password cannot be empty");
            }

            Optional<Patient> existingPatient = patientRepository.findById(request.getUsername());

            if (existingPatient.isPresent()) {
                throw new RuntimeException("Username should be unique");
            }

            Patient patient = new Patient();
            patient.setUsername(request.getUsername());

            patient.setHashedPassword(Optional.ofNullable(AuthUtils.hashPassword(request.getPassword()))
                    .orElseThrow(() -> new RuntimeException("Error occurred with hashing the password.")));

            patientRepository.save(patient);
            return ResponseEntity.ok().build();

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("/login-user")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequestPayload request) {
        LoginResponse loginResponse = new LoginResponse();
        try {

            if (StringUtils.isNullOrEmpty(request.getUsername()) || StringUtils.isNullOrEmpty(request.getPassword())) {
                throw new RuntimeException("Patient username/password cannot be empty");
            }

            Optional<Patient> patient = patientRepository.findById(request.getUsername());

            if (!patient.isPresent()) {
                throw new RuntimeException("User does not exist.");
            }

            if (!patient.get().getHashedPassword().equals(AuthUtils.hashPassword(request.getPassword()))) {
                throw new RuntimeException("Incorrect password.");
            }

            Cookies cookie = new Cookies();
            cookie.setPatient(patient.get());
            cookieRepository.save(cookie);

            loginResponse.setCookie(cookie.getCookie());
            loginResponse.setStatus(LoginStatus.SUCCESS);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception ex) {
            loginResponse.setStatus(LoginStatus.FAILURE);
            loginResponse.setMsg(ex.getMessage());
            return ResponseEntity.internalServerError().body(loginResponse);
        }
    }

    @GetMapping("/get-user/{cookie}")
    public ResponseEntity<Object> getPatient(@PathVariable("cookie") String cookie) {
        Patient patient = cookieRepository.findById(cookie).map(Cookies::getPatient).orElse(null);

        if (Objects.isNull(patient)) {
            return ResponseEntity.internalServerError().body(null);
        }

        return ResponseEntity.ok(patient);
    }
}
