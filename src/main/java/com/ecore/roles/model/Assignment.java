package com.ecore.roles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Assignment {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "team_id", nullable = false)
    private UUID teamId;

}
