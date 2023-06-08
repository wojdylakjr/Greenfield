package Server.beans;

import MQTT.SensorMessage;
import Robot.CleaningRobot;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SensorMeasurementsList {
    private List<SensorMessage> measurements;
    private static SensorMeasurementsList instance;
    private SensorMeasurementsList() {
        measurements = new LinkedList<>();
    }
    //singleton
    public synchronized static SensorMeasurementsList getInstance(){
        if(instance==null)
            instance = new SensorMeasurementsList();
        return instance;
    }

    public synchronized List<SensorMessage> getSensorMeasurementsList() {
        return new LinkedList<>(measurements);

    }


    public synchronized boolean add(SensorMessage m){
        System.out.println("Adding new sensor message: " + m);
        return measurements.add(m);
    }


}
