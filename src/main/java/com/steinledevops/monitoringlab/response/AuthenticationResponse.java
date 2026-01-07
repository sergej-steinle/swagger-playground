package com.steinledevops.monitoringlab.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String firstName;
    private String lastName;
    private String username;
    private String token;
}
