package com.ecore.roles.web.dto;

import com.ecore.roles.exception.InvalidArgumentException;
import com.ecore.roles.model.Assignment;
import com.ecore.roles.model.Role;

import java.util.UUID;

import static java.util.Optional.ofNullable;

public record RoleDto(
        UUID id,
        String name
) {
    public RoleDto(Role role) {
        this(
                ofNullable(role)
                        .orElseThrow(() -> new InvalidArgumentException(Assignment.class))
                        .getId(),
                role.getName()
        );
    }

}
