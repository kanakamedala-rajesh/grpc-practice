package com.venkatasudha.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class ClientMain {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(CommonConstants.SERVER_ADDRESS).usePlaintext().build();

        try {
            StudentClient client = new StudentClient(channel);
            client.getStudent(900);
        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            try {
                System.out.println("Terminating Client");
                channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.err.println("Error shutting down client: " + e.getLocalizedMessage());
            }
        }
    }
}
