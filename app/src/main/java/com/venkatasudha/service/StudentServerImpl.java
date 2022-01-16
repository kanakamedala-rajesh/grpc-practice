package com.venkatasudha.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.venkatasudha.entities.EntitiesProto;
import com.venkatasudha.services.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

public class StudentServerImpl extends StudentServiceGrpc.StudentServiceImplBase {
    StreamObserver<Int32Value> int32ValueStreamObserver = new StreamObserver<>() {
        @Override
        public void onNext(Int32Value value) {
            System.out.println("OnNext: " + value);
        }

        @Override
        public void onError(Throwable t) {
            System.err.println("int32ValueStreamObserver error: " + t.getLocalizedMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("onCompleted");
        }
    };

    StreamObserver<Int32Value> int32ValueStreamObserver2 = new StreamObserver<>() {
        @Override
        public void onNext(Int32Value value) {
            System.out.println("OnNext2: " + value);
        }

        @Override
        public void onError(Throwable t) {
            System.err.println("int32ValueStreamObserver error2: " + t.getLocalizedMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("onCompleted2");
        }
    };

    @Override
    public void getStudent(Empty request, StreamObserver<EntitiesProto.Student> responseObserver) {
        System.out.println("getStudent: ");
        EntitiesProto.Student responseStudent = getStudent(-1);
        responseObserver.onNext(responseStudent);
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentByID(Int32Value request, StreamObserver<EntitiesProto.Student> responseObserver) {
        System.out.println("getStudentByID: " + request.getValue());
        responseObserver.onNext(getStudent(request.getValue()));
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentList(Int32Value request, StreamObserver<EntitiesProto.Student> responseObserver) {
        System.out.println("getStudentList: " + request.getValue());
        responseObserver.onNext(getStudent(request.getValue()));
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Int32Value> getStudentByStream(StreamObserver<EntitiesProto.Student> responseObserver) {
        System.out.println("getStudentByStream: ");
        return int32ValueStreamObserver;
    }

    @Override
    public StreamObserver<Int32Value> getStudentsListByStream(StreamObserver<EntitiesProto.Student> responseObserver) {
        System.out.println("getStudentsListByStream: ");
        return int32ValueStreamObserver2;
    }

    private EntitiesProto.Student getStudent(int request) {
        return EntitiesProto.Student.newBuilder().setId(request).setName("Rajesh Kanakamedala").setGroup(EntitiesProto.Group.M_TECH).build();
    }
}
