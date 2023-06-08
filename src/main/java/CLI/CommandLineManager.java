package CLI;

import GRPC.Client;
import Robot.CleaningRobot;

import java.util.Scanner;

public class CommandLineManager implements Runnable{
    private final Client grpcClient;
    private final int robotId;

    public CommandLineManager(Client grpcClient, int robotId) {
        this.grpcClient = grpcClient;
        this.robotId = robotId;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            printMenu();
            switch (userChoice = scanner.nextInt()){
                case 1:
                    System.out.println("[COMMAND LINE] Robot " + this.robotId + " wants to go to mechanic");
                    grpcClient.sendRequestToUseCriticalSectionTest(this.robotId);
                    break;
                case 2:
                    System.out.println("[COMMAND LINE] Robot " + this.robotId + " wants to leave Greenfield");
                    CleaningRobot.getInstance().leaveGreenfield();
                    break;
                case 3:
                    System.out.println("3");
                    break;
                default:
                    System.out.println("Wrong option");
            }
        } while (userChoice != 0);
    }


    private static void printMenu(){
        System.out.println("1. Send robot to mechanic");
        System.out.println("2. Leave Greenfield");
        System.out.println("0. Press 0 to exit");
    }
}
