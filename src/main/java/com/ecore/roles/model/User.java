package com.ecore.roles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private final UUID id;

    private String firstName;

    private String lastName;

    private String displayName;

    private String avatarUrl;

    private String location;
}
