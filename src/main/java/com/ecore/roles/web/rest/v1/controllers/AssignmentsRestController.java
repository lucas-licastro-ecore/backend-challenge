package com.ecore.roles.web.rest.v1.controllers;

import com.ecore.roles.model.Assignment;
import com.ecore.roles.model.Role;
import com.ecore.roles.service.AssignmentsService;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.web.dto.AssignmentDto;
import com.ecore.roles.web.dto.AssignmentInputDto;
import com.ecore.roles.web.dto.RoleDto;
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
@RequestMapping(value = "/v1/assignments")
public class AssignmentsRestController {

    private final RolesService rolesService;
    private final AssignmentsService assignmentsService;

    @PostMapping(
            path = "/roles/{roleId}",
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<AssignmentDto> createAssignment(
            @PathVariable UUID roleId,
            @RequestBody AssignmentInputDto roleAssignmentInput) {
        Role role = rolesService.getRole(roleId);
        Assignment assignment = assignmentsService.createAssignment(
                roleAssignmentInput.toModel(role));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AssignmentDto(assignment));
    }

    @GetMapping(
            path = "/roles/{roleId}",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AssignmentDto>> getAssignments(
            @PathVariable UUID roleId) {
        Role role = rolesService.getRole(roleId);
        List<AssignmentDto> roleAssignments = assignmentsService.getAssignments(role)
                .stream().map(AssignmentDto::new)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleAssignments);
    }

    @GetMapping(
            path = "/teams/{teamId}/users/{userId}/role",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<RoleDto> getTeamMemberAssignmentRole(
            @PathVariable UUID teamId,
            @PathVariable UUID userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RoleDto(assignmentsService.getTeamMemberAssignment(teamId, userId).getRole()));
    }

}
