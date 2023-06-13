package com.ecore.roles.service.impl;

import com.ecore.roles.client.TeamsClient;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Team;
import com.ecore.roles.service.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeamsServiceImpl implements TeamsService {

    private final TeamsClient teamsClient;

    @Override
    public void verifyTeamContainsUser(UUID teamId, UUID userId) {
        if (!teamsClient.getTeam(teamId).getTeamMembersIds().contains(userId)) {
            throw new ResourceNotFoundException(Team.class, teamId);
        }
    }
}
