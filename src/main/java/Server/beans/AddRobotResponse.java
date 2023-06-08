package Server.beans;

import Robot.CleaningRobot;
import Robot.RobotCoordinates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddRobotResponse {
    private RobotCoordinates coordinates;
//    @XmlElement(name="robots")
    private Set<CleaningRobot> otherRobots;


    public AddRobotResponse() {
    }

    public AddRobotResponse(RobotCoordinates coordinates, Set<CleaningRobot> otherRobots) {
        this.coordinates = coordinates;
        this.otherRobots = otherRobots;
    }

    public RobotCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(RobotCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Set<CleaningRobot> getOtherRobots() {
        return otherRobots;
    }

    public void setOtherRobots(Set<CleaningRobot> otherRobots) {
        this.otherRobots = otherRobots;
    }
}
