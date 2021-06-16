package com.brainstation23.user.core.events;

import com.brainstation23.user.core.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserUpdatedEvent {

  private String id;

  private User user;
}
