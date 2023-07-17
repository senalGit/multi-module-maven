package fr.maetic.service;

import fr.maetic.config.JwtService;
import fr.maetic.mailing.service.EmailService;
import fr.maetic.model.Confirmation;
import fr.maetic.model.Token;
import fr.maetic.model.TokenType;
import fr.maetic.model.User;
import fr.maetic.model.dto.AuthenticationRequest;
import fr.maetic.model.dto.AuthenticationResponse;
import fr.maetic.model.dto.RegisterRequest;
import fr.maetic.repository.ConfirmationRepository;
import fr.maetic.repository.TokenRepository;
import fr.maetic.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

@Override
    public AuthenticationResponse register(@NotNull RegisterRequest request) {
        boolean userExist = userRepository.existsByEmailAllIgnoreCase(request.getEmail());
        if (userExist) {
            throw new RuntimeException("L'utilisateur existe déjà");
        }

        var user = User.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRoles())
                .isEnable(false)
                .build();

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        emailService.sendSimpleMailMessageUserVerification(user.getNom(), user.getEmail(), confirmation.getToken());
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    @Override
    public AuthenticationResponse authenticate(@NotNull AuthenticationRequest request) {

        UsernamePasswordAuthenticationToken userToAuthenticate = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        authManager.authenticate(userToAuthenticate);

        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Could not find user with the specified ID"));

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

@Override
    public Boolean verifierTokenCreationCompte(String token){
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());

        user.setEnable(true);

        userRepository.save(user);

        //confirmationRepository.delete(confirmation);

        return Boolean.TRUE;
    }
}
