import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class Quake implements ActiveEntity {

    // public EntityKind kind;
    public String id;
    public Point position;
    public List<PImage> images;
    public int imageIndex;
    public int resourceLimit;
    public int resourceCount;
    public int actionPeriod;
    public int animationPeriod;
    
    private final int QUAKE_ANIMATION_REPEAT_COUNT = 10;

    public static final String QUAKE_KEY = "quake";

    public Quake(String id, Point position, List<PImage> images,
     int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
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


    public PImage getCurrentImage()
    {
        return (this).images.get((this).imageIndex);
    }

    public void scheduleActions(EventScheduler scheduler,
    WorldModel world, ImageStore imageStore) {
        Activity act = new Activity(this, world, imageStore, 0);
        Animation anim = new Animation(this, world, imageStore, 0);
        scheduler.scheduleEvent((Entity)this,
        act.createActivityAction(world, imageStore),
          this.actionPeriod);
          scheduler.scheduleEvent((Entity)this,
       anim.createAnimationAction(QUAKE_ANIMATION_REPEAT_COUNT),
          this.getAnimationPeriod());
    }

    public void executeQuakeActivity(WorldModel world,
       ImageStore imageStore, EventScheduler scheduler) {
       scheduler.unscheduleAllEvents((Entity)this);
       world.removeEntity((Entity)this);
    }
 
    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }


}