package com.brainstation23.user.cmd.api.aggregates;

import com.brainstation23.user.core.events.UserRegisteredEvent;
import com.brainstation23.user.core.events.UserRemovedEvent;
import com.brainstation23.user.core.events.UserUpdatedEvent;
import com.brainstation23.user.core.models.User;
import com.brainstation23.user.cmd.api.commands.RegisterUserCommand;
import com.brainstation23.user.cmd.api.commands.RemoveUserCommand;
import com.brainstation23.user.cmd.api.commands.UpdateUserCommand;
import com.brainstation23.user.cmd.api.security.PasswordEncoder;
import com.brainstation23.user.cmd.api.security.PasswordEncoderImpl;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Aggregate
public class UserAggregate {

  @AggregateIdentifier
  private String id;
  private User user;

  private final PasswordEncoder passwordEncoder;

  public UserAggregate() {
    passwordEncoder = new PasswordEncoderImpl();
  }

  @CommandHandler
  public UserAggregate(RegisterUserCommand command) {

    User newUser = command.getUser();
    newUser.setId(command.getId());
    String password = newUser.getAccount().getPassword();
    passwordEncoder = new PasswordEncoderImpl();
    String hashedPassword = passwordEncoder.hashPassword(password);
    newUser.getAccount().setPassword(hashedPassword);

    var event = UserRegisteredEvent.builder().id(command.getId()).user(newUser).build();

    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void handle(UpdateUserCommand command) {

    User updatedUser = command.getUser();
    updatedUser.setId(command.getId());
    String password = updatedUser.getAccount().getPassword();
    String hashedPassword = passwordEncoder.hashPassword(password);
    updatedUser.getAccount().setPassword(hashedPassword);

    UserUpdatedEvent event =
        UserUpdatedEvent.builder().id(UUID.randomUUID().toString()).user(updatedUser).build();

    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void handle(RemoveUserCommand command) {

    UserRemovedEvent event = new UserRemovedEvent();
    event.setId(command.getId());

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(UserRegisteredEvent event) {

    this.id = event.getId();
    this.user = event.getUser();
  }

  @EventSourcingHandler
  public void on(UserUpdatedEvent event) {

    this.id = event.getId();
    this.user = event.getUser();
  }

  @EventSourcingHandler
  public void on(UserRemovedEvent event) {

    AggregateLifecycle.markDeleted();
  }
}
