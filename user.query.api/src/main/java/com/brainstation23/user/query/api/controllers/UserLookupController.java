package com.brainstation23.user.query.api.controllers;

import com.brainstation23.user.query.api.dto.UserLookupResponse;
import com.brainstation23.user.query.api.queries.FindAllUsersQuery;
import com.brainstation23.user.query.api.queries.FindUserByIdQuery;
import com.brainstation23.user.query.api.queries.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/userLookup")
public class UserLookupController {

  private final QueryGateway queryGateway;

  @Autowired
  public UserLookupController(QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  @GetMapping(path = "/")
  public ResponseEntity<UserLookupResponse> getAllUsers() {

    try {

      UserLookupResponse response =
          queryGateway
              .query(new FindAllUsersQuery(), ResponseTypes.instanceOf(UserLookupResponse.class))
              .join();

      if (response == null || response.getUsers() == null) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {

      var safeErrorMessage = "Failed to complete get all users request";
      System.out.println(e.toString());

      return new ResponseEntity<>(
          new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/byId/{id}")
  public ResponseEntity<UserLookupResponse> getUserById(@PathVariable(value = "id") String id) {

    try {

      FindUserByIdQuery query = new FindUserByIdQuery(id);
      UserLookupResponse response =
          queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

      if (response == null || response.getUsers() == null) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {

      var safeErrorMessage = "Failed to complete get user by ID request";
      System.out.println(e.toString());

      return new ResponseEntity<>(
          new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/byFilter/{filter}")
  public ResponseEntity<UserLookupResponse> searchUserByFilter(
      @PathVariable(value = "filter") String filter) {

    try {

      var query = new SearchUsersQuery(filter);
      var response =
          queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

      if (response == null || response.getUsers() == null) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {

      var safeErrorMessage = "Failed to complete user search request";
      System.out.println(e.toString());

      return new ResponseEntity<>(
          new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
