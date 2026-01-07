package com.steinledevops.monitoringlab.service;

import com.steinledevops.monitoringlab.entity.Authority;
import com.steinledevops.monitoringlab.entity.User;
import com.steinledevops.monitoringlab.repository.UserRepository;
import com.steinledevops.monitoringlab.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser") ){
            throw new AccessDeniedException("User is not authenticated");
        }
        User user=  (User) authentication.getPrincipal();

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .authorities((List<Authority>) user.getAuthorities())
                .build();
    }
}
