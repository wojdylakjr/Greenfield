package GRPC;

import Robot.CleaningRobot;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RobotsPeers {

  //  private Set<CleaningRobot> cleaningRobots;
    private Map<CleaningRobot, ManagedChannel> cleaningRobotManagedChannelMap;

    private static RobotsPeers instance;

    private RobotsPeers() {
       // cleaningRobots = new HashSet<>();
        cleaningRobotManagedChannelMap = new HashMap<>();
    }

    //singleton
    public synchronized static RobotsPeers getInstance() {
        if (instance == null) instance = new RobotsPeers();
        return instance;
    }

    public static boolean isNull() {
        return instance.cleaningRobotManagedChannelMap == null;
    }

//    public synchronized Set<CleaningRobot> getRobotsSet() {
//        return new HashSet<>(cleaningRobots);
//    }

    public synchronized Map<CleaningRobot, ManagedChannel> getCleaningRobotManagedChannelMap() {
        return new HashMap<>(cleaningRobotManagedChannelMap);
    }

    public synchronized void addCleaningRobots(Set<CleaningRobot> cleaningRobots) {
      //  this.cleaningRobots = cleaningRobots;
        cleaningRobots.forEach(robot -> cleaningRobotManagedChannelMap.put(robot, ManagedChannelBuilder.forTarget("localhost:" + robot.getListeningPort()).usePlaintext().build()));
    }

    public synchronized void add(CleaningRobot robot) {
      //  this.cleaningRobots.add(robot);
        this.cleaningRobotManagedChannelMap.put(robot, ManagedChannelBuilder.forTarget("localhost:" + robot.getListeningPort()).usePlaintext().build());
    }

    public synchronized void removeRobotById(int id){
        CleaningRobot robotToDelete = new CleaningRobot(id);
        if(this.getCleaningRobotManagedChannelMap().get(robotToDelete)!=null){
            this.cleaningRobotManagedChannelMap.get(robotToDelete).shutdown();
            this.cleaningRobotManagedChannelMap.remove(robotToDelete);
        }

    }

    @Override
    public String toString() {
        return "Robots{" + "cleaningRobots=" + cleaningRobotManagedChannelMap.keySet() + '}';
    }
}