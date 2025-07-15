package com.example.ruleengine.controller;

import com.example.ruleengine.dto.AuthRequest;
import com.example.ruleengine.dto.AuthResponse;
import com.example.ruleengine.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody AuthRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        System.out.println("Login attempt: " + request.getEmail());
        return authService.login(request);
    }

}
