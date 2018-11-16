import java.util.List;
import processing.core.PImage;

public abstract class Miner extends ActiveEntity {

    public Miner(String id, Point position, List<PImage> images,
    int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    } 

    public Point nextPositionMiner(WorldModel world, Point destPos) {
        // int horiz = Integer.signum(destPos.x - this.getPosition().x); // returns -1, 0, or 1
        // Point newPos = new Point(this.getPosition().x + horiz,
        //     this.getPosition().y);
    
        // if (horiz == 0 || world.isOccupied(newPos)) {
        //     int vert = Integer.signum(destPos.y - this.getPosition().y); // returns -1, 0, or 1
        //     newPos = new Point(this.getPosition().x,
        //         this.getPosition().y + vert);
    
        //     if (vert == 0 || world.isOccupied(newPos)) {
        //         newPos = this.getPosition();
        //     }
        // }
    
        // return newPos;

        List<Point> pathPoints = SingleStepPathingStrategy.computePath(this.getPosition(), destPos,
            Predicate<Point> canPassThrough,
            BiPredicate<Point, Point> withinReach,
            CARDINAL_NEIGHBORS);
        
        return pathPoints[0];
    }
    
}
