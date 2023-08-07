package com.ecore.roles.web.grpc;

import com.ecore.roles.exception.ResourceNotFoundException;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;

@GrpcAdvice
public class GrpcExceptionHandler {

    @net.devh.boot.grpc.server.advice.GrpcExceptionHandler(ResourceNotFoundException.class)
    public StatusRuntimeException handleResourceNotFoundException(ResourceNotFoundException exception) {
        return StatusProto.toStatusRuntimeException(Status.newBuilder()
                .setCode(Code.INVALID_ARGUMENT_VALUE)
                .setMessage(exception.getMessage())
                .build());
    }
}
