package fr.maetic.model.patient;

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
public class Medicament implements Serializable {
    @Serial
    private static final long serialVersionUID = -5513997812048404108L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MedicamentId;

}