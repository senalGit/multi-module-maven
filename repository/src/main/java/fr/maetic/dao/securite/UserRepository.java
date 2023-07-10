package fr.maetic.dao.securite;

import fr.maetic.model.securite.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    User findByNom(String nom);
    long deleteByUsername(String username);
    @Override
    boolean existsById(@NotNull Long id);
    boolean existsByIdAndUsername(Long id, String username);
    User queryById(Long id);
}