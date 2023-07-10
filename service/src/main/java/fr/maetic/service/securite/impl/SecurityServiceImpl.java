package fr.maetic.service.securite.impl;

import fr.maetic.dao.securite.RoleRepository;
import fr.maetic.dao.securite.UserRepository;
import fr.maetic.enumeration.EnumRole;
import fr.maetic.model.securite.Role;
import fr.maetic.model.securite.User;
import fr.maetic.security.configuration.PasswordConfig;
import fr.maetic.service.securite.SecurityService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordConfig passwordConfig;

    @Override
    public User addUser(@NotNull User user) {
        User appUser = userRepository.findByUsername(user.getUsername());
        if (appUser == null){
            log.info("Création de l'utilisateur {}", user.getUsername());
            user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            log.info("l'utilisateur {} est créé", user.getUsername());
        }else {
            log.error("l'utilisateur {} existe déja", user.getUsername());
            throw new RuntimeException("L'utilisateur " + user.getUsername() + " existe deja");
        }
return user;
    }


    @Override
    public Role addRole(EnumRole roleName) {
        Role optionalRole = roleRepository.findByRoleName(roleName);

        if (Optional.of(optionalRole).isPresent()) throw new RuntimeException("Le role " + roleName + " existe déja");

        Role role = new Role(null, roleName);
        return roleRepository.save(role);

    }

    @Override
    public void addRoleToUser(String username, EnumRole roleName) {
        if (!userRepository.existsByUsername(username)){
            throw new RuntimeException("L'utilisateur " + username + " n'existe pas");
        }
        User user = userRepository.findByUsername(username);

        if (!roleRepository.existsByRoleName(roleName)) throw new RuntimeException("Le role " + roleName + " n'existe pas");

Role role = roleRepository.queryByRoleName(roleName);
        user.getRoles().add(role);
    }



    @Override
    public void deleteRoleFromUser(String username, EnumRole roleName) {
        User user = userRepository.findByNom(username);
        if (user == null) throw new RuntimeException("L'utilisateur " + username + " n'existe pas");
        Role role = roleRepository.queryByRoleName(roleName);
        if (role == null) throw new RuntimeException("Le role " + roleName + " n'existe pas");

        user.getRoles().remove(role);
    }

    @Override
    public User loadUserByUserName(String username) {
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public Optional<Role> findRoleByName(EnumRole roleName) {
        return Optional.of(this.roleRepository.findByRoleName(roleName));
    }


}