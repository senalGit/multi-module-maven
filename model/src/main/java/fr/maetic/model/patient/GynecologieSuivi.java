package fr.maetic.model.patient;

import jakarta.persistence.Entity;
import lombok.*;
import java.io.Serial;
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class GynecologieSuivi extends Suivi {
    @Serial
    private static final long serialVersionUID = -1388736819062283218L;
    private String contraception;
}