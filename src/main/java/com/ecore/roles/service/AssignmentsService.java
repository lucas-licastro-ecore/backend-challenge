package com.ecore.roles.service;

import com.ecore.roles.model.Role;
import com.ecore.roles.model.Assignment;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface AssignmentsService {

    @NotNull
    Assignment createAssignment(
            @NonNull Assignment assignment);

    List<Assignment> getAssignments(@NonNull Role role);

    @Nullable
    Assignment getTeamMemberAssignment(
            @NonNull UUID teamId,
            @NonNull UUID userId);

}
