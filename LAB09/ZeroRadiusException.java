import java.io.*;

public class ZeroRadiusException extends CircleException {

    public ZeroRadiusException(){
        super("zero radius");
    }

}