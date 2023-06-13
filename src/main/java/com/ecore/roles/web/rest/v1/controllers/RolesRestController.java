package com.ecore.roles.web.rest.v1.controllers;

import com.ecore.roles.model.Role;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.web.dto.RoleDto;
import com.ecore.roles.web.dto.RoleInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles")
public class RolesRestController {

    private final RolesService rolesService;

    @PostMapping(
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<RoleDto> createRole(
            @RequestBody RoleInputDto roleInput) {
        Role role = rolesService.createRole(roleInput.toModel());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RoleDto(role));
    }

    @GetMapping(
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<RoleDto>> getRoles() {
        List<RoleDto> roles = rolesService.getRoles()
                .stream().map(RoleDto::new)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roles);
    }

    @GetMapping(
            path = "/{roleId}",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<RoleDto> getRole(
            @PathVariable UUID roleId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RoleDto(rolesService.getRole(roleId)));
    }

}
