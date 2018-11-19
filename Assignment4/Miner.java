import java.util.List;
import processing.core.PImage;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Miner extends ActiveEntity {

    public Miner(String id, Point position, List<PImage> images,
    int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    } 

    public Point nextPositionMiner(WorldModel world, Point destPos) {

        SingleStepPathingStrategy ssps = new SingleStepPathingStrategy();

        List<Point> pathPoints = ssps.computePath(this.getPosition(), destPos,
            (p -> world.withinBounds(p) && !(world.isOccupied(p))),
            ((p1, p2) -> p1.adjacent(p2)),
            PathingStrategy.CARDINAL_NEIGHBORS);
        
            if (pathPoints.isEmpty()){
                return this.getPosition();
            }
            
        return pathPoints.get(0);
    }
    
}
