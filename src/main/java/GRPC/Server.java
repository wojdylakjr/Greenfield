package GRPC;

import io.grpc.ServerBuilder;

import java.io.IOException;

public class Server implements Runnable {
   private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run(){
        this.startServer();
    }

    private void startServer() {
        try {

            System.out.println("[BOOT] Launching chat service on port " + this.port);

            io.grpc.Server server = ServerBuilder.forPort(this.port).addService(new RobotServiceImpl()).build();

            server.start();

            System.out.println("[BOOT] Server started!");

            server.awaitTermination();
            System.out.println("Terminated");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
