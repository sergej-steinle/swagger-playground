package com.steinledevops.swaggerplayground.controller;

import com.steinledevops.swaggerplayground.response.UserResponse;
import com.steinledevops.swaggerplayground.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name= "User REST endpoints", description = "Endpoints for managing users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo() {
        UserResponse userResponse = userService.getUserInfo();
        return ResponseEntity.ok(userResponse);
    }

}
