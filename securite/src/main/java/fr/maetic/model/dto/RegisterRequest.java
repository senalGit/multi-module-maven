package fr.maetic.model.dto;

import fr.maetic.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class RegisterRequest {
    private String prenom;
    private String nom;
    private String email;
    private String password;
    private List<Role> roles;
}
