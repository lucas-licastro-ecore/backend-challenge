package com.ecore.roles.service;

import com.ecore.roles.model.Role;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface RolesService {

    @NonNull
    Role createRole(@NonNull Role role);

    List<Role> getRoles();

    @Nullable
    Role getRole(@NonNull UUID id);

    @NonNull
    Role getDefaultRole();

    void validateRole(Role role);

}
