package com.brainstation23.user.cmd.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Service
public class PasswordEncoderImpl implements PasswordEncoder {

  @Override
  public String hashPassword(String password) {

    var encoder = new BCryptPasswordEncoder(12);
    return encoder.encode(password);
  }
}
