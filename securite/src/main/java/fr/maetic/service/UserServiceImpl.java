package fr.maetic.service;

import fr.maetic.model.User;
import fr.maetic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;


    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void delete(Long id) {
        log.info("Suppression du user numero [ {} ]", id);
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        log.info("Suppression de [ {} {} ]", user.getPrenom(), user.getPrenom());
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository
                .findUserById(id)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas"));
    }
}
