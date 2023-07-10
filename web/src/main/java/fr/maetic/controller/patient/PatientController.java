package fr.maetic.controller.patient;


import fr.maetic.dto.HttpResponse;
import fr.maetic.model.patient.Patient;
import fr.maetic.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/patient/")
public class PatientController {
    private final PatientService patientService;

    @PostMapping("nouveau")
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED);
    }

@PostMapping("newlist")
public ResponseEntity<List<Patient>> saveALlEntity(@RequestBody List<Patient> patients ) {
    return new ResponseEntity<List<Patient>>(patientService.saveAllPatient(patients), HttpStatus.CREATED);
    
}

    @PutMapping("modifier")
    public ResponseEntity<Patient> update(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Long id) {
        Patient patient = patientService.findPatientById(id);
        return new ResponseEntity<>(patient, OK);
    }

    @GetMapping("liste")
    public ResponseEntity<List<Patient>> getAllPatient() {
        List<Patient> patients = patientService.findAllPatients();
        return new ResponseEntity<>(patients, OK);
    }

    @GetMapping("patients")
    public ResponseEntity<HttpResponse> getAllPatients() {
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .donnees(of("liste", patientService.findAllPatients()))
                        .message("Liste des patients récupérés")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());

    }

    @GetMapping("page/liste")
    public ResponseEntity<HttpResponse> getPagePatients(@RequestParam(defaultValue = "") Optional<String> name,
            @RequestParam(defaultValue = "0") Optional<Integer> page,
            @RequestParam(defaultValue = "5") Optional<Integer> size) {
        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .donnees(
                                of("page",
                                        patientService.getPagePatients(
                                                name.orElse(""),
                                                page.orElse(0),
                                                size.orElse(10))))
                        .message("Liste des patients récupérés")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());

    }

    @DeleteMapping("supprimer/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        patientService.delete(id);
    }

    // @DeleteMapping("supprimer/{patient}")
    // public void deletePatient(@PathVariable(value = "patient") Patient patient) {
    // patientService.delete(patient);
    // }

    // @DeleteMapping("supprimer/patients")
    // public void deleteAll() {
    // patientService.deleteAll();
    // }

    @GetMapping("total")
    public long total() {
        return patientService.count();
    }
}