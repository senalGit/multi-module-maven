package fr.maetic.dto.securite;

import fr.maetic.model.securite.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String prenom;
    private String nom;
    private Collection<Role> roles;
    private boolean isActive;

}