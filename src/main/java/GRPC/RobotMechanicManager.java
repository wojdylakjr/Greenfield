package GRPC;

public class RobotMechanicManager {

    private RobotMechanicVisitState robotMechanicVisitState;

    private long requestToAccessMechanicTimestamp;
    private static RobotMechanicManager instance;

    private RobotMechanicManager(){
        robotMechanicVisitState = RobotMechanicVisitState.NOT_REQUIRING;
    }

    public synchronized static RobotMechanicManager getInstance() {
        if (instance == null) instance = new RobotMechanicManager();
        return instance;
    }

    public RobotMechanicVisitState getRobotMechanicVisitState() {
        return robotMechanicVisitState;
    }

    public void setRobotMechanicVisitState(RobotMechanicVisitState robotMechanicVisitState) {
        this.robotMechanicVisitState = robotMechanicVisitState;
    }

    public long getRequestToAccessMechanicTimestamp() {
        return requestToAccessMechanicTimestamp;
    }

    public void setRequestToAccessMechanicTimestamp(long requestToAccessMechanicTimestamp) {
        this.requestToAccessMechanicTimestamp = requestToAccessMechanicTimestamp;
    }

}
