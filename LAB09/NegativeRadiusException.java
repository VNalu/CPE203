import java.io.*;

public class NegativeRadiusException extends CircleException {

    private double radius;

    public NegativeRadiusException(Double radius){
        super("negative radius");
        this.radius = radius;
        System.out.println("negative radius: " + radius);
    }

    public double radius() {
        return radius;
    }

}