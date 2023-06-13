package com.ecore.roles.client.dto;

import com.ecore.roles.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserDto {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String displayName;

    @JsonProperty
    private String avatarUrl;

    @JsonProperty
    private String location;

    public User toModel() {
        if (this.getId() == null) {
            return null;
        }
        return User.builder()
                .id(this.getId())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .displayName(this.getDisplayName())
                .avatarUrl(this.getAvatarUrl())
                .location(this.getLocation())
                .build();
    }
}
