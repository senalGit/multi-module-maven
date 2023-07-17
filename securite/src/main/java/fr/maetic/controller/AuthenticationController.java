package fr.maetic.controller;

import fr.maetic.dto.HttpResponse;
import fr.maetic.model.dto.AuthenticationRequest;
import fr.maetic.model.dto.AuthenticationResponse;
import fr.maetic.model.dto.RegisterRequest;
import fr.maetic.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

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
    public ResponseEntity<AuthenticationResponse> authentifier(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("Authentification de : " + request.getEmail());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = authenticationService.verifierTokenCreationCompte(token);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .donnees(Map.of("Success", isSuccess))
                        .message("Vérification de votre adresse mail - SUCCES")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/list")
    public ResponseEntity<HttpResponse> list(){
        return ResponseEntity.ok()
                .body(
                        HttpResponse
                                .builder()
                                .timestamp(LocalDateTime.now().toString())
                                .donnees(Map.of("Liste", authenticationService.findAllUsers()))
                                .message("Liste des utilisateurs")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }
}
