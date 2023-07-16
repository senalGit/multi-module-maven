package fr.maetic.model.patient;

import fr.maetic.enumeration.EnumTypeSuivi;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;



import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("SUIVI")
public class Suivi implements Serializable {
    @Serial
    private static final long serialVersionUID = -5081769841994528492L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suiviId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate debutSuivi;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finSuivi;

    @Enumerated(EnumType.STRING)
    private EnumTypeSuivi typeSuivi;

    // @OneToMany(mappedBy = "suivi", fetch = FetchType.EAGER)
    // @JsonManagedReference
    // private List<RendezVous> rendezVousList;

    private Long patientId;

    private Double poidsPatient;
}