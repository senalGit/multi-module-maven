package fr.maetic.dao.praticien;


import fr.maetic.model.praticien.Praticien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PraticienRepository extends JpaRepository<Praticien, Long> {
}