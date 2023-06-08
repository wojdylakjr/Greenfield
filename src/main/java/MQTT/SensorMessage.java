package MQTT;

import Simulators.Measurement;

import java.util.ArrayList;
import java.util.List;

public class SensorMessage {
    private List<Measurement> averagesMeasurements;
    private int robotId;
    private long timestamp;

    public SensorMessage() {
    }

    public SensorMessage(List<Measurement> averagesMeasurements, int robotId, long timestamp) {
        this.averagesMeasurements = averagesMeasurements;
        this.robotId = robotId;
        this.timestamp = timestamp;
    }

    public List<Measurement> getAveragesMeasurements() {
        return averagesMeasurements;
    }

    public void setAveragesMeasurements(ArrayList<Measurement> averagesMeasurements) {
        this.averagesMeasurements = averagesMeasurements;
    }

    public int getRobotId() {
        return robotId;
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SensorMessage{" +
                "averagesMeasurements=" + averagesMeasurements +
                ", robotId=" + robotId +
                ", timestamp=" + timestamp +
                '}';
    }
}
