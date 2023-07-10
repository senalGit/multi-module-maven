package fr.maetic.model.patient;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Consultation implements Serializable {
    @Serial
    private static final long serialVersionUID = -8857762620069621965L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    private LocalDate dateConsultation;
    private String compteRendu;
    @ManyToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;
    @ManyToOne
    @JoinColumn(name = "suivi_id")
    private Suivi suivi;
}