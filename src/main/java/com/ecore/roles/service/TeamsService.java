package com.ecore.roles.service;

import org.springframework.lang.NonNull;

import java.util.UUID;

public interface TeamsService {

    void verifyTeamContainsUser(
            @NonNull UUID teamId,
            @NonNull UUID userId);

}
