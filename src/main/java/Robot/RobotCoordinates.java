package Robot;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Random;

@XmlRootElement
public class RobotCoordinates {
    int x;
    int y;

    public RobotCoordinates() {
    }

    public RobotCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public District getDistrict(){
        if(x < 0 || x > 9 || y < 0 || y > 9){
            System.out.println("Wrong coordinates");
            return null;
        }
        else if(x < 5 && y < 5){
            return District.FIRST;
        }
        else if(x < 5 && y >= 5){
            return District.SECOND;
        }
        else if(x >= 5 && y >= 5){
            return District.THIRD;
        }
        else if(x >= 5 && y < 5){
            return District.FOURTH;
        }
        else{
            return null;
        }

    }
    public static RobotCoordinates getRandomCoordinatesInDistrict(District district){
        Random r = new Random();
        int x = r.nextInt(district.getMaxX() - district.getMinX()) + district.getMinX();
        int y = r.nextInt(district.getMaxY() - district.getMinY()) + district.getMinY();
        return new RobotCoordinates(x,y);
    }





    @Override
    public String toString() {
        return "RobotCoordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
