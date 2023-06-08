package Robot;

import GRPC.Client;
import GRPC.RobotsPeers;
import Client.HTTPClient;
import MQTT.MQTTPublisher;
import Server.beans.AddRobotResponse;
import com.google.gson.Gson;

import com.sun.jersey.api.client.ClientResponse;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;

@XmlRootElement
public class CleaningRobot {
    private int id;
    private int listeningPort;
    private String administratorServerAddress;
    private RobotCoordinates robotCoordinates;
    @XmlTransient
    private boolean isConnected = true;


    private transient MQTTPublisher publisher;


    // private Set<CleaningRobot> otherRobots;
    private static CleaningRobot instance;


    public CleaningRobot() {
    }

    public CleaningRobot(int id) {
        this.id = id;
    }

    public CleaningRobot(int id, int listeningPort, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.listeningPort = listeningPort;
        this.robotCoordinates = new RobotCoordinates(xCoordinate, yCoordinate);
    }

    private CleaningRobot(int id, int listeningPort, String administratorServerAddress) {
        this.id = id;
        this.listeningPort = listeningPort;
        this.administratorServerAddress = administratorServerAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListeningPort() {
        return listeningPort;
    }

    public void setListeningPort(int listeningPort) {
        this.listeningPort = listeningPort;
    }

    public String getAdministratorServerAddress() {
        return administratorServerAddress;
    }

    public void setAdministratorServerAddress(String administratorServerAddress) {
        this.administratorServerAddress = administratorServerAddress;
    }

    public RobotCoordinates getRobotCoordinates() {
        return robotCoordinates;
    }

    public void setRobotCoordinates(RobotCoordinates robotCoordinates) {
        this.robotCoordinates = robotCoordinates;
    }

    public boolean isRobotConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public static CleaningRobot createAndRegister(int id, int listeningPort, String administratorServerAddress) {
        instance = new CleaningRobot(id, listeningPort, administratorServerAddress);
        if (registerNewRobotAtServer(instance)) {
            RobotsPeers.getInstance().add(instance);
            System.out.println("New robot was added");
            return instance;
        }
        return null;
    }


    public synchronized static CleaningRobot getInstance() {
        if (instance == null) return null;
        return instance;
    }

    private static boolean registerNewRobotAtServer(CleaningRobot robot) {
        ClientResponse clientResponse;
        String postPath = "/robots/add";
        clientResponse = new HTTPClient(robot.administratorServerAddress).postRequest(postPath, new Gson().toJson(robot));
        System.out.println(clientResponse.toString());

        if (clientResponse.getStatusInfo().getStatusCode() == 200) {
            AddRobotResponse addRobotResponse = clientResponse.getEntity(AddRobotResponse.class);
            robot.setRobotCoordinates(addRobotResponse.getCoordinates());
            if (addRobotResponse.getOtherRobots() != null) {
                RobotsPeers.getInstance().addCleaningRobots(addRobotResponse.getOtherRobots());
            }
            return true;
        }
        return false;
    }

    public void startPublishingSensorMeasurements() {
        this.publisher = new MQTTPublisher("tcp://localhost:1883",
                "greenfield/pollution/district" + this.robotCoordinates.getDistrict().getDistrictNumber(),
                2, this.id);

        Thread publisherThread = new Thread(this.publisher);
        publisherThread.start();
    }

    public void leaveGreenfield() {
        new Client().notifyAllPeersToRemoveRobotFromNetwork(this.id);
        //this.publisher.disconnectClient();
        isConnected = false;
        CleaningRobot robotToDelete = new CleaningRobot(id);
        RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().get(robotToDelete).shutdown();
        RobotsPeers.getInstance().getCleaningRobotManagedChannelMap().remove(robotToDelete);
        sendMessageToServerToDeleteBrokenRobot(this.id);
    }

    public void sendMessageToServerToDeleteBrokenRobot(int robotId) {
        ClientResponse clientResponse;
        String postPath = "/robots/delete";
        clientResponse = new HTTPClient(this.administratorServerAddress).deleteRequest(postPath, robotId);
        System.out.println(clientResponse.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleaningRobot robot = (CleaningRobot) o;
        return id == robot.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CleaningRobot{" + "id=" + id + ", listeningPort=" + listeningPort + ", administratorServerAddress='" + administratorServerAddress + '\'' + ", robotCoordinates=" + robotCoordinates + '}';
    }
}
