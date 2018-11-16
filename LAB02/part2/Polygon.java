import java.util.*;

public class Polygon {
   private List<Point> vertices;

   public Polygon(List<Point> points) {
      vertices = new ArrayList<Point>();
      for (int i=0; i<points.size(); i++) {
         vertices.add(points.get(i));
      }
   }

   public List<Point> getPoints() {
       return vertices;
   }

    public double perimeter() {
        double lengthSum = 0;
        double xDif = 0;
        double yDif = 0;
        Point lastPoint = this.getPoints().get(0);
        // Point list instead of multiple method calls
        List<Point> polyVertices = this.getPoints();

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
