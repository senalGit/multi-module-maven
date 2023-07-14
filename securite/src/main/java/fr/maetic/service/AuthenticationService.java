package fr.maetic.service;

import fr.maetic.config.JwtService;
import fr.maetic.model.Token;
import fr.maetic.model.TokenType;
import fr.maetic.model.User;
import fr.maetic.model.dto.AuthenticationRequest;
import fr.maetic.model.dto.AuthenticationResponse;
import fr.maetic.model.dto.RegisterRequest;
import fr.maetic.repository.TokenRepository;
import fr.maetic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        boolean userExist = userRepository.existsByEmailAllIgnoreCase(request.getEmail());


        if (!userExist){
            var user = User.builder()
                    .prenom(request.getPrenom())
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(request.getRoles())
                    .build();

            var savedUser = userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            saveUserToken(savedUser, jwtToken);
            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        }else {
log.info("L'utilisateur existe deja");
        }

        return null;
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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with the specified ID"));
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
