import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class Ore implements ActiveEntity {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    public int resourceLimit;
    public int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

    public static final String BLOB_KEY = "blob";
    public static final int BLOB_ANIMATION_MIN = 50;
    public static final int BLOB_ANIMATION_MAX = 150;
    // public static final String ORE_KEY = "ore";
    
    public Ore(String id, Point position, List<PImage> images, int resourceLimit,
        int resourceCount, int actionPeriod, int animationPeriod) {
        // this.kind = kind;
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;

    }
    public void setPosition(Point newPos) {
        this.position = newPos;
    }

    public Point getPosition() {
        return position;
    }

    public void setImages(List<PImage> newImages) {
        this.images = newImages;
    }

    public List<PImage> getImages() {
        return images;
    }

    public void setResourceLimit(int newResLim) {
        this.resourceLimit = newResLim;
    }

    public int getResourceLimit() {
        return resourceLimit;
    }

    public void setResourceCount(int newResCt) {
        this.resourceCount = newResCt;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setActionPeriod(int newActPer) {
        this.actionPeriod = newActPer;
    }

    public int getActionPeriod() {
        return actionPeriod;
    }

    public void setAnimationPeriod(int newAnimPer) {
        this.animationPeriod = newAnimPer;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public void executeOreActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        // Point pos = position;  // store current position before removing

        OreBlob blob = new OreBlob(this.id + OreBlob.BLOB_ID_SUFFIX,
         position, imageStore.getImageList(BLOB_KEY), resourceLimit, resourceCount,
         this.actionPeriod / OreBlob.BLOB_PERIOD_SCALE,
         BLOB_ANIMATION_MIN + ImageStore.rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN));

        world.removeEntity((Entity)this);
        scheduler.unscheduleAllEvents((Entity)this);

        world.addEntity((Entity)blob);
        blob.scheduleActions(scheduler, world, imageStore);
    }

    public void scheduleActions(EventScheduler scheduler,
    WorldModel world, ImageStore imageStore) {
        Activity act = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent(this,
         act.createActivityAction(world, imageStore), 
          this.actionPeriod);
    }

    public PImage getCurrentImage() {
        return (this).images.get((this).imageIndex);
    }

}