package fr.maetic.model.patient;

import fr.maetic.enumeration.EnumTypeAnalyseMedicale;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class InformationMedicale implements Serializable {
    @Serial
    private static final long serialVersionUID = 7855522066173175866L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "infoMedical_id", nullable = false)
    private Long informationMedicaleId;
    private LocalDateTime dateAnalyse;
    @Enumerated(EnumType.STRING)
    private EnumTypeAnalyseMedicale analyseMedicale;
    private String analyseMedicaleLibelle;
    private Long patientId;
}