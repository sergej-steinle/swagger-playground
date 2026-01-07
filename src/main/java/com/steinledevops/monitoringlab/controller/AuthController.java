package com.steinledevops.monitoringlab.controller;

import com.steinledevops.monitoringlab.request.RegisterRequest;
import com.steinledevops.monitoringlab.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication REST Endpoints", description = "Endpoints for user authentication and authorization")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Creates a new user account with the provided registration details")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest registerRequest){
        authenticationService.register(registerRequest);
        return ResponseEntity.ok().build();
    }
}
