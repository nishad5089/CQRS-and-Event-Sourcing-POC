package com.brainstation23.user.cmd.api.controller;

import com.brainstation23.user.cmd.api.commands.RemoveUserCommand;
import com.brainstation23.user.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/removeUser")
public class RemoveUserController {

  private final CommandGateway commandGateway;

  @Autowired
  public RemoveUserController(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<BaseResponse> removeUser(@PathVariable(value = "id") String id) {

    try {

      commandGateway.sendAndWait(new RemoveUserCommand(id));

      return new ResponseEntity<>(new BaseResponse("User successfully removed!"), HttpStatus.OK);
    } catch (Exception e) {

      var safeErrorMessage = "Error while processing remove user request for id - " + id;
      System.out.println(e.toString());

      return new ResponseEntity<>(
          new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
