package com.steinledevops.swaggerplayground.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String firstName;
    private String lastName;
    private String username;
    private String token;
    private List<String> roles;
}
