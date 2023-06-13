package com.ecore.roles.exception;

import com.ecore.roles.model.Role;

import java.util.UUID;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException {

    public <T> ResourceNotFoundException(Class<T> resource, UUID id) {
        super(format("%s %s not found", resource.getSimpleName(), id));
    }

    public ResourceNotFoundException(Class<Role> resource, String name) {
        super(format("%s %s not found", resource.getSimpleName(), name));
    }
}
