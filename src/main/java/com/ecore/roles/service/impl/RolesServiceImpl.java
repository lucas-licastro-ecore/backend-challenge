package com.ecore.roles.service.impl;

import com.ecore.roles.exception.InvalidArgumentException;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Role;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    public static final String DEFAULT_ROLE_NAME = "Developer";

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        validateRole(role);
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, roleId));
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByName(DEFAULT_ROLE_NAME)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, DEFAULT_ROLE_NAME));
    }

    @Override
    public void validateRole(Role role) {
        ofNullable(role)
                .orElseThrow(() -> new InvalidArgumentException(Role.class));
        ofNullable(role.getName())
                .orElseThrow(() -> new InvalidArgumentException(Role.class));
        if (role.getName().isBlank()) {
            throw new InvalidArgumentException(Role.class);
        }
    }
}
