package fr.maetic.service.securite.impl;

import fr.maetic.dao.securite.ConfirmationRepository;
import fr.maetic.dao.securite.UserRepository;
import fr.maetic.mailing.service.EmailService;
import fr.maetic.model.securite.Confirmation;
import fr.maetic.model.securite.User;
import fr.maetic.service.securite.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    @Override
    public Boolean verifyToken(String token) {
        return null;
    }

    @Override
    public Object findAllUsers() {
        return null;
    }

    public User save(User user) {

        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setActive(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        /* TODO send mail to user*/
        //emailService.sendSimpleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());
        // emailService.sendMimeMessageWithAttachment(user.getName(), user.getEmail(), confirmation.getToken());
        emailService.sendMimeMessageWithEmbeddedImages(user.getNom(), user.getEmail(), confirmation.getToken());
        return user;

    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Page<User> findByCondition(User user, Pageable pageable) {
        Page<User> entityPage = userRepository.findAll(pageable);
        List<User> users = entityPage.getContent();
        return new PageImpl<>(users, pageable, entityPage.getTotalElements());
    }

    public User update(User user, Long id) {
        User data = findById(id);
        return save(user);
    }

    public List<User> findAllAppUsers() {
        return userRepository.findAll();
    }
}