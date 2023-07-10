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
    public User save(User user);
    public void deleteById(Long id);
    public User findById(Long id);
    public Page<User> findByCondition(User user, Pageable pageable);
    public User update(User user, Long id);
    public List<User> findAllAppUsers();
}
