package com.venkatasudha.service;

import com.google.protobuf.Int32Value;
import com.venkatasudha.entities.EntitiesProto;
import com.venkatasudha.services.StudentServiceGrpc;
import io.grpc.Channel;

public class StudentClient {
    private final StudentServiceGrpc.StudentServiceBlockingStub blockingStub;

    public StudentClient(Channel channel) {
        blockingStub = StudentServiceGrpc.newBlockingStub(channel);
    }

    public void getStudent(int id) {
        EntitiesProto.Student student = blockingStub.getStudentByID(Int32Value.newBuilder().setValue(id).build());

        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Group: " + student.getGroup());
        System.out.println("Hash Code: " + student.hashCode());
    }
}
