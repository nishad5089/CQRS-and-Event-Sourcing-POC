package com.brainstation23.user.core.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public enum Role implements GrantedAuthority {

    READ_PRIVILEGE, WRITE_PRIVILEGE;

    @Override
    public String getAuthority() {
        return name();
    }
}
