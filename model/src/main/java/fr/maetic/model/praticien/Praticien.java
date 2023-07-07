package fr.maetic.model.praticien;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Praticien implements Serializable {
public static final long serialVersionUID = 7155176596680799677L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long praticienId;
    private String nomPraticien;
    private String prenomPraticien;
    private LocalDate dateCreation;
    private String codePraticien;

    // @ElementCollection
    // @Column(name = "specialite")
    // private Collection<String> specialite = new ArrayList<>();
}
