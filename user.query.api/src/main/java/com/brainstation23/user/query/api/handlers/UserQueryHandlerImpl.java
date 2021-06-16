package com.brainstation23.user.query.api.handlers;

import com.brainstation23.user.core.models.User;
import com.brainstation23.user.query.api.dto.UserLookupResponse;
import com.brainstation23.user.query.api.queries.FindAllUsersQuery;
import com.brainstation23.user.query.api.queries.FindUserByIdQuery;
import com.brainstation23.user.query.api.queries.SearchUsersQuery;
import com.brainstation23.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

  private final UserRepository userRepository;

  @Autowired
  public UserQueryHandlerImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @QueryHandler
  @Override
  public UserLookupResponse getUserById(FindUserByIdQuery query) {

    Optional<User> user = userRepository.findById(query.getId());
    return user.map(UserLookupResponse::new).orElse(null);
  }

  @QueryHandler
  @Override
  public UserLookupResponse searchUsers(SearchUsersQuery query) {

    var users = new ArrayList<>(userRepository.findByFilterRegex(query.getFilter()));
    return new UserLookupResponse(users);
  }

  @QueryHandler
  @Override
  public UserLookupResponse getAllUsers(FindAllUsersQuery query) {

    var users = new ArrayList<>(userRepository.findAll());
    return new UserLookupResponse(users);
  }
}
