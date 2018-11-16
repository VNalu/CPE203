import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class PartTwoTestCases
{
   public static final double DELTA = 0.00001;

   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getCenter", "getRadius", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, double.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getTopLeft", "getBottomRight", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, Point.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getPoints", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         List.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0]);

      verifyImplSpecifics(Polygon.class, expectedMethodNames,
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
         0, Point.class.getFields().length);

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

   // MY TEST CASES

   @Test
   public void testPerimPoly() { // Polygon perimeter
        List <Point> points0 = new ArrayList<Point>();
        points0.add(new Point(0, 0));
        points0.add(new Point(2,0));
        points0.add(new Point(0,2));

        Polygon poly = new Polygon(points0);
        double d = poly.perimeter();
        assertEquals(6.82842712, d, DELTA);
   }

   @Test
   public void testPerimRect() { // Rectangle perimeter
        Point tl = new Point(0, 0);
        Point br = new Point(2, 2);
        Rectangle rect = new Rectangle(tl, br);
        
        double d = rect.perimeter();
        assertEquals(8.0, d, DELTA);
   }

   @Test
   public void testPerimCirc() { // Circle perimeter
        Point center = new Point(0, 0);
        Circle circ = new Circle(center, 2);
        
        double d = circ.perimeter();
        assertEquals(12.56637, d, DELTA);
   }

   @Test
   public void testBigger() { // Which is bigger
        // Circle
        Point center = new Point(0, 0);
        Circle circ = new Circle(center, 2);
        
        // Rectangle
        Point tl = new Point(0, 0);
        Point br = new Point(2, 2);
        Rectangle rect = new Rectangle(tl, br);

        // Polygon
        List <Point> points0 = new ArrayList<Point>(); 
        points0.add(new Point(0, 0));
        points0.add(new Point(2,0));
        points0.add(new Point(0,2));
        Polygon poly = new Polygon(points0);

        double biggestPerim = Bigger.whichIsBigger(circ, rect, poly);
        assertEquals(12.56637, biggestPerim, DELTA);
   }
}

