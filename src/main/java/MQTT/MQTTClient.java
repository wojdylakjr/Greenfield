package MQTT;

import Server.beans.SensorMeasurementsList;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

import java.sql.Timestamp;
import java.util.Scanner;

public abstract class MQTTClient  {
    protected String broker;
    protected String topic;
    protected int qualityOfService;
    protected String clientId;
    protected MqttClient client;


    protected MQTTClient(String broker, String topic, int qualityOfService) {
        this.broker = broker;
        this.topic = topic;
        this.qualityOfService = qualityOfService;

    }

//    public static MQTTClient createAndConnect(String broker, String topic, int qualityOfService) throws MqttException {
//        MQTTClient mqttClient = new MQTTClient(broker, topic, qualityOfService);
//        mqttClient.clientId = MqttClient.generateClientId();
//        mqttClient.client = new MqttClient(mqttClient.broker, mqttClient.clientId);
//        MqttConnectOptions connOpts = new MqttConnectOptions();
//        connOpts.setCleanSession(true);
//        // Connect the client
//        System.out.println(mqttClient.clientId + " Connecting Broker " + broker);
//        mqttClient.client.connect(connOpts);
//        System.out.println(mqttClient.clientId + " Connected");
//        return mqttClient;
//    }
    public  void connect() throws MqttException {
        //MQTTClient mqttClient = new MQTTClient(broker, topic, qualityOfService);
        this.clientId = MqttClient.generateClientId();
        this.client = new MqttClient(this.broker, this.clientId);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        // Connect the client
        System.out.println(this.clientId + " Connecting Broker " + this.broker);
        this.client.connect(connOpts);
        System.out.println(this.clientId + " Connected");
    }






    public void disconnectClient() {
        if (this.client.isConnected()) {
            try {
                this.client.disconnect();
                System.out.println("Publisher " + clientId + " disconnected");
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected String serializeMeasurementsToJson(SensorMessage measurements) {
        return new Gson().toJson(measurements);
    }
    protected static SensorMessage unserializeMeasurements(String sensorMessageInJson){
        return new Gson().fromJson(sensorMessageInJson, SensorMessage.class);
    }


}
