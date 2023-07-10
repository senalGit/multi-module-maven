package fr.maetic.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class JwtResponse {

    private String token;
    private UserDetails user;
    private List<String> roles;
}
