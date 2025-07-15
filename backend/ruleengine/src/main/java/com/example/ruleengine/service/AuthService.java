package com.example.ruleengine.service;

import com.example.ruleengine.dto.AuthRequest;
import com.example.ruleengine.dto.AuthResponse;
import com.example.ruleengine.model.User;
import com.example.ruleengine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse signup(AuthRequest request) {
        try {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new RuntimeException("Email already registered");
            }

            User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            userRepository.save(user);

            String token = jwtService.generateToken(user.getEmail());
            return new AuthResponse(token);
        } catch (Exception e) {
            e.printStackTrace(); // 🪵 Print actual error for debugging
            throw new RuntimeException("Signup failed: " + e.getMessage());
        }
    }

    public AuthResponse login(AuthRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid credentials");
            }

            String token = jwtService.generateToken(user.getEmail());
            return new AuthResponse(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }
}
