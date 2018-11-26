import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class Obstacle extends Entity {


    public Obstacle(String id, Point position, List<PImage> images,
     int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

}