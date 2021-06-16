package com.brainstation23.user.cmd.api.security;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public interface PasswordEncoder {

  String hashPassword(String password);
}
