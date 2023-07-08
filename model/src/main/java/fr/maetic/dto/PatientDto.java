package fr.maetic.dto;

import fr.maetic.enumeration.EnumGroupeRhesus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link fr.maetic.model.patient.Patient} entity
 */
@Data
public class PatientDto implements Serializable {
    private final Long patientId;
    @NotEmpty
    @Size(min = 2, max = 50)
    private final String nomPatient;
    @NotEmpty
    @Size(min = 2, max = 50)
    private final String prenomPatient;
    private final String telephone;
    private final String email;
    private final String adresse;
    private final String commune;
    private final String codePostal;
    private final String profession;
    private final String prenomConjoint;
    private final String nomConjoint;
    private final String professionConjoint;
    private final String medecinTraitant;
    private final LocalDate dateNaissancePatient;
    private final boolean enceinte;
    private final EnumGroupeRhesus groupeRhesus;
    //private final List<Suivi> suivis;
    //private final List<Antecedent> antecedents;
    //private final List<InformationMedicale> informationMedicales;
}