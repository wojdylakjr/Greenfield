package Server;

import MQTT.MQTTClient;
import MQTT.MQTTSubscriber;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;

/**
 * Created by civi on 26/04/16.
 */
public class StartServer {

    private static final String HOST = "localhost";
    private static final int PORT = 1337;


    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://"+HOST+":"+PORT+"/");
        server.start();

        System.out.println("Server running!");
        System.out.println("Server started on: http://"+HOST+":"+PORT);

        MQTTSubscriber subscriber = new MQTTSubscriber("tcp://localhost:1883", "greenfield/pollution/#",2);
        try {
            subscriber.connect();
            subscriber.subscribeToSensorMeasurements();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
