package com.ecore.roles.web.dto;

import com.ecore.roles.exception.InvalidArgumentException;
import com.ecore.roles.model.Assignment;
import com.ecore.roles.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import static java.util.Optional.ofNullable;


public record AssignmentDto(
        UUID id,
        UUID roleId,
        @JsonProperty(value = "teamMemberId") UUID userId,
        UUID teamId
) {
    public AssignmentDto(Assignment assignment) {
        this(
                ofNullable(assignment)
                        .orElseThrow(() -> new InvalidArgumentException(Assignment.class))
                        .getId(),
                ofNullable(assignment.getRole())
                        .orElseThrow(() -> new InvalidArgumentException(Role.class))
                        .getId(),
                assignment.getUserId(),
                assignment.getTeamId()
        );
    }
}
