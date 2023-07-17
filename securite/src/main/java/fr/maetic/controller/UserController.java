package fr.maetic.controller;

import fr.maetic.dto.HttpResponse;
import fr.maetic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;


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

}
