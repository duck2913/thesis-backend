package com.thesis.user_service.auth;

import com.thesis.user_service.config.JwtService;
import com.thesis.user_service.exception.CustomException;
import com.thesis.user_service.user.Role;
import com.thesis.user_service.user.User;
import com.thesis.user_service.user.UserDto;
import com.thesis.user_service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final ModelMapper mapper;


    public AuthResponse register(RegisterRequest request) {
        var existingUser = userService.getUserByUsername(request.username());
        if (existingUser != null) {
            throw new CustomException("Username already exists");
        }

        User newUser = User.builder()
                .username(request.username())
                .password(request.password())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .role(request.username().equals("admin") ? Role.ADMIN : Role.USER)
                .build();
        userService.saveUser(newUser);

        UserDto userDto = mapper.map(newUser, UserDto.class);
        var claims = new HashMap<String, Object>();
        claims.put("user", userDto);
        var jwtToken = jwtService.generateToken(claims);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userService.getUserByUsername(request.username());
        UserDto userDto = mapper.map(user, UserDto.class);

        var claims = new HashMap<String, Object>();
        claims.put("user", userDto);

        var jwtToken = jwtService.generateToken(claims);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}