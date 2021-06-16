package com.brainstation23.user.query.api.handlers;

import com.brainstation23.user.core.events.UserRegisteredEvent;
import com.brainstation23.user.core.events.UserRemovedEvent;
import com.brainstation23.user.core.events.UserUpdatedEvent;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public interface UserEventHandler {

  void on(UserRegisteredEvent event);

  void on(UserUpdatedEvent event);

  void on(UserRemovedEvent event);
}
