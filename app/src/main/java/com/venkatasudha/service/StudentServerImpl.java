package com.venkatasudha.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.venkatasudha.entities.EntitiesProto;
import com.venkatasudha.services.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class StudentServerImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getStudent(Empty request, StreamObserver<EntitiesProto.Student> responseObserver) {
        super.getStudent(request, responseObserver);
    }

    @Override
    public void getStudentByID(Int32Value request, StreamObserver<EntitiesProto.Student> responseObserver) {
        System.out.println("getStudentByID: " + request.getValue());
        EntitiesProto.Student responseStudent = EntitiesProto.Student.newBuilder()
              .setId(request.getValue())
              .setName("Rajesh Kanakamedala")
              .setGroup(EntitiesProto.Group.M_TECH)
              .build();

        responseObserver.onNext(responseStudent);
        responseObserver.onCompleted();
    }
}
