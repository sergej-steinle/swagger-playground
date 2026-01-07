package com.steinledevops.monitoringlab.response;


import com.steinledevops.monitoringlab.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private List<Authority> authorities;
}
