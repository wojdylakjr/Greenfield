import CLI.CommandLineManager;
import GRPC.Client;
import GRPC.Server;
import Robot.CleaningRobot;

import java.util.Random;

public class Robot1 {
    public static void main(String args[]) throws InterruptedException {
        CleaningRobot robot = CleaningRobot.createAndRegister(1, 8181, "http://localhost:1337");

        if (robot != null) {
            Client grpcClient = new Client();
            Thread commandLineManagerThread = new Thread(new CommandLineManager(grpcClient, robot.getId()));
            commandLineManagerThread.start();

            robot.startPublishingSensorMeasurements(); //is it better to start new thread in method like that or to do it here, like under

            Thread grpcServerThread = new Thread(new Server(robot.getListeningPort()));
            grpcServerThread.start();
            grpcClient.broadcastRobotToIntroduceInNetwork(robot.getId(), robot.getListeningPort(), robot.getRobotCoordinates().getX(), robot.getRobotCoordinates().getY());

            Random r = new Random();
            while (robot.isRobotConnected()) {
                if (r.nextInt(9) == 5) {
                    System.out.println("[MAIN] Robot " + robot.getId() + "wants to go to mechanic");
                    grpcClient.sendRequestToUseCriticalSectionTest(robot.getId());
                }
                Thread.sleep(5000);
            }

        } else {
            System.out.println("Robot was not added");
        }

    }
}
