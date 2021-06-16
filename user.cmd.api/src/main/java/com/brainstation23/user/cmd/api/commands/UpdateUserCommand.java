package com.brainstation23.user.cmd.api.commands;


import com.brainstation23.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;
}
