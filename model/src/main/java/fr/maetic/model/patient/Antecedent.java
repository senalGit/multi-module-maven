package fr.maetic.model.patient;

import fr.maetic.enumeration.EnumTypeAntecedent;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Antecedent implements Serializable {
    @Serial
    private static final long serialVersionUID = 5795971642690746458L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "antecedent_id", nullable = false)
    private Long antecedent_id;
    private String libelleAntecedent;
    private String detailAntecedent;
    private String autreObservation;
    @Enumerated(EnumType.STRING)
    private EnumTypeAntecedent typeAntecedent;
    private Long patientId;
    private Double poidsPatient;
    private String tensionPatient;
    private Integer tensionDiastole;
    private Integer tensionSystole;
    private Integer bruitCoeurPatient;
}