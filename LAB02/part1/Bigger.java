public class Bigger {
    public static double whichIsBigger(Circle c, Rectangle r, Polygon p) {
        double biggestPerimeter = Util.perimeter(c);
        double rPerimeter = Util.perimeter(r);
        double pPerimeter = Util.perimeter(p);
        if (rPerimeter > biggestPerimeter) {
            biggestPerimeter = rPerimeter;
        }
        else if (pPerimeter > biggestPerimeter) {
            biggestPerimeter = pPerimeter;
        }
        return biggestPerimeter;
    }
}