package fr.maetic.model.patient;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serial;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class GrossesseSuivi extends Suivi {
    @Serial
    private static final long serialVersionUID = 3156851796817248377L;
    private LocalDate dateDernierRegle;
    private LocalDate debutGrossesseEchographie;
    private LocalDate termePrevuAcouchement;
    private Integer parite; // nombre d'enfant né viable
    private Integer gestite; // Toute grossesse
    private boolean jumelaire;
    private String maternite;
    private boolean actuelle;
    private boolean aRisque;
}