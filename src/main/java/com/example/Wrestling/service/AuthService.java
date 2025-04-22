package com.example.Wrestling.service;

import com.example.Wrestling.dto.UserDTO;
import com.example.Wrestling.entity.User;
import com.example.Wrestling.enumurate.Role;
import com.example.Wrestling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean saveUser(UserDTO user)
    {
        User found = userRepository.findByUsername(user.getUsername()).orElse(null);
        if(found != null) return false;
        User new_user = new User();
        new_user.setUsername(user.getUsername());
        if (user.getUsername().equals("ADMIN")) {
            new_user.setRole(Role.ADMIN);
        } else {
            new_user.setRole(Role.USER);
        }
        new_user.setPassword(passwordEncoder.encode(user.getPassword()));
        new_user.setExpired(false);
        userRepository.save(new_user);
        return true;
    }

//    public LoginResponse authenticate(LoginRequest loginRequest) {
//
//        UserEntity user = userServiceHelper.findUserByUsername(loginRequest.getUsername());
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        String accessToken = jwtService.generateAccessToken(user);
//        String refreshToken = jwtService.generateRefreshToken(user);
//
//        return new LoginResponse(accessToken, refreshToken);
//    }
//
//    public LoginResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
//        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            throw new AuthenticationServiceException("No JWT token");
//        }
//        String token = authorizationHeader.substring(7);
//        String username = jwtService.extractUsername(token);
//        UserEntity user = userServiceHelper.findUserByUsername(username);
//
//        if (jwtService.validate(token)) {
//            String accessToken = jwtService.generateAccessToken(user);
//            String refreshToken = jwtService.generateRefreshToken(user);
//            return new LoginResponse(accessToken, refreshToken);
//        }
//        return null;
//    }
}
