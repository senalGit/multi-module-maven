package fr.maetic.service.securite;
import fr.maetic.enumeration.EnumRole;
import fr.maetic.model.securite.Role;
import fr.maetic.model.securite.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SecurityService {

    User addUser(User user);

    Role addRole(EnumRole roleName);

    void addRoleToUser(String username, EnumRole roleName);

    void deleteRoleFromUser(String username, EnumRole roleName);

    User loadUserByUserName(String username);


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Role> findRoleByName(EnumRole roleName);
}
