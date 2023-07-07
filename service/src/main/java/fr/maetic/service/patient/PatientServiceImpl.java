package fr.maetic.service.patient;


import fr.maetic.dao.patient.PatientRepository;
import fr.maetic.exception.PatientNotFoundException;
import fr.maetic.model.patient.Patient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        String nom = String.format("%s %s", patient.getPrenomPatient(), patient.getNomPatient());
        patientRepository.findPatientByEmail(patient.getEmail())
                .ifPresentOrElse(p -> {
                            log.error("Le patient [ {} {} ] existe déjà. -> SAUVEGARDE ANNULEE.", p.getPrenomPatient(), p.getNomPatient());
                        },
                        () -> {
                            log.info("Il semble que le patient {} n'existe pas encore.", nom);
                            log.info("Sauvegarde du patient {}", nom);
                            patientRepository.save(patient);
                        });
        return patient;
    }
    
    @Override
    public List<Patient> saveAllPatient(List<Patient> patients) {
       return patientRepository.saveAll(patients);
    }

    @Override
    public Optional<Patient> find(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> findAll(Sort sort) {
        return patientRepository.findAll(sort);
    }

    @Override
    public Page<Patient> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Page<Patient> getPagePatients(String nom, int page, int size) {
        log.info("Chargement des patients de la page {} de taille {}", page, size);
        return patientRepository.findByNomPatientContainsIgnoreCase(nom, PageRequest.of(page, size));
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression du patient numero [ {} ]", id);
        patientRepository.deleteById(id);
    }

    @Override
    public void delete(Patient patient) {
        log.info("Suppression de [ {} {} ]", patient.getPrenomPatient(), patient.getNomPatient());
        patientRepository.delete(patient);
    }

    @Override
    public void deleteAll() {
        patientRepository.deleteAll();
    }

    @Override
    public long count() {
        return patientRepository.count();
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientRepository
                .findPatientByPatientId(id)
                .orElseThrow(() -> new PatientNotFoundException("Le patient n'existe pas"));
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @PostConstruct
    public void initPatient(){
        Patient awa = new Patient(null, "SARR", "Awa", "0769697602","senal@gmail.com","rue des meuniers", "Montbert", "44140","Gynéco","Nicolas", "Test","test", "Remi", LocalDate.of(1987, Month.JUNE,12),false,null,null,LocalDate.of(1987, Month.JUNE,12));


patientRepository.saveAll(Stream.of(awa).collect(Collectors.toList()));
    }
}