package com.steinledevops.swaggerplayground.service;

import com.steinledevops.swaggerplayground.request.AuthenticationRequest;
import com.steinledevops.swaggerplayground.request.RegisterRequest;
import com.steinledevops.swaggerplayground.response.AuthenticationResponse;

public interface AuthenticationService {

    void register(RegisterRequest registerRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
