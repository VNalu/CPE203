import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;


public interface ScheduleActions extends Entity {


    // public void nextImage();

    public void executeActivityAction(EventScheduler scheduler);

 
    public void scheduleActions(EventScheduler scheduler, 
    WorldModel world, ImageStore imageStore);
}