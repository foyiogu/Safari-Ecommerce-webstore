package com.decagon.safariwebstore.security.models;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class Roles implements GrantedAuthority {
    @Setter private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}
