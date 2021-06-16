package com.brainstation23.user.query.api.handlers;

import com.brainstation23.user.core.events.UserRegisteredEvent;
import com.brainstation23.user.core.events.UserRemovedEvent;
import com.brainstation23.user.core.events.UserUpdatedEvent;
import com.brainstation23.user.query.api.repositories.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

  private final UserRepository userRepository;

  @Autowired
  public UserEventHandlerImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @EventHandler
  @Override
  public void on(UserRegisteredEvent event) {

    userRepository.save(event.getUser());
  }

  @EventHandler
  @Override
  public void on(UserUpdatedEvent event) {

    userRepository.save(event.getUser());
  }

  @EventHandler
  @Override
  public void on(UserRemovedEvent event) {

    userRepository.deleteById(event.getId());
  }
}
