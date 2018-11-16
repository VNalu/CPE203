public class Bigger {
    public static double whichIsBigger(Circle c, Rectangle r, Polygon p) {
        double biggestPerimeter = c.perimeter();
        double rPerimeter = r.perimeter();
        double pPerimeter = p.perimeter();
        if (rPerimeter > biggestPerimeter) {
            biggestPerimeter = rPerimeter;
        }
        else if (pPerimeter > biggestPerimeter) {
            biggestPerimeter = pPerimeter;
        }
        return biggestPerimeter;
    }
}