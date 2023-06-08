package Client;


import Robot.CleaningRobot;
import Server.beans.Robots;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.List;

public class HTTPClient {
    private final String serverURL;
    private final Client client;

    public HTTPClient(String serverURL) {
        this.serverURL = serverURL;
        this.client = Client.create();
    }

    public Robots getAllRobotsInGreenfield(){
        return getRequest("/robots").getEntity(Robots.class);
    }
    public ClientResponse postRequest(String path, String jsonPayload) {
        WebResource webResource = this.client.resource(this.serverURL + path);
        try {
            return webResource.type("application/json").post(ClientResponse.class, jsonPayload);
        } catch (ClientHandlerException e) {
            System.out.println("Server not available");
            return null;
        }
    }

    public  ClientResponse getRequest(String path){
        WebResource webResource = this.client.resource(this.serverURL + path);
        try {
            return webResource.type("application/json").get(ClientResponse.class);
        } catch (ClientHandlerException e) {
            System.out.println("Server not available");
            return null;
        }
    }

    public  ClientResponse deleteRequest(String path, int robotId){
        WebResource webResource = this.client.resource(this.serverURL + path + "/" + robotId);
        try {
            return webResource.type("application/json").delete(ClientResponse.class);
        } catch (ClientHandlerException e) {
            System.out.println("Server not available");
            return null;
        }
    }
}
