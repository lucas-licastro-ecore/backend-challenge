package com.ecore.roles.web.dto;

import com.ecore.roles.model.Assignment;
import com.ecore.roles.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record AssignmentInputDto(
        @JsonProperty(value = "teamMemberId") UUID userId,
        UUID teamId
) {

    public Assignment toModel(Role role) {
        return Assignment.builder()
                .role(role)
                .userId(userId())
                .teamId(teamId())
                .build();
    }

}
