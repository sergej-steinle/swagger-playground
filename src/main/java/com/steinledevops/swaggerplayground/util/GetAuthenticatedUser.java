package com.steinledevops.swaggerplayground.util;

import com.steinledevops.swaggerplayground.entity.User;
import com.steinledevops.swaggerplayground.exceptions.ApiException;
import com.steinledevops.swaggerplayground.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAuthenticatedUser {

    private final UserRepository userRepository;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ApiException("User is not authenticated");
        }

        String username = authentication.getName();
        if (username == null || "anonymousUser".equals(username)) {
            throw new ApiException("Anonymous user is not allowed");
        }

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException("User not found in database - " + username));
    }
}
