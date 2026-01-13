package com.steinledevops.swaggerplayground.config;

import com.steinledevops.swaggerplayground.entity.Authority;
import com.steinledevops.swaggerplayground.entity.User;
import com.steinledevops.swaggerplayground.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class InitAdminRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .authorities(List.of(new Authority("ROLE_ADMIN")))
                    .build();

            userRepository.save(admin);
        }
    }
}
