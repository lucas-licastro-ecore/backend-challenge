package com.ecore.roles.client;

import com.ecore.roles.client.dto.TeamDto;
import com.ecore.roles.configuration.ClientsProperties;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Component
public class TeamsClient {

    private final RestTemplate restTemplate;
    private final ClientsProperties clientsProperties;

    public @NotNull Team getTeam(@NotNull UUID id) {
        ResponseEntity<TeamDto> responseEntity = restTemplate.exchange(
                clientsProperties.getTeamsApiHost() + "/" + id,
                HttpMethod.GET,
                null,
                TeamDto.class);
        return ofNullable(responseEntity.getBody())
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, id))
                .toModel();
    }
}
