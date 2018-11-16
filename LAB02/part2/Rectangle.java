public class Rectangle {
    private final Point topLeft;
    private final Point bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public double perimeter() {
        double xSum = Math.abs(this.getTopLeft().getX() - this.getBottomRight().getX()) * 2;
        double ySum = Math.abs(this.getTopLeft().getY() - this.getBottomRight().getY()) * 2;
        return xSum + ySum;
    }
}