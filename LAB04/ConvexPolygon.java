import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;

public class ConvexPolygon implements Shape {
    private ArrayList<Point> points;
    private Color color;

    public ConvexPolygon(ArrayList<Point> points, Color color) {
        this.points = points;
        this.color = color;
    }

    public Color getColor() { return color; }
    public void setColor(Color c) { color = c; }

    public Point getVertex(int ind) {return points.get(ind);}

    public void translate(Point newPoint) {
        ArrayList<Point> newVertices = new ArrayList<Point>();
        for (Point vertex : points) {
            int newX = (int)(vertex.getX() + newPoint.getX());
            int newY = (int)(vertex.getY() + newPoint.getY());
            newVertices.add(new Point(newX, newY));
        }
        points = newVertices;
    }

    public double getPerimeter() {
        double lengthSum = 0;
        double xDif = 0;
        double yDif = 0;
        ArrayList<Point> polyVertices = this.points;
        Point lastPoint = polyVertices.get(0);
        // Point list instead of multiple method calls

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

    public double getArea() {
        double totalArea = 0.0;
        int i2 = 1;
        int lastidx = (this.points.size() - 1);
        for (int i1=0; i1<lastidx; i1++) {
            i2 = i1 + 1;
            totalArea += (this.points.get(i1).getX()*this.points.get(i2).getY()) - (this.points.get(i1).getY()*this.points.get(i2).getX());
        }
        totalArea += (this.points.get(lastidx).getX()*this.points.get(0).getY()) - (this.points.get(lastidx).getY()*this.points.get(0).getX());
        return totalArea/2;
    }
}