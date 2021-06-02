package com.decagon.safariwebstore.model;

import com.decagon.safariwebstore.model.Roles;
import lombok.Setter;

import java.util.Collection;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetail implements UserDetails {
    @Setter private String password, username;
    @Setter private Collection<Roles> authorities;

    private Boolean isAccountNonExpired,
                    isAccountNonLocked,
                    isCredentialsNonExpired,
                    isEnabled;

    @Override
    public Collection<Roles> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
