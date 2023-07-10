package fr.maetic.controller.securite;/* package fr.maia.api_patient.securite.controller;

import fr.maia.api_patient.entities.Patient;
import fr.maia.api_patient.securite.entities.AppUser;
import fr.maia.api_patient.securite.repository.UserRepository;
import fr.maia.api_patient.service.interfaces.PatientService;
import fr.maia.api_patient.service.interfaces.SuiviService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    private final PatientService patientService;
    private final SuiviService suiviService;

@PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public List<AppUser> user(){
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/patients")
    public List<Patient> admin(){
        return patientService.findAllPatients();
    }
} */
