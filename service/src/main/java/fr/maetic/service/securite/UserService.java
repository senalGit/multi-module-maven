package fr.maetic.service.securite;

import fr.maetic.model.securite.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    Boolean verifyToken(String token);
    Object findAllUsers();
    User save(User user);
    void deleteById(Long id);
    User findById(Long id);
    Page<User> findByCondition(User user, Pageable pageable);
    User update(User user, Long id);
    List<User> findAllAppUsers();
}
