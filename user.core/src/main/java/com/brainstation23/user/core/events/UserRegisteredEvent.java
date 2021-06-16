package com.brainstation23.user.core.events;

import com.brainstation23.user.core.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
public class UserRegisteredEvent {

  private String id;

  private User user;
}
