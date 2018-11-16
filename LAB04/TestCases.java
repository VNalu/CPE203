import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /* put your tests here */
   //Circle Tests
   @Test
   public void testGetColorCircle()
   {
        Circle c1 = new Circle(2, new Point(0, 0), Color.GREEN);
        assertEquals(c1.getColor(), Color.GREEN);
        c1.setColor(Color.RED);
        assertEquals(c1.getColor(), Color.RED);
   }
   @Test
   public void testGetRadiusCircle()
   {
        Circle c2 = new Circle(2, new Point(0, 0), Color.GREEN);
        assertEquals(c2.getRadius(), 2, DELTA);
        c2.setRadius(10);
        assertEquals(c2.getRadius(), 10, DELTA);
   }
   @Test
   public void testGetPerimeterCircle()
   {
        Circle c2 = new Circle(2, new Point(0, 0), Color.GREEN);
        assertEquals(c2.getPerimeter(), 4*Math.PI, DELTA);
   }
   @Test
   public void testGetAreaCircle()
   {
        Circle c2 = new Circle(2, new Point(0, 0), Color.GREEN);
        assertEquals(c2.getArea(), 4*Math.PI, DELTA);
   }
   @Test
   public void testTranslateCircle()
   {
        Circle c2 = new Circle(2, new Point(0, 0), Color.GREEN);
        assertEquals(c2.getCenter(), new Point(0, 0));
        c2.translate(new Point(2, 2));
        assertEquals(c2.getCenter(), new Point(2, 2));
   }
   // Rectangle Tests
   @Test
   public void testGetUpperLeft()
   {
        Rectangle r1 = new Rectangle(2, 2, new Point(0, 0), Color.GREEN);
        assertEquals(r1.getUpperLeft(), new Point(0, 0));
   }
   @Test
   public void testGetWidthRectangle()
   {
        Rectangle r1 = new Rectangle(3, 2, new Point(0, 0), Color.GREEN);
        assertEquals(r1.getWidth(), 3, DELTA);
        r1.setWidth(8);
        assertEquals(r1.getWidth(), 8, DELTA);
   }
   @Test
   public void testGetHeightRectangle()
   {
        Rectangle r1 = new Rectangle(3, 2, new Point(0, 0), Color.GREEN);
        assertEquals(r1.getHeight(), 2, DELTA);
        r1.setHeight(8);
        assertEquals(r1.getHeight(), 8, DELTA);
   }
   @Test
   public void testTranslateRectangle()
   {
        Rectangle r1 = new Rectangle(3, 2, new Point(0, 0), Color.GREEN);
        assertEquals(r1.getUpperLeft(), new Point(0, 0));
        r1.translate(new Point(4, 4));
        assertEquals(r1.getUpperLeft(), new Point(4, 4));
   }
   @Test
   public void testTriangleColor()
   {
        Triangle t1 = new Triangle(new Point(2, 0), new Point(2, 0), new Point(0, 0), Color.GREEN);
        assertEquals(t1.getColor(), Color.GREEN);
        t1.setColor(Color.BLUE);
        assertEquals(t1.getColor(), Color.BLUE);
   }
   @Test
   public void testTriangleArea()
   {
        Triangle t1 = new Triangle(new Point(20, 0), new Point(0, 20), new Point(0, 0), Color.GREEN);
        assertEquals(t1.getArea(), 200, DELTA);
   }
   @Test
   public void testTrianglePerimeter()
   {
        Triangle t1 = new Triangle(new Point(3, 0), new Point(0, 4), new Point(0, 0), Color.GREEN);
        assertEquals(t1.getPerimeter(), 12, DELTA);
   }
   @Test
   public void testTriangleTranslate()
   {
        Triangle t1 = new Triangle(new Point(1, 0), new Point(0, 1), new Point(0, 0), Color.GREEN);
        assertEquals(t1.getVertexA(), new Point(1, 0));
        assertEquals(t1.getVertexB(), new Point(0, 1));
        assertEquals(t1.getVertexC(), new Point(0, 0));
        t1.translate(new Point(1, 1));
        assertEquals(t1.getVertexA(), new Point(2, 1));
        assertEquals(t1.getVertexB(), new Point(1, 2));
        assertEquals(t1.getVertexC(), new Point(1, 1));
   }
   @Test
   public void testgetColorPolygon()
   {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(0, 0));
        pts.add(new Point(0, 1));
        pts.add(new Point(1, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        assertEquals(cp1.getColor(), Color.GREEN);
   }
   @Test
   public void testgetPointsPolygon()
   {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(0, 0));
        pts.add(new Point(0, 1));
        pts.add(new Point(1, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        assertEquals(cp1.getVertex(0), new Point(0, 0));
        assertEquals(cp1.getVertex(1), new Point(0, 1));
        assertEquals(cp1.getVertex(2), new Point(1, 0));
   }
   @Test
   public void testTranslatePolygon()
   {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(0, 0));
        pts.add(new Point(0, 1));
        pts.add(new Point(1, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        assertEquals(cp1.getVertex(0), new Point(0, 0));
        ArrayList<Point> pts2 = new ArrayList<Point>();
        cp1.translate(new Point(1, 1));
        assertEquals(cp1.getVertex(0), new Point(1, 1));
        assertEquals(cp1.getVertex(1), new Point(1, 2));
   }
   @Test
   public void testGetPerimeterPolygon()
   {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(0, 0));
        pts.add(new Point(0, 3));
        pts.add(new Point(4, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        assertEquals(cp1.getPerimeter(), 12, DELTA);
   }
   @Test
   public void testGetAreaPolygon()
   {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 2));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        pts.add(new Point(2, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        assertEquals(cp1.getArea(), 4, DELTA);
   }
   @Test
   public void testGetArea2Polygon()
   {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        assertEquals(cp1.getArea(), 2, DELTA);
   }

   // Workspace
   @Test
   public void testSizeWorkspace()
   {
        Workspace shapes = new Workspace();
        shapes.add(new Circle(2, new Point(0, 0), Color.WHITE));
        shapes.add(new Rectangle(5, 5, new Point(0, 0), Color.GRAY));
        shapes.add(new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4), Color.RED));
        // Convex Polygon points
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        shapes.add(new ConvexPolygon(pts, Color.GREEN));
        assertEquals(shapes.size(), 4);
   }
   @Test
   public void testGetCirclesWorkspace()
   {
        Workspace shapes = new Workspace();
        Circle c1 = new Circle(2, new Point(0, 0), Color.WHITE);
        shapes.add(c1);
        shapes.add(new Rectangle(5, 5, new Point(0, 0), Color.GRAY));
        shapes.add(new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4), Color.RED));
        Circle c2 = new Circle(10, new Point(-10, 2), Color.RED);
        shapes.add(c2);
        // Convex Polygon points
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        shapes.add(new ConvexPolygon(pts, Color.GREEN));

        //Circle ArrayList
        ArrayList<Circle> circles = new ArrayList<Circle>();
        circles.add(c1);
        circles.add(c2);
        assertEquals(shapes.getCircles(), circles);
   }
   @Test
   public void testGetRectanglesWorkspace()
   {
        Workspace shapes = new Workspace();
        Circle c1 = new Circle(2, new Point(0, 0), Color.WHITE);
        shapes.add(c1);
        Rectangle r1 = new Rectangle(5, 5, new Point(0, 0), Color.GRAY);
        shapes.add(r1);
        Triangle t1 = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4), Color.RED);
        shapes.add(t1);
        Rectangle r2 = new Rectangle(50, 5, new Point(10, 10), Color.BLUE);
        shapes.add(r2);
        // Convex Polygon points
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        shapes.add(new ConvexPolygon(pts, Color.GREEN));

        //Rectangle ArrayList
        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        rectangles.add(r1);
        rectangles.add(r2);
        assertEquals(shapes.getRectangles(), rectangles);
   }
   @Test
   public void testGetTrianglesWorkspace()
   {
        Workspace shapes = new Workspace();
        Circle c1 = new Circle(2, new Point(0, 0), Color.WHITE);
        shapes.add(c1);
        Rectangle r1 = new Rectangle(5, 5, new Point(0, 0), Color.GRAY);
        shapes.add(r1);
        Triangle t1 = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4), Color.RED);
        shapes.add(t1);
        Triangle t2 = new Triangle(new Point(110, 10), new Point(13, -9), new Point(20, 4), Color.BLACK);
        shapes.add(t2);
        // Convex Polygon points
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        shapes.add(new ConvexPolygon(pts, Color.GREEN));

        //Triangles ArrayList
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
        triangles.add(t1);
        triangles.add(t2);
        assertEquals(shapes.getTriangles(), triangles);
   }
   @Test
   public void testGetConvexPolygonWorkspace()
   {
        Workspace shapes = new Workspace();
        Circle c1 = new Circle(2, new Point(0, 0), Color.WHITE);
        shapes.add(c1);
        Rectangle r1 = new Rectangle(5, 5, new Point(0, 0), Color.GRAY);
        shapes.add(r1);
        Triangle t1 = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4), Color.RED);
        shapes.add(t1);
        // Convex Polygon points
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        shapes.add(cp1);
        ArrayList<Point> pts2 = new ArrayList<Point>();
        pts.add(new Point(2, 30));
        pts.add(new Point(-90, 2));
        pts.add(new Point(4, 0));
        pts.add(new Point(90, 20));
        ConvexPolygon cp2 = new ConvexPolygon(pts2, Color.WHITE);
        shapes.add(cp2);

        //Triangles ArrayList
        ArrayList<ConvexPolygon> convexPolygons = new ArrayList<ConvexPolygon>();
        convexPolygons.add(cp1);
        convexPolygons.add(cp2);
        assertEquals(shapes.getConvexPolygons(), convexPolygons);
   }

   @Test
   public void testGetShapesByColorWorkspace()
   {
        Workspace shapes = new Workspace();
        Circle c1 = new Circle(2, new Point(0, 0), Color.WHITE);
        shapes.add(c1);
        Rectangle r1 = new Rectangle(5, 5, new Point(0, 0), Color.GRAY);
        shapes.add(r1);
        Triangle t1 = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4), Color.RED);
        shapes.add(t1);
        Circle c2 = new Circle(10, new Point(-10, 2), Color.RED);
        shapes.add(c2);
        Rectangle r2 = new Rectangle(50, 5, new Point(10, 10), Color.BLUE);
        shapes.add(r2);
        Triangle t2 = new Triangle(new Point(110, 10), new Point(13, -9), new Point(20, 4), Color.BLACK);
        shapes.add(t2);
        // Convex Polygon points
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        ConvexPolygon cp1 = new ConvexPolygon(pts, Color.GREEN);
        shapes.add(cp1);
        ArrayList<Point> pts2 = new ArrayList<Point>();
        pts.add(new Point(2, 30));
        pts.add(new Point(-90, 2));
        pts.add(new Point(4, 0));
        pts.add(new Point(90, 20));
        ConvexPolygon cp2 = new ConvexPolygon(pts2, Color.WHITE);
        shapes.add(cp2);

        //WHITE ArrayList
        ArrayList<Shape> whiteShapes = new ArrayList<Shape>();
        whiteShapes.add(c1);
        whiteShapes.add(cp2);
        assertEquals(shapes.getShapesByColor(Color.WHITE), whiteShapes);
   }
   @Test
   public void testGetAreaOfAllShapesWorkspace()
   {
        Workspace shapes = new Workspace();
        Circle c1 = new Circle(2, new Point(0, 0), Color.WHITE);
        shapes.add(c1);
        Rectangle r1 = new Rectangle(5, 5, new Point(0, 0), Color.GRAY);
        shapes.add(r1);
        Triangle t1 = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4), Color.RED);
        shapes.add(t1);
        Triangle t2 = new Triangle(new Point(110, 10), new Point(13, -9), new Point(20, 4), Color.BLACK);
        shapes.add(t2);
        // Convex Polygon points
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(2, 0));
        pts.add(new Point(0, 2));
        pts.add(new Point(0, 0));
        shapes.add(new ConvexPolygon(pts, Color.GREEN));
        assertEquals(shapes.getAreaOfAllShapes(), 609.56637, DELTA);
   }



   /* HINT - comment out implementation tests for the classes that you have not yet implemented */
   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getRadius", "setRadius", "getCenter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getWidth", "setWidth", "getHeight", "setHeight", "getUpperLeft");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class}, new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testTriangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertexA", "getVertexB", "getVertexC");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class, Point.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testConvexPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertex");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[] {int.class});

      verifyImplSpecifics(ConvexPolygon.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
