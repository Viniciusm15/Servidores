package com.vinicius.serversapi.auth.service.auth.impl;

import com.vinicius.serversapi.auth.dto.auth.AuthResponse;
import com.vinicius.serversapi.auth.dto.auth.LoginRequest;
import com.vinicius.serversapi.auth.dto.auth.RegisterRequest;
import com.vinicius.serversapi.auth.model.User;
import com.vinicius.serversapi.auth.model.core.Person;
import com.vinicius.serversapi.auth.repository.UserRepository;
import com.vinicius.serversapi.auth.service.auth.contract.IAuthService;
import com.vinicius.serversapi.auth.service.impl.PersonService;
import com.vinicius.serversapi.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PersonService personService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        Person person = personService.createAndReturnEntity(request.getPerson());

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .person(person)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse refreshToken(User user) {
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
