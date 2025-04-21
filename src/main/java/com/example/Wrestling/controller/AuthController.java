package com.example.Wrestling.controller;

import com.example.Wrestling.configuration.JwtUtil;
import com.example.Wrestling.dto.TokenResponse;
import com.example.Wrestling.dto.UserDTO;
import com.example.Wrestling.entity.User;
import com.example.Wrestling.service.AuthService;
import com.example.Wrestling.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/api/v1")
public class AuthController {
    @Autowired
    public AuthService authService;
    @Autowired
    public UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registration(@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok(authService.saveUser(userDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserDTO userDto){
        User user = userDetailsService.loadUserByUsername(userDto.getUsername());
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), jwtUtil.generateToken(user.getUsername()), user.getRole().toString()));
    }
}
