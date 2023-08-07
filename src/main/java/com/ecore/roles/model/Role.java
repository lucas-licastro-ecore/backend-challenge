package com.ecore.roles.model;

import com.ecore.roles.service.Roles.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public GrpcRole toGrpcRole() {
        return GrpcRole.newBuilder()
                .setId(this.id.toString())
                .setName(this.name)
                .build();
    }
}
