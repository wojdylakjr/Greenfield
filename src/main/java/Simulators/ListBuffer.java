package Simulators;

import java.util.ArrayList;
import java.util.List;

public class ListBuffer implements Buffer {
    private ArrayList<Measurement> measurements;

    public ListBuffer() {
        measurements = new ArrayList<>();
    }

    @Override
    public void addMeasurement(Measurement m) {
        measurements.add(m);
    }

    @Override
    public List<Measurement> readAllAndClean() {
        //sliding window with 8 buffer and 50% overlap
        //counting average by countig average of sum of two halfs of sliding window, return counted averages as new measurments
        int bufferSize = 8;

        if (measurements.size() < bufferSize) {
            return new ArrayList<>();
        }
        ArrayList<Measurement> averages = new ArrayList<>();
        double firstBufferHalf = 0;
        double secondBufferHalf = 0;

        int index = 0;
        for (; index < bufferSize / 2; index++) {
            firstBufferHalf += measurements.get(index).getValue();
        }
        int j = 0;
        while (index < measurements.size() - bufferSize / 2) {
        j = 0;
            while (j < bufferSize / 2) {
                secondBufferHalf += measurements.get(index++).getValue();
                j++;
            }
            averages.add(new Measurement(measurements.get(index).getId(), "Average", (firstBufferHalf + secondBufferHalf) / bufferSize, System.currentTimeMillis()));
            firstBufferHalf = secondBufferHalf;
            secondBufferHalf = 0;
        }
        measurements.clear();
        return averages;
    }
}
