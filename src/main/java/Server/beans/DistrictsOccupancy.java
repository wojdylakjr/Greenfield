package Server.beans;

import MQTT.SensorMessage;
import Robot.District;
import Robot.RobotCoordinates;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DistrictsOccupancy {
    private Integer[] districts = {0,0,0,0};
    private static DistrictsOccupancy instance;
    private DistrictsOccupancy() {
    }
    //singleton
    public synchronized static DistrictsOccupancy getInstance(){
        if(instance==null)
            instance = new DistrictsOccupancy();
        return instance;
    }

//    public synchronized List<SensorMessage> getSensorMeasurementsList() {
//        return new LinkedList<>(measurements);
//
//    }


    public synchronized void addRobot(RobotCoordinates coordinates){
        District district = coordinates.getDistrict();
        if(district != null){
            districts[district.getDistrictNumber() - 1]++;
        }

    }

    public synchronized void removeRobot(RobotCoordinates coordinates){
        districts[coordinates.getDistrict().getDistrictNumber() - 1]--;
    }

    public District getDistrictToPlaceNewRobot(){
        int districtIndexWithMinNumberOfRobots = 0;
        int min = this.districts[0];
        for(int i = 1; i < districts.length; i++){
            if(this.districts[i] < min){
                min = this.districts[i];
                districtIndexWithMinNumberOfRobots = i;
            }
        }
            return  District.getEnumByNumber(districtIndexWithMinNumberOfRobots + 1) ;

    }


}
