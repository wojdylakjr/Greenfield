package Server.beans;

import Robot.CleaningRobot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Robots {

    @XmlElement(name="robots")
    private Set<CleaningRobot> cleaningRobots;

    private static Robots instance;

    private Robots() {
        cleaningRobots = new HashSet<>();
    }

    //singleton
    public synchronized static Robots getInstance(){
        if(instance==null)
            instance = new Robots();
        return instance;
    }

    public synchronized Set<CleaningRobot> getRobotsSet() {
        return new HashSet<>(cleaningRobots);

    }

    public synchronized void setCleaningRobots(Set<CleaningRobot> cleaningRobots) {
        this.cleaningRobots = cleaningRobots;
    }

    public synchronized boolean add(CleaningRobot u){
        return cleaningRobots.add(u);
    }

    public synchronized boolean delete(int robotId){
        return cleaningRobots.remove(new CleaningRobot(robotId));
    }

    @Override
    public String toString() {
        return "Robots{" +
                "cleaningRobots=" + cleaningRobots +
                '}';
    }
}