package com.steinledevops.swaggerplayground.service;

import com.steinledevops.swaggerplayground.entity.Authority;
import com.steinledevops.swaggerplayground.entity.User;
import com.steinledevops.swaggerplayground.repository.UserRepository;
import com.steinledevops.swaggerplayground.request.AuthenticationRequest;
import com.steinledevops.swaggerplayground.request.RegisterRequest;
import com.steinledevops.swaggerplayground.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .authorities(List.of(new Authority("ROLE_EMPLOYEE")))
                .build();

        userRepository.save(user);
    }

    @Override
    @Transactional
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return AuthenticationResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .token(jwtService.generateToken(new HashMap<String, Object>(),user))
                .roles(roles)
                .build();
    }
}
