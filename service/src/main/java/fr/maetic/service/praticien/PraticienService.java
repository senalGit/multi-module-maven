package fr.maetic.service.praticien;

import fr.maetic.model.praticien.Praticien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PraticienService {

    Praticien save(Praticien praticien);

    Optional<Praticien> find(Long id);

    List<Praticien> findAll();

    List<Praticien> findAll(Sort sort);

    Page<Praticien> findAll(Pageable pageable);

    void delete(Long id);

    void delete(Praticien praticien);

    void deleteAll();

    long count();

}