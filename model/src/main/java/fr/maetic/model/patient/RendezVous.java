package fr.maetic.model.patient;

import fr.maetic.enumeration.EnumStatutRendezVous;
import fr.maetic.model.praticien.Praticien;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter @ToString
public class RendezVous implements Serializable {

	@Serial
    private static final long serialVersionUID = 387959997589565126L;

	@Id
	private long id;
    private String title;
    private String description;

    @Column(name = "start_time")
    private LocalDateTime start;

    @Column(name = "end_time")
    private LocalDateTime end;


    @ManyToOne @JoinColumn(name = "praticien_id")
    private Praticien praticien;

    @Enumerated(EnumType.STRING)
    private EnumStatutRendezVous statutRendezVousEnum;

     @ManyToOne @JoinColumn(name = "suivi_id")
     private Suivi suivi;
}
