package com.example.demo.core.useCase;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.exception.IncorrectPasswordException;
import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.core.repository.DeveloperRepository;
import com.example.demo.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthUseCase {
    private final DeveloperRepository developerRepository;
    private final DeveloperUseCase developerUseCase;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public Map<String, String> login(String username, String password) {
        Developer developer = this.developerRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found."));

        if (passwordEncoder.matches(password, developer.getPassword())) {
            return new HashMap<String, String>() {{
                put("token", tokenService.generateToken(developer));
            }};
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public Developer register(Developer data) {
        this.developerUseCase.validateEmailDoesNotExist(data.getEmail());

        String passwordEncrypted = this.passwordEncoder.encode(data.getPassword());
        Developer developer = new Developer(data.getName(), data.getEmail(), passwordEncrypted);
        this.developerRepository.save(developer);
        return developer;
    }
}
