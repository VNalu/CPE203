import java.util.List;

public class Util {
    public static double perimeter(Circle c) {
        return c.getRadius() * Math.PI;
    }
    public static double perimeter(Rectangle r) {
        double xSum = Math.abs(r.getTopLeft().getX() - r.getBottomRight().getX()) * 2;
        double ySum = Math.abs(r.getTopLeft().getY() - r.getBottomRight().getY()) * 2;
        return xSum + ySum;
    }
    public static double perimeter(Polygon p) {
        double lengthSum = 0;
        double xDif = 0;
        double yDif = 0;
        Point lastPoint = p.getPoints().get(0);
        // Point list instead of multiple method calls
        List<Point> polyVertices = p.getPoints();

        for (int i=1; i<polyVertices.size(); i++) {
            xDif = Math.abs((polyVertices.get(i)).getX() - lastPoint.getX());
            yDif = Math.abs((polyVertices.get(i)).getY() - lastPoint.getY());
            lengthSum += Math.sqrt(xDif*xDif + yDif*yDif);
            lastPoint = polyVertices.get(i);
        }
        xDif = Math.abs(lastPoint.getX() - (polyVertices.get(0)).getX());
        yDif = Math.abs(lastPoint.getY() - (polyVertices.get(0)).getY());
        lengthSum += Math.sqrt(xDif*xDif + yDif*yDif);

        return lengthSum;
    }
}