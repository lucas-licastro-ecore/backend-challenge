package com.ecore.roles.client.dto;

import com.ecore.roles.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class TeamDto {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    private UUID teamLeadId;

    @JsonProperty
    private List<UUID> teamMemberIds;

    public Team toModel() {
        if (this.getId() == null) {
            return null;
        }
        return Team.builder()
                .id(this.getId())
                .name(this.getName())
                .teamLeadId(this.getTeamLeadId())
                .teamMembersIds(this.getTeamMemberIds())
                .build();
    }
}
