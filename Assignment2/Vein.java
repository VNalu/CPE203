import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class Vein implements ActiveEntity {

    
    public String id;
    public Point position;
    public List<PImage> images;
    public int imageIndex;
    public int resourceLimit;
    public int resourceCount;
    public int actionPeriod;
    public int animationPeriod;
        
    public static final String ORE_ID_PREFIX = "ore -- ";
    public static final int ORE_CORRUPT_MIN = 20000;
    public static final int ORE_CORRUPT_MAX = 30000;

    public Vein(String id, Point position, List<PImage> images,
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

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public void scheduleActions(EventScheduler scheduler,
    WorldModel world, ImageStore imageStore) {
        Activity act = new Activity(this, world, imageStore, 0);
       scheduler.scheduleEvent((Entity)this,
        act.createActivityAction(world, imageStore),
          this.actionPeriod);
    }

    public PImage getCurrentImage()
    {
        return (this).images.get((this).imageIndex);
    }
 
    public void executeVeinActivity(WorldModel world,
       ImageStore imageStore, EventScheduler scheduler)
    {
       Optional<Point> openPt = world.findOpenAround(this.getPosition());
 
       if (openPt.isPresent())
       {
            Ore ore = new Ore(ORE_ID_PREFIX + this.id, openPt.get(), imageStore.getImageList(world.ORE_KEY), resourceLimit, resourceCount,
            ORE_CORRUPT_MIN + ImageStore.rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN), animationPeriod);

        //   Entity ore = openPt.get().createOre(ORE_ID_PREFIX + this.id, ORE_CORRUPT_MIN +
        //      ImageStore.rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN),
        //      imageStore.getImageList(world.ORE_KEY));
             world.addEntity((Entity)ore);
             ore.scheduleActions(scheduler, world, imageStore);
       }
 
       Activity act = new Activity(this, world, imageStore, 0);
       scheduler.scheduleEvent((Entity)this,
       act.createActivityAction(world, imageStore), this.actionPeriod);
    }


    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.getImages().size();
    }

}