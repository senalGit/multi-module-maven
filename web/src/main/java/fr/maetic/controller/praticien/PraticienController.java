package fr.maetic.controller.praticien;

import fr.maetic.model.praticien.Praticien;
import fr.maetic.service.praticien.PraticienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/praticien")
public class PraticienController {

    private final PraticienService praticienService;

    @PostMapping("/praticien")
    public Praticien save(@RequestBody Praticien praticien) {
        return praticienService.save(praticien);
    }

    @GetMapping("/praticien/{id}")
    public Optional<Praticien> getById(@PathVariable(value = "id") Long id) {
        return praticienService.find(id);
    }

    @GetMapping("/praticien")
    public List<Praticien> getAll() {
        return praticienService.findAll();
    }

    @DeleteMapping("/praticien/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        praticienService.delete(id);
    }

    @DeleteMapping("/praticien")
    public void deleteAll() {
        praticienService.deleteAll();
    }

    @GetMapping("/praticien/count")
    public long count() {
        return praticienService.count();
    }
}