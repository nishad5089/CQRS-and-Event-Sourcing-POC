package com.brainstation23.user.query.api.dto;

import com.brainstation23.user.core.dto.BaseResponse;
import com.brainstation23.user.core.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Getter
@Setter
public class UserLookupResponse extends BaseResponse {

  private List<User> users;

  public UserLookupResponse(String message) {
    super(message);
  }

  public UserLookupResponse(List<User> users) {
    super(null);
    this.users = users;
  }

  public UserLookupResponse(User user) {
    super(null);
    this.users = new ArrayList<>();
    this.users.add(user);
  }
}
