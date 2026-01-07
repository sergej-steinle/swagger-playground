package com.steinledevops.monitoringlab.service;

import com.steinledevops.monitoringlab.entity.Authority;
import com.steinledevops.monitoringlab.entity.User;
import com.steinledevops.monitoringlab.repository.UserRepository;
import com.steinledevops.monitoringlab.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
