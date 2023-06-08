package GRPC;

import Robot.CleaningRobot;
import com.example.chat.RobotServiceGrpc.*;
import com.example.chat.RobotServiceOuterClass.*;
import io.grpc.stub.StreamObserver;

import java.util.*;

public class RobotServiceImpl extends RobotServiceImplBase {
    //final Set<RobotRequest> requests;
    private final Object lock = new Object();

    public RobotServiceImpl() {
      //  requests = new HashSet<>();
    }

    @Override
    public void askForPermissionToVisitMechanic(RobotRequest request, StreamObserver<RobotResponse> responseObserver) {
        int robotId = CleaningRobot.getInstance().getId();
        System.out.println("[GRPC SERVER][RobotServiceImpl_Send message] (Current robot id: " + robotId + ") Request: " + request);
        RobotMechanicVisitState robotMechanicVisitState = RobotMechanicManager.getInstance().getRobotMechanicVisitState();
        switch (robotMechanicVisitState) {
            case NOT_REQUIRING:
                System.out.println("[GRPC SERVER] [RobotServiceImpl_Send message] robot " + robotId + ". is not interested and doesn't need to go to mechanic, return OK to robot" + request.getSenderId());
                break;

            case REPAIRING:
                System.out.println("[GRPC SERVER][RobotServiceImpl_Send message] current robot " + robotId + ". is in CS. Robot with id: " + request.getSenderId() + " needs to wait for release");
//                synchronized (requests) {
//                    requests.add(request);
//                }
                synchronized (lock) {
                    while (RobotMechanicManager.getInstance().getRobotMechanicVisitState().equals(RobotMechanicVisitState.REPAIRING)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            System.out.println("[GRPC SERVER] Task execution was interrupted: " + e.getMessage());
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                break;
                //TODO: case when the same robot sends the 2 request at the same time
            case REQUIRING:
                long requestToAccessMechanicTimestamp = RobotMechanicManager.getInstance().getRequestToAccessMechanicTimestamp();
                if (!(robotId == request.getSenderId() ||
                        request.getTimestamp() < requestToAccessMechanicTimestamp ||
                        (requestToAccessMechanicTimestamp == request.getTimestamp() && robotId < request.getSenderId()))) {
                    synchronized (lock) {
                        while (!RobotMechanicManager.getInstance().getRobotMechanicVisitState().equals(RobotMechanicVisitState.NOT_REQUIRING)) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                System.out.println("[GRPC SERVER] Task execution was interrupted: " + e.getMessage());
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                }
                break;
        }
        responseObserver.onNext(RobotResponse.newBuilder().setMessage("OK from peer " + robotId).build());
        responseObserver.onCompleted();
    }

    @Override
    public void registerRobotInNetwork(Robot request, StreamObserver<RobotResponse> responseObserver) {
        System.out.println("[GRPC SERVER] Trying to add new robot with id: " + request.getRobotId() + " to p2p network");
        CleaningRobot robot = new CleaningRobot(request.getRobotId(), request.getListeningPort(), request.getXCoordinate(), request.getYCoordinate());
        RobotsPeers.getInstance().add(robot);
        System.out.println("[GRPC SERVER] New robots list: " + RobotsPeers.getInstance());
        RobotResponse response = RobotResponse.newBuilder().setMessage("New peer with id: " + request.getRobotId() + " was added to list in robot peer with id: " + CleaningRobot.getInstance().getId()).setSenderId(CleaningRobot.getInstance().getId()).setTimestamp(System.currentTimeMillis()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void finishMechanicVisit(RobotRequest request, StreamObserver<RobotResponse> responseObserver) {
        System.out.println("[GRPC SERVER][ReleaseCS] Return " + request.getSenderId() + " is realising CS");
        synchronized (lock) {
            lock.notifyAll();
            RobotResponse response = RobotResponse.newBuilder().setMessage("Released").setTimestamp(System.currentTimeMillis()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void deleteRobotFromNetwork(RobotRequest request, StreamObserver<RobotResponse> responseObserver) {
        System.out.println("[GRPC SERVER] Robot with id: " + request.getSenderId() + " wants leave to p2p network");
        RobotsPeers.getInstance().removeRobotById(request.getSenderId());
        System.out.println("[GRPC SERVER] New robots list: " + RobotsPeers.getInstance());
        RobotResponse response = RobotResponse.newBuilder().setMessage("Peer with id: " + request.getSenderId() + " was deleted from list  ").setSenderId(CleaningRobot.getInstance().getId()).setTimestamp(System.currentTimeMillis()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
