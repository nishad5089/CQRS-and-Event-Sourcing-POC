package com.brainstation23.user.query.api.handlers;

import com.brainstation23.user.query.api.dto.UserLookupResponse;
import com.brainstation23.user.query.api.queries.FindAllUsersQuery;
import com.brainstation23.user.query.api.queries.FindUserByIdQuery;
import com.brainstation23.user.query.api.queries.SearchUsersQuery;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public interface UserQueryHandler {

  UserLookupResponse getUserById(FindUserByIdQuery query);

  UserLookupResponse searchUsers(SearchUsersQuery query);

  UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
