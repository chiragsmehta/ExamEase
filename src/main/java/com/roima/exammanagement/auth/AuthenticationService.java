package com.roima.exammanagement.auth;

import com.roima.exammanagement.config.JwtService;
import com.roima.exammanagement.model.Role;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.config.ApplicationConfig.*;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
//        User user = com.roima.exammanagement.model.User.builder()
//                .name(registerRequest.getName())
//                .email(registerRequest.getEmail())
//                .password(passwordEncoder.encode(registerRequest.getPassword()))
//                .role(Role.USER)
//                .build();

        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        user.setIsActive(registerRequest.getIsActive());
        userRepository.save(user);
        HashMap<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("role",user.getRole());
        String jwtToken = jwtService.generateToken(extraClaims,user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
    User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElse(null);

        HashMap<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("role",user.getRole());

        String jwtToken = jwtService.generateToken(extraClaims,user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
}
