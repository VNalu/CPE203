import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

public class Workspace {
    private ArrayList<Shape> shapes;

    public Workspace() {
        this.shapes = new ArrayList<Shape>();
    }
    public void add(Shape shape) {
        this.shapes.add(shape);
    }
    public int size() {
        return this.shapes.size();
    }
    public ArrayList<Circle> getCircles() {
        ArrayList<Circle> circles = new ArrayList<Circle>();
        for (Shape testShape : this.shapes) {
            if (testShape instanceof Circle) {
                circles.add((Circle)testShape);
            }
        }
        return circles;
    }
    public ArrayList<Rectangle> getRectangles() { // getRect()
        ArrayList<Rectangle> rect = new ArrayList<Rectangle>();
        for (Shape testShape : this.shapes) {
            if (testShape instanceof Rectangle) {
                rect.add((Rectangle)testShape);
            }
        }
        return rect;
    }
    public ArrayList<Triangle> getTriangles() {
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
        for (Shape testShape : this.shapes) {
            if (testShape instanceof Triangle) {
                triangles.add((Triangle)testShape);
            }
        }
        return triangles;
    }
    public ArrayList<ConvexPolygon> getConvexPolygons() {
        ArrayList<ConvexPolygon> convexPolygons = new ArrayList<ConvexPolygon>();
        for (Shape testShape : this.shapes) {
            if (testShape instanceof ConvexPolygon) {
                convexPolygons.add((ConvexPolygon)testShape);
            }
        }
        return convexPolygons;
    }

    public ArrayList<Shape> getShapesByColor(Color color) {
        ArrayList<Shape> colorShapes = new ArrayList<Shape>();
        for (Shape shape : this.shapes) {
            if (shape.getColor() == color) {
                colorShapes.add(shape);
            }
        }
        return colorShapes;
    }

    public double getAreaOfAllShapes() {
        double totalArea = 0.0;
        for (Shape shape : this.shapes) {
            totalArea += shape.getArea();
        }
        return totalArea;
    }


}