package GRPC;

import Robot.CleaningRobot;
import com.example.chat.RobotServiceGrpc;
import com.example.chat.RobotServiceOuterClass;
import com.sun.jersey.api.client.ClientResponse;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;

import java.time.LocalTime;


public class Client {

    final Object lock = new Object();
    int completedTasks = 0;


    public void sendRequestToUseCriticalSectionTest(int id) {
        if (RobotsPeers.isNull()) {
            System.out.println("[GRPC CLIENT] Robot with id: " + id + "is not in network");
            return;
        }
        if (RobotMechanicManager.getInstance().getRobotMechanicVisitState() == RobotMechanicVisitState.REPAIRING) {
            System.out.println("[GRPC CLIENT] Robot is currently repairing. Try again after leaving mechanic");
            return;
        }

        RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().forEach((robot, channel) -> {
            System.out.println("[GRPC CLIENT] { " + LocalTime.now() + "} Sending message to request critical section from: " + CleaningRobot.getInstance() + " to: " + robot);
            RobotServiceGrpc.RobotServiceStub stub = RobotServiceGrpc.newStub(channel);
            RobotMechanicManager.getInstance().setRequestToAccessMechanicTimestamp(System.currentTimeMillis());
            RobotServiceOuterClass.RobotRequest request = RobotServiceOuterClass.RobotRequest.newBuilder().setSenderId(id).setTimestamp(RobotMechanicManager.getInstance().getRequestToAccessMechanicTimestamp()).build();
            RobotMechanicManager.getInstance().setRobotMechanicVisitState(RobotMechanicVisitState.REQUIRING);
            stub.askForPermissionToVisitMechanic(request, new StreamObserver<RobotServiceOuterClass.RobotResponse>() {
                @Override
                public void onNext(RobotServiceOuterClass.RobotResponse value) {
                    System.out.println("[GRPC CLIENT] " + value);
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("[GRPC CLIENT] error: " + t.getMessage());
                    System.out.println("[GRPC CLIENT] Robot with id: " + robot.getId() + " is not responding");
                    if(RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().containsKey(new CleaningRobot(robot.getId()))){
                        notifyAllPeersToRemoveRobotFromNetwork(robot.getId());
                        CleaningRobot.getInstance().sendMessageToServerToDeleteBrokenRobot(robot.getId());
                    }
                    synchronized (lock) {
                        completedTasks++;
                        if (completedTasks >= RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().size()) {
                            lock.notifyAll();
                        }
                    }

                }

                @Override
                public void onCompleted() {
                    System.out.println("[GRPC CLIENT] completed - broadcast message was sent");
                    synchronized (lock) {
                        completedTasks++;
                        if (completedTasks == RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().size()) {
                            lock.notifyAll();
                        }
                    }
                }
            });
        });

        synchronized (lock) {
            while (completedTasks < RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().size()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("[GRPC CLIENT] Task execution was interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        System.out.println("[GRPC CLIENT] it is done after running everything in loop");
        System.out.println("[GRPC CLIENT] completed task: " + completedTasks);

        LocalTime start = LocalTime.now();
        System.out.println(" Robot with id: " + id + " is going to mechanic");

        RobotMechanicManager.getInstance().setRobotMechanicVisitState(RobotMechanicVisitState.REPAIRING);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RobotMechanicManager.getInstance().setRobotMechanicVisitState(RobotMechanicVisitState.NOT_REQUIRING);
        System.err.println("[GRPC CLIENT] Robot was in mechanic from: " + start + " to: " + LocalTime.now());
        System.out.println("Robot" + CleaningRobot.getInstance().getId() + " is done with mechanic - sending message to release critical section");

        //synchronous call to own grpc server to inform grpc server that robot is no more in mechanic
        ManagedChannel currentRobotChannel = RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().get(CleaningRobot.getInstance());
        RobotServiceGrpc.RobotServiceBlockingStub blockingStub = RobotServiceGrpc.newBlockingStub(currentRobotChannel);
        RobotServiceOuterClass.RobotRequest request = RobotServiceOuterClass.RobotRequest.newBuilder().setSenderId(id).setTimestamp(System.currentTimeMillis()).build();
        RobotServiceOuterClass.RobotResponse robotResponse = blockingStub.finishMechanicVisit(request);
        completedTasks = 0;
    }

    //
    public void broadcastRobotToIntroduceInNetwork(int robotId, int listeningPort, int xCoordinate, int yCoordinate) {
        //asynchronous
        if (RobotsPeers.isNull()) {
            System.out.println("[GRPC CLIENT] Robot with id: " + robotId + "is not in network");
            return;
        }

        RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().forEach((robot, channel) -> {
            System.out.println("[GRPC CLIENT][BroadcastRobot] Sending presenting message from: " + CleaningRobot.getInstance() + " to: " + robot);
            RobotServiceGrpc.RobotServiceStub stub = RobotServiceGrpc.newStub(channel);
            RobotServiceOuterClass.Robot request = RobotServiceOuterClass.Robot.newBuilder().setRobotId(robotId).setListeningPort(listeningPort).setXCoordinate(xCoordinate).setYCoordinate(yCoordinate).build();
            stub.registerRobotInNetwork(request, new StreamObserver<RobotServiceOuterClass.RobotResponse>() {
                @Override
                public void onNext(RobotServiceOuterClass.RobotResponse value) {
                    System.out.println("[GRPC CLIENT][BroadcastRobot onNext] Responsed after adding to p2p network: " + value);
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("[GRPC CLIENT][BroadcastRobot onError] error: " + t.getMessage());
                    System.out.println("[GRPC CLIENT][BroadcastRobot onError] robot with id: " + robot.getId() + " is not responding");
                    if(RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().containsKey(new CleaningRobot(robot.getId()))){
                        notifyAllPeersToRemoveRobotFromNetwork(robot.getId());
                        CleaningRobot.getInstance().sendMessageToServerToDeleteBrokenRobot(robot.getId());
                    }
                    synchronized (lock) {
                        completedTasks++;
                        if (completedTasks == RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().size()) {
                            lock.notifyAll();
                        }
                    }
                }

                @Override
                public void onCompleted() {
                    System.out.println("[GRPC CLIENT][BroadcastRobot onCompleted] completed - robot is presented");
                    synchronized (lock) {
                        completedTasks++;
                        if (completedTasks == RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().size()) {
                            lock.notifyAll();
                        }
                    }
                }
            });
        });

        synchronized (lock) {
            while (completedTasks < RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().size()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Task execution was interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        System.out.println("[GRPC CLIENT][BroadcastRobot] it is done after running everything in loop");
        completedTasks = 0;
    }

    public void notifyAllPeersToRemoveRobotFromNetwork(int deletedRobotId) {
        if (RobotsPeers.isNull()) {
            System.out.println("[GRPC CLIENT] Robot with id: " + deletedRobotId + "is not in network");
            return;
        }

        RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().forEach((robot, channel) -> {
            if (robot.getId() != deletedRobotId) {
                System.out.println("[GRPC CLIENT][notifyAllRobotsToLeaveNetwork] Sending message to delete robot : " + deletedRobotId + " to: " + robot);
                RobotServiceGrpc.RobotServiceStub stub = RobotServiceGrpc.newStub(channel);
                RobotServiceOuterClass.RobotRequest request = RobotServiceOuterClass.RobotRequest.newBuilder().setSenderId(deletedRobotId).setTimestamp(System.currentTimeMillis()).setMessage("See you later!").build();
                stub.deleteRobotFromNetwork(request, new StreamObserver<RobotServiceOuterClass.RobotResponse>() {
                    @Override
                    public void onNext(RobotServiceOuterClass.RobotResponse value) {
                        System.out.println("[GRPC CLIENT][leavingNetwork onNext] Responsed after removing to p2p network: " + value);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("[GRPC CLIENT][leavingNetwork onError] error: " + t.getMessage());
                        System.out.println("[GRPC CLIENT][leavingNetwork onError] robot with id: " + robot.getId() + " is not responding");
                        if(RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().containsKey(new CleaningRobot(robot.getId()))){
                            notifyAllPeersToRemoveRobotFromNetwork(robot.getId());
                            CleaningRobot.getInstance().sendMessageToServerToDeleteBrokenRobot(robot.getId());
                        }
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("[GRPC CLIENT][leavingNetwork onCompleted] completed - robot is deleted");
                    }
                });
            }
        });

        System.out.println("[GRPC CLIENT][leavingNetwork] robot with id: " + deletedRobotId + "has left Greenfield");

    }


}
