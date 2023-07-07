package fr.maetic.dao.patient;

import fr.maetic.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByPatientId(Long id);
    /*Chercher patient par son numéro de téléphone*/
    Page<Patient> findByTelephone(String telephone, Pageable pageable);

    //Chercher patient par son nom ou prénom
    Page<Patient> findByNomPatientIgnoreCaseOrPrenomPatientIgnoreCaseOrderByNomPatientAsc(String nomPatient, String prenomPatient, Pageable pageable);

    /*Chercher patient par son nom*/
    Page<Patient> findByNomPatientContainsIgnoreCase(String nomPatient, Pageable pageable);
    Page<Patient> findByNomPatientContaining(String name, Pageable pageable);

    Optional<Patient> findPatientByEmail(String email);
}