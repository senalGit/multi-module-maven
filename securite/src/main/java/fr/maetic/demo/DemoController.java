package fr.maetic.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> bonjour(){
        return ResponseEntity.ok("Je suis dans le controler de demonstration");
    }
}
