package Server;


import MQTT.SensorMessage;
import Robot.CleaningRobot;
import Robot.District;
import Server.beans.AddRobotResponse;
import Robot.RobotCoordinates;
import Server.beans.DistrictsOccupancy;
import Server.beans.Robots;
import Server.beans.SensorMeasurementsList;
import Simulators.Measurement;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("robots")
public class RobotsService {

    @GET
    @Produces({"application/json", "application/xml"})
    public Response getCleaningRobots() {
        return Response.ok(Robots.getInstance()).build();
    }

    @Path("all_measurements")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getSensorMeasurementsList() {
        return Response.ok(SensorMeasurementsList.getInstance()).build();
    }
    @Path("{numberOfLastMeasurements}/average_measurements")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getLastAverageSensorMeasurementsList(@PathParam("numberOfLastMeasurements")int numberOfLastMeasurements) {
        List<SensorMessage> sensorMeasurementsList = SensorMeasurementsList.getInstance().getSensorMeasurementsList();
        if(numberOfLastMeasurements>=sensorMeasurementsList.size()){
            return Response.noContent().build();
        }

            List<SensorMessage> sensorMessagesSubList = sensorMeasurementsList.subList(sensorMeasurementsList.size() - numberOfLastMeasurements - 1, sensorMeasurementsList.size() - 1);
        int numberOfMeasurements = 0;
        double measurementsSum = 0;
        for(SensorMessage message : sensorMessagesSubList){
            for(Measurement measurement : message.getAveragesMeasurements()){
                measurementsSum += measurement.getValue();
                ++numberOfMeasurements;
            }
        }
        double average = measurementsSum/numberOfMeasurements;

        return Response.ok(average).build();
    }
    @Path("{startTime}/{endTime}/average_measurements")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getAverageSensorMeasurementsListBetweenTimestamps(@PathParam("startTime")long startTime, @PathParam("endTime")long endTime) {
        List<SensorMessage> sensorMeasurementsList = SensorMeasurementsList.getInstance().getSensorMeasurementsList();
        List<SensorMessage> sensorMessagesSubList = new ArrayList<>();
        for(SensorMessage message : sensorMeasurementsList){
            if(message.getTimestamp()>startTime && message.getTimestamp()<endTime){
                sensorMessagesSubList.add(message);
            }
        }
        int numberOfMeasurements = 0;
        double measurementsSum = 0;
        for(SensorMessage message : sensorMessagesSubList){
            for(Measurement measurement : message.getAveragesMeasurements()){
                measurementsSum += measurement.getValue();
                ++numberOfMeasurements;
            }
        }
        double average = measurementsSum/numberOfMeasurements;

        return Response.ok(average).build();
    }

    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    public Response addCleaningRobot(CleaningRobot robot) throws URISyntaxException {
        if (Robots.getInstance().getRobotsSet().contains(robot)) {
            return Response.status(409).build();
        }
        Random r = new Random();
        District district = DistrictsOccupancy.getInstance().getDistrictToPlaceNewRobot();
        RobotCoordinates coordinates = RobotCoordinates.getRandomCoordinatesInDistrict(district);
        System.out.println("New robot with: " + coordinates + " in " + district);
        AddRobotResponse response = new AddRobotResponse(coordinates, Robots.getInstance().getRobotsSet());
        robot.setRobotCoordinates(coordinates);
        if (Robots.getInstance().add(robot)) {
            DistrictsOccupancy.getInstance().addRobot(coordinates);
            return Response.ok(response).build();
        }
        return Response.notModified().build();
    }

    @Path("delete/{robotId}")
    @DELETE
    public Response deleteCleaningRobotById(@PathParam("robotId") int robotId) {
        if (Robots.getInstance().delete(robotId)) {
            return Response.ok().build();
        }
        return Response.notModified().build();
    }

}