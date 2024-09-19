package com.example.demo.presentation.controller;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.useCase.AuthUseCase;
import com.example.demo.presentation.dto.auth.AuthLoginDTO;
import com.example.demo.presentation.dto.auth.AuthRegisterDTO;
import com.example.demo.presentation.dto.developer.DeveloperDTO;
import com.example.demo.presentation.mapper.DeveloperMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUseCase authUseCase;
    private final DeveloperMapper developerMapper;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid AuthLoginDTO data) {
        Map<String, String> token = this.authUseCase.login(data.getEmail(), data.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<DeveloperDTO> register(@RequestBody @Valid AuthRegisterDTO data) {
        Developer developer = this.developerMapper.toDeveloperEntity(data);
        Developer developerRegistered = this.authUseCase.register(developer);
        DeveloperDTO developerDTO = this.developerMapper.toDeveloperDTO(developerRegistered);
        return ResponseEntity.status(HttpStatus.CREATED).body(developerDTO);
    }
}
