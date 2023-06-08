package MQTT;

import Server.beans.SensorMeasurementsList;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.Timestamp;

public class MQTTSubscriber extends MQTTClient{
    public MQTTSubscriber(String broker, String topic, int qualityOfService) {
        super(broker, topic, qualityOfService);
    }
    public void subscribeToSensorMeasurements() {
        client.setCallback(new MqttCallback() {

            public void messageArrived(String topic, MqttMessage message) {
                // Called when a message arrives from the server that matches any subscription made by the client
                String time = new Timestamp(System.currentTimeMillis()).toString();
                String receivedMessage = new String(message.getPayload());
                SensorMeasurementsList.getInstance().add(unserializeMeasurements(receivedMessage));
                System.out.println(clientId + " Received a Message! - Callback - Thread PID: " + Thread.currentThread().getId() +
                        "\n\tTime:    " + time +
                        "\n\tTopic:   " + topic +
                        "\n\tMessage: " + receivedMessage +
                        "\n\tQoS:     " + message.getQos() + "\n");

                System.out.println("\n ***  Press a random key to exit *** \n");

            }

            public void connectionLost(Throwable cause) {
                System.out.println(clientId + " Connection lost! cause:" + cause.getMessage() + "-  Thread PID: " + Thread.currentThread().getId());
            }

            public void deliveryComplete(IMqttDeliveryToken token) {
                // Not used here
            }

        });
        System.out.println(clientId + " Subscribing ... - Thread PID: " + Thread.currentThread().getId());
        try {
            client.subscribe(topic, qualityOfService);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        System.out.println(clientId + " Subscribed to topics : " + topic);
    }
}
