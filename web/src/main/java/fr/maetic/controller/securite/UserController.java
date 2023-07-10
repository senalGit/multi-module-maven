package fr.maetic.controller.securite;

import fr.maetic.dto.HttpResponse;
import fr.maetic.model.securite.User;
import fr.maetic.service.securite.SecurityService;
import fr.maetic.service.securite.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        log.info("Création de l'utilisateur {} :" , user.getUsername());

        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .donnees(Map.of("user", newUser))
                        .message("User created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = userService.verifyToken(token);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .donnees(Map.of("Success", isSuccess))
                        .message("Vérification de votre adresse mail - SUCCESS")
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
                                .donnees(Map.of("Liste", userService.findAllUsers()))
                                .message("Liste des utilisateurs")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()
                );
    }




    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(userService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new EntityNotFoundException();
        });
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return ResponseEntity.ok().build();
    }
}