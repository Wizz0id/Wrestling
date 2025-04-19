package com.example.Wrestling.controller;

import com.example.Wrestling.dto.TokenResponse;
import com.example.Wrestling.dto.UserDTO;
import com.example.Wrestling.entity.User;
import com.example.Wrestling.service.AuthService;
import com.example.Wrestling.service.JwtService;
import com.example.Wrestling.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/api/v1")
public class AuthController {
    @Autowired
    public AuthService authService;
    @Autowired
    public UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registration(@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok(authService.saveUser(userDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserDTO userDto){
        User user = userDetailsService.loadUserByUsername(userDto.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<TokenResponse> refreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationServiceException("No JWT token");
        }
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);
        User user = userDetailsService.loadUserByUsername(username);
        if (jwtService.validate(token)) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
        }
        return ResponseEntity.notFound().build();
    }
}

/*
    @Autowired
    private PasswordEncoder passwordEncoder;


        UserDetails user = userDetailsService.loadUserByUsername(userDto.getUsername());
        if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
            return ResponseEntity.ok(new UserDTO(userDto.getUsername(), ""));
        }
        return ResponseEntity.noContent().build();
 */
