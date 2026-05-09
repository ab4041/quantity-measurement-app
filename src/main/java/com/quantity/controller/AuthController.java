package com.quantity.controller;

import com.quantity.dto.AuthRequest;
import com.quantity.dto.AuthResponse;
import com.quantity.security.JwtService;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(
            JwtService jwtService
    ) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request
    ) {

        User user =
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        java.util.List.of()
                );

        String token =
                jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}