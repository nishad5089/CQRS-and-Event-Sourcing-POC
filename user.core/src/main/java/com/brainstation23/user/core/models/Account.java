package com.brainstation23.user.core.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

  @Size(min = 2, message = "username must have a minimum of 2 characters")
  private String username;

  @Size(min = 7, message = "password must have a minimum of 7 characters")
  private String password;

  @NotNull(message = "specify at least 1 user role")
  private List<Role> roles;
}
