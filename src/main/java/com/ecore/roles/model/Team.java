package com.ecore.roles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Team {

    private final UUID id;

    private String name;

    private UUID teamLeadId;

    private List<UUID> teamMembersIds;
}
