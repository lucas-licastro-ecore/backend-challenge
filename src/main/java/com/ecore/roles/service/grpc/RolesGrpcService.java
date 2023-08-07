package com.ecore.roles.service.grpc;

import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Role;
import com.ecore.roles.service.Roles.*;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.service.RolesServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class RolesGrpcService extends RolesServiceGrpc.RolesServiceImplBase {

    private final RolesService rolesService;

    @Override
    public void getRoles(GetRolesRequest request, StreamObserver<GetRolesResponse> responseObserver) {
        GetRolesResponse response = GetRolesResponse.newBuilder()
                .addAllRoles(rolesService.getRoles().stream()
                        .map(Role::toGrpcRole)
                        .collect(Collectors.toList()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getRole(GetRoleRequest request, StreamObserver<GetRoleResponse> responseObserver) {
        UUID roleId = UUID.fromString(request.getId());
        GrpcRole grpcRole = Optional.ofNullable(rolesService.getRole(roleId))
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, request.getId()))
                .toGrpcRole();
        GetRoleResponse response = GetRoleResponse.newBuilder()
                .setRoles(grpcRole).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
