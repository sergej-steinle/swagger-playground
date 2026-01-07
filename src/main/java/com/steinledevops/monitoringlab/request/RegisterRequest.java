package com.steinledevops.monitoringlab.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {

    @NotBlank(message = "First name cannot be blank")
    @Size(min=2,max=80,message = "First name must be between 2 and 80 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min=2,max=80,message = "Last name must be between 2 and 80 characters")
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    @Size(min=2,max=80,message = "Username must be between 2 and 80 characters")
    private String username;

    @NotBlank
    @Size(min=5,message = "Password must be at least 5 characters")
    private String password;
}
