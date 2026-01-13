package com.steinledevops.swaggerplayground.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Authority implements GrantedAuthority {

    private String authority;

    @Override
    public @Nullable String getAuthority() {
        return this.authority;
    }
}
