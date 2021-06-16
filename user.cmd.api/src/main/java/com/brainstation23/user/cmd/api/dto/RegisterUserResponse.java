package com.brainstation23.user.cmd.api.dto;

import com.brainstation23.user.core.dto.BaseResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public class RegisterUserResponse extends BaseResponse {

  @Getter
  @Setter(AccessLevel.PRIVATE)
  private String id;

  public RegisterUserResponse(String id, String message) {
    super(message);
    this.id = id;
  }
}
