package fr.maetic.repository;


import fr.maetic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
 @Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAllIgnoreCase(String email);
    Optional<User> findUserByEmail(String email);
    User findByEmailIgnoreCase(String email);

     Optional<User> findUserById(Long id);
 }