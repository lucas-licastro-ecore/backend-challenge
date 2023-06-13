package com.ecore.roles.service.impl;

import com.ecore.roles.exception.InvalidArgumentException;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.model.Role;
import com.ecore.roles.model.Assignment;
import com.ecore.roles.model.Team;
import com.ecore.roles.model.User;
import com.ecore.roles.repository.AssignmentRepository;
import com.ecore.roles.service.AssignmentsService;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.service.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class AssignmentsServiceImpl implements AssignmentsService {

    private final AssignmentRepository assignmentRepository;
    private final TeamsService teamsService;
    private final RolesService rolesService;

    @Override
    public Assignment createAssignment(
            Assignment assignment) {
        Assignment validAssignment = validateAssignment(assignment);
        teamsService.verifyTeamContainsUser(validAssignment.getTeamId(), validAssignment.getUserId());
        return assignmentRepository.save(validAssignment);
    }

    @Override
    public List<Assignment> getAssignments(Role role) {
        rolesService.validateRole(role);
        return assignmentRepository.findByRole(role);
    }

    @Override
    public Assignment getTeamMemberAssignment(UUID teamId, UUID userId) {
        teamsService.verifyTeamContainsUser(teamId, userId);
        return assignmentRepository.findByTeamIdAndUserId(teamId, userId)
                .orElse(Assignment.builder()
                        .role(rolesService.getDefaultRole())
                        .teamId(teamId)
                        .userId(userId)
                        .build());
    }

    private Assignment validateAssignment(Assignment assignment) {
        ofNullable(assignment)
                .orElseThrow(() -> new InvalidArgumentException(Assignment.class));
        UUID teamId = ofNullable(assignment.getTeamId())
                .orElseThrow(() -> new InvalidArgumentException(Team.class));
        UUID userId = ofNullable(assignment.getUserId())
                .orElseThrow(() -> new InvalidArgumentException(User.class));

        rolesService.validateRole(assignment.getRole());

        ofNullable(assignmentRepository.findByTeamIdAndUserId(teamId, userId))
                .orElseThrow(() -> new ResourceExistsException(Assignment.class));

        if (assignment.getRole() == null) {
            assignment.setRole(rolesService.getDefaultRole());
        }
        return assignment;
    }
}
