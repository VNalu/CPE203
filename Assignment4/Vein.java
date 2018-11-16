import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class Vein extends ActiveEntity {

    public static final String ORE_ID_PREFIX = "ore -- ";
    public static final int ORE_CORRUPT_MIN = 20000;
    public static final int ORE_CORRUPT_MAX = 30000;

    public Vein(String id, Point position, List<PImage> images,
     int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }


    public void executeVeinActivity(WorldModel world,
       ImageStore imageStore, EventScheduler scheduler)
    {
       Optional<Point> openPt = world.findOpenAround(this.getPosition());
 
       if (openPt.isPresent())
       {
            Ore ore = new Ore(ORE_ID_PREFIX + this.getId(), openPt.get(), imageStore.getImageList(world.ORE_KEY), resourceLimit, resourceCount,
            ORE_CORRUPT_MIN + ImageStore.rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN), this.getAnimationPeriod());

        //   Entity ore = openPt.get().createOre(ORE_ID_PREFIX + this.id, ORE_CORRUPT_MIN +
        //      ImageStore.rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN),
        //      imageStore.getImageList(world.ORE_KEY));
             world.addEntity(ore);
             ore.scheduleActions(scheduler, world, imageStore);
       }
 
       Activity act = new Activity(this, world, imageStore, 0);
       scheduler.scheduleEvent(this,
       act.createActivityAction(world, imageStore), this.getActionPeriod());
    }

}