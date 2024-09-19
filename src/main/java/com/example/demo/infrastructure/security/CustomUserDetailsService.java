package com.example.demo.infrastructure.security;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

@RequiredArgsConstructor
@Configuration
public class CustomUserDetailsService implements UserDetailsService {
    private final DeveloperRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Developer developer = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Developer not found"));
        return new org.springframework.security.core.userdetails.User(developer.getEmail(), developer.getPassword(), new ArrayList<>());
    }
}