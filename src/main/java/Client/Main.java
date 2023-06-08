package Client;


import Server.beans.Robots;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        HTTPClient client = new HTTPClient("http://localhost:1337");
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            printMenu();
            switch (userChoice = scanner.nextInt()){
                case 1:
                    System.out.println(client.getAllRobotsInGreenfield());
                    break;
                case 2:
                    System.out.println("2");
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
        System.out.println("1. The list of the cleaning robots currently located in Greenfield");
        System.out.println("2. The average of the last n air pollution levels sent to the server by a\n" +
                "   given robot");
        System.out.println("3. The average of the air pollution levels sent by all the robots to the\n" +
                "   server and occurred from timestamps t1 and t2");
        System.out.println("0. Press 0 to exit");
    }
}
