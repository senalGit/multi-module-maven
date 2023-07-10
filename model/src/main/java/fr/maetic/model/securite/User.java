package fr.maetic.model.securite;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.SEQUENCE;

@SuperBuilder
@Setter
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_generateur_sequence")
    @SequenceGenerator(name = "user_generateur_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
    @Column(unique = true, length = 100)
    private String email;
    @Column(length = 50)
    private String prenom;
    @Column(length = 50)
    private String nom;
    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Collection<Role> roles = new ArrayList<>();
    private boolean isActive;

    public User(String username, String password, Collection<Role> roles, boolean isActive) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.isActive = isActive;
    }
}
