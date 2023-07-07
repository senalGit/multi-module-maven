package fr.maetic.service.patient;


import fr.maetic.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient save(Patient patient);

    List<Patient> saveAllPatient(List<Patient> patients);

    Patient update(Patient patient);

    Optional<Patient> find(Long id);

    List<Patient> findAllPatients();

    List<Patient> findAll(Sort sort);

    Page<Patient> findAll(Pageable pageable);
    Page<Patient> getPagePatients(String nom, int page, int size);

    void delete(Long id);

    void delete(Patient patient);

    void deleteAll();

    long count();

    Patient findPatientById(Long id);

}