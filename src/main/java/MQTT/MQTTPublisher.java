package MQTT;

import Robot.CleaningRobot;
import Simulators.ListBuffer;
import Simulators.Measurement;
import Simulators.PM10Simulator;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class MQTTPublisher extends MQTTClient implements Runnable{
    private final int robotId;

    public MQTTPublisher(String broker, String topic, int qualityOfService, int robotId) {
        super(broker, topic, qualityOfService);
        this.robotId = robotId;
    }

    @Override
    public void run() {
        try {
            this.connect();
        } catch (MqttException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        ListBuffer listBuffer = new ListBuffer();
        PM10Simulator pm10Simulator = new PM10Simulator(listBuffer);
        pm10Simulator.start();
        while (true) {
            List<Measurement> measurements = listBuffer.readAllAndClean();
            this.publishMeasurements(new SensorMessage(measurements, this.robotId, System.currentTimeMillis()));
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void publishMeasurements(SensorMessage measurements) {
        try {
            String message = serializeMeasurementsToJson(measurements);
            MqttMessage byteMessage = new MqttMessage(message.getBytes());

            // Set the QoS on the Message
            byteMessage.setQos(this.qualityOfService);
          //  System.out.println(this.clientId + " Publishing message: " + message + " ...");
            this.client.publish(this.topic, byteMessage);
            //System.out.println(this.clientId + " Message published");
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnectClient(){
        if (client.isConnected()) {
            try {
                client.disconnect();
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("[MQTT Publisher] Publisher " + clientId + " disconnected");
    }

}
