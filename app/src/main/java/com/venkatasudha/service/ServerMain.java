package com.venkatasudha.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerMain {
    private static Server server;

    public static void main(String[] args) {
        startServer();
        blockUntilShutdown();
    }

    private static void blockUntilShutdown() {
        if (null != server) {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                System.err.println("Error holding server in running state: " + e.getLocalizedMessage());
            }
        }
    }

    private static void startServer() {
        try {
            server = ServerBuilder.forPort(CommonConstants.PORT).addService(new StudentServerImpl()).build().start();
            System.out.println("Server Started on port: " + CommonConstants.PORT);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.err.println("Shutting down GRPC server gue to JVM shutdown");
                    stopServer();
                }
            });
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getLocalizedMessage());
        }
    }

    private static void stopServer() {
        if (null != server) {
            try {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.err.println("Error stopping server: " + e.getLocalizedMessage());
            }
            System.err.println("**Server shutdown");
        }
    }
}
