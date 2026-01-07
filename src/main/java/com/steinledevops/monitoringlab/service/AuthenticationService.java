package com.steinledevops.monitoringlab.service;

import com.steinledevops.monitoringlab.request.RegisterRequest;

public interface AuthenticationService {

    void register(RegisterRequest registerRequest);
}
