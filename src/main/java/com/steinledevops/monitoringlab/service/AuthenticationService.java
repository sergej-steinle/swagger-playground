package com.steinledevops.monitoringlab.service;

import com.steinledevops.monitoringlab.request.AuthenticationRequest;
import com.steinledevops.monitoringlab.request.RegisterRequest;
import com.steinledevops.monitoringlab.response.AuthenticationResponse;

public interface AuthenticationService {

    void register(RegisterRequest registerRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
