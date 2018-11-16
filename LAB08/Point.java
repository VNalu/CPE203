public class Point {

    private double x;
    private double y;
    private double z;

    // Constructor
    public Point() {
        x = 1.0;
        y = 1.0;
        z = 1.0;
    } 
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    } 

    // Returns the x-coordinate of this point
    public double getX() {
        return x;
    }

    // Returns the y-coordinate of this point
    public double getY() {
        return y;
    }

    // Returns the z-coordinate of this point
    public double getZ() {
        return z;
    }

}

 