package com.excersice.blog.controller;

import com.excersice.blog.request.auth.LoginRequest;
import com.excersice.blog.response.auth.LoginResponse;
import com.excersice.blog.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.excersice.blog.service.JwtService;
import com.excersice.blog.service.MyUserDetailsService;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/api/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(
                    myUserDetailsService.loadUserByUsername(loginRequest.getUsername())
            );

            return LoginResponse.builder()
                    .token(token)
                    .build();
        }

        throw new UsernameNotFoundException("Invalid user");
    }
}