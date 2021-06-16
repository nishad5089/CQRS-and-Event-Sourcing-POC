package com.brainstation23.user.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class SearchUsersQuery {

  private String filter;
}
