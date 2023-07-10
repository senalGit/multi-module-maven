package fr.maetic.controller.securite;

import fr.maetic.dto.securite.UserDto;
import fr.maetic.model.securite.Role;
import fr.maetic.model.securite.User;
import fr.maetic.payload.response.MessageResponse;
import fr.maetic.payload.response.SignUpRequest;
import fr.maetic.payload.response.JwtResponse;
import fr.maetic.security.configuration.JwtUtils;
import fr.maetic.service.securite.JpaUserDetailsService;
import fr.maetic.service.securite.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static fr.maetic.enumeration.EnumRole.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    //Quand on veut authentifier un user on a juste besoin de le déléguer à spring. alors on inject AuthenticationManager
    //Il faut fournir un Bean de AuthenticationManager

    @Autowired
    private final AuthenticationManager authenticationManager;
    private final SecurityService securityService;
    private final JpaUserDetailsService jpaUserDetailsService;
    private final JwtUtils jwtUtils; // On injecte jwtutils responsable de generer le token



    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserDto request) {
        String username = request.getUsername();
        String password = request.getPassword();


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);

        final UserDetails user = jpaUserDetailsService.loadUserByUsername(username);
        if (user != null) {
            List<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList());
            //return ResponseEntity.ok(jwtUtils.generateToken(user));
            return ResponseEntity.ok(new JwtResponse(jwtUtils.generateToken(user), user, roles));
        } else {
            return ResponseEntity.status(400).body("Une erreur s'est produite, l'utilisateur existe-t-il?");
        }

    }


    @PostMapping(path = "/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signupRequest) {
        if (this.securityService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already taken!"));
        }
        if (this.securityService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        //Creation d'un nouvel utilisateur
        User user = new User();
        user.setPassword(signupRequest.getPassword());
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());

        Set<String> strRoles = signupRequest.getRoles();

        Collection<Role> roles= new ArrayList<>();
        if (strRoles == null) {
            Role userRole = this.securityService.findRoleByName(ROLE_USER)
                                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = this.securityService.findRoleByName(ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = this.securityService.findRoleByName(ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        this.securityService.addUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}