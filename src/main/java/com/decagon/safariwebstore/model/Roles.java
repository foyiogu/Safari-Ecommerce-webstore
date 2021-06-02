package com.decagon.safariwebstore.model;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

public class Roles implements GrantedAuthority {
    @Setter
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
