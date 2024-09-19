package com.example.demo.infrastructure.security;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.core.repository.DeveloperRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final DeveloperRepository developerRepository;
    private final List<String> permitAllPaths = List.of("/auth/login", "/auth/register");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return permitAllPaths.contains(request.getRequestURI());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        String login = this.tokenService.validateToken(token);

        if (login != null) {
            Developer developer = this.developerRepository.findByEmail(login)
                    .orElseThrow(() -> new ResourceNotFoundException("Developer not found"));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_DEVELOPER"));
            var authentication = new UsernamePasswordAuthenticationToken(developer, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
