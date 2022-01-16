package com.venkatasudha.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.venkatasudha.entities.EntitiesProto;
import com.venkatasudha.services.StudentServiceGrpc;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.function.Consumer;

public class StudentClient {
    private final StudentServiceGrpc.StudentServiceBlockingStub blockingStub;
    private final StudentServiceGrpc.StudentServiceStub studentServiceStub;

    public StudentClient(Channel channel) {
        blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        studentServiceStub = StudentServiceGrpc.newStub(channel);
    }

    public void getStudent(int id) {
        StreamObserver<EntitiesProto.Student> studentStreamObserver = new StreamObserver<EntitiesProto.Student>() {
            @Override
            public void onNext(EntitiesProto.Student value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };

        EntitiesProto.Student student = blockingStub.getStudentByID(Int32Value.newBuilder().setValue(id).build());

        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Group: " + student.getGroup());
        System.out.println("Hash Code: " + student.hashCode());


        blockingStub.getStudent(Empty.newBuilder().build());
        Iterator<EntitiesProto.Student> studentList = blockingStub.getStudentList(Int32Value.newBuilder().setValue(id).build());
        studentList.forEachRemaining(student1 -> {
            System.out.println("Received StudentName: "+student1.getName());
        });
        studentServiceStub.getStudentByStream(studentStreamObserver);
        studentServiceStub.getStudentsListByStream(studentStreamObserver);
    }
}
