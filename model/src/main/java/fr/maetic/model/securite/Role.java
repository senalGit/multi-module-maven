package fr.maetic.model.securite;


import fr.maetic.enumeration.EnumRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generateur_sequence")
    @SequenceGenerator(name = "role_generateur_sequence", sequenceName = "role_sequence", allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnumRole roleName;
}
