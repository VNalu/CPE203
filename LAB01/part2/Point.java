public class Point {

    private double pointX;
    private double pointY;

    // Constructor
    public Point() {
        pointX = 1.0;
        pointY = 1.0;
    } 
    public Point(double x, double y) {
        pointX = x;
        pointY = y;
    } 

    // Returns the x-coordinate of this point
    public double getX() {
        return pointX;
    }

    // Returns the y-coordinate of this point
    public double getY() {
        return pointY;
    }

    // Returns the distance from the origin to the point
    public double getRadius() {
        return Math.sqrt(pointX*pointX + pointY*pointY);
    }

    // Returns the counterclockwise angle (in radians) from the x-axis.
    // Remember your trigonometry -
    // the tangent of and angle is opposite divided by adjacent.
    // And if you haven't learned about the atan2( )
    // method in the Math library this is a good time.
    public double getAngle() {
        return Math.tan(pointY/pointX);
    }

    // Returns a newly created Point representing a 90-degree
    // (counterclockwise) rotation of this point about the origin
    public Point rotate90() {
        double newX = pointX*Math.cos(1.5708) - pointY*Math.sin(1.5708);
        double newY = pointX*Math.sin(1.5708) + pointY*Math.cos(1.5708);
        Point rotated = new Point(newX, newY);
        return rotated;
    }
}

 