import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class Ore extends ActiveEntity {

    public static final String BLOB_KEY = "blob";
    public static final int BLOB_ANIMATION_MIN = 50;
    public static final int BLOB_ANIMATION_MAX = 150;
    public static final String BLOB_ID_SUFFIX = " -- blob";
    public static final int BLOB_PERIOD_SCALE = 4;
    // public static final String ORE_KEY = "ore";
    
    public Ore(String id, Point position, List<PImage> images, int resourceLimit,
        int resourceCount, int actionPeriod, int animationPeriod) {
            super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);

    }

    public void executeOreActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        // Point pos = position;  // store current position before removing

        OreBlob blob = new OreBlob(this.getId() + BLOB_ID_SUFFIX,
         this.getPosition(), imageStore.getImageList(BLOB_KEY), resourceLimit, resourceCount,
         this.getActionPeriod() / BLOB_PERIOD_SCALE,
         BLOB_ANIMATION_MIN + ImageStore.rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN));

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(blob);
        blob.scheduleActions(scheduler, world, imageStore);
    }

}