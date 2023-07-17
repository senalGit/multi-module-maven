package fr.maetic.service;

import fr.maetic.model.dto.AuthenticationRequest;
import fr.maetic.model.dto.AuthenticationResponse;
import fr.maetic.model.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);

    Boolean verifierTokenCreationCompte(String token);

    Object findAllUsers();
}
