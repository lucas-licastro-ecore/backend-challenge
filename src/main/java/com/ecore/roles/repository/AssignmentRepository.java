package com.ecore.roles.repository;

import com.ecore.roles.model.Assignment;
import com.ecore.roles.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, UUID> {

    Optional<Assignment> findByTeamIdAndUserId(UUID teamId, UUID userId);

    List<Assignment> findByRole(Role role);
}
