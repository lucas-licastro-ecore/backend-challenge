package com.ecore.roles.web.dto;

import com.ecore.roles.model.Role;

public record RoleInputDto(
        String name
) {

    public Role toModel() {
        return Role.builder()
                .name(name())
                .build();
    }

}
