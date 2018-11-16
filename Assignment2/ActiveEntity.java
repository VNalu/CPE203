import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;


public interface ActiveEntity extends Entity {
 
    public void scheduleActions(EventScheduler scheduler, 
    WorldModel world, ImageStore imageStore);

}