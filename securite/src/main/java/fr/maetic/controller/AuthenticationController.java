package fr.maetic.controller;

import fr.maetic.model.dto.AuthenticationRequest;
import fr.maetic.model.dto.AuthenticationResponse;
import fr.maetic.model.dto.RegisterRequest;
import fr.maetic.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        log.info("Création de l'utilisateur" + request.getPrenom());
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("Authentification de : " + request.getEmail());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
