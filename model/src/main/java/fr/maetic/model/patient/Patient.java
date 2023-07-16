package fr.maetic.model.patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long patientId;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String nomPatient;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String prenomPatient;
    private String telephone;
    private String email;
    private String adresse;
    private String commune;
    private String codePostal;
    private String profession;
    private String prenomConjoint;
    private String nomConjoint;
    private String professionConjoint;
    private String medecinTraitant;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissancePatient;
    private boolean enceinte;
//    @Enumerated(EnumType.STRING)
//    private EnumGroupeRhesus groupeRhesus;
    private String groupeRhesusLibelle;
    private String urlImage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreationPatient;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Suivi> suivis = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Antecedent> antecedents = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<InformationMedicale> informationMedicales;

}
