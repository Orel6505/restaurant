package com.orel6505.restaurant.controllers;

import com.orel6505.restaurant.dto.LoginRequest;
import com.orel6505.restaurant.dto.LoginResponse;
import com.orel6505.restaurant.models.User;
import com.orel6505.restaurant.repositories.UserRepository;
import com.orel6505.restaurant.security.JwtTokenProvider;
import com.orel6505.restaurant.models.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "User Authentication API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                         UserRepository userRepository,
                         JwtTokenProvider jwtTokenProvider,
                         PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    @Operation(summary = "User login with email and password")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

            if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
            }

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            java.util.Set<String> roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

            LoginResponse response = new LoginResponse(
                token,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                roles
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authentication failed: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user information")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Object> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
            .orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        java.util.Set<String> roles = user.getRoles().stream()
            .map(Role::getRoleName)
            .collect(Collectors.toSet());
        
        // Also show Spring Security authorities for debugging
        java.util.Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        java.util.List<String> springRoles = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        return ResponseEntity.ok(new LoginResponse(
            null,
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail() + " | SpringAuth: " + springRoles,
            roles
        ));
    }
}
