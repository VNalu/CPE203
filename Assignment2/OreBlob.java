import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class OreBlob implements ActiveEntity {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    public int resourceLimit;
    public int resourceCount;
    private int actionPeriod;
    private int animationPeriod;
    public static final String BLOB_ID_SUFFIX = " -- blob";
    public static final int BLOB_PERIOD_SCALE = 4;

    public static final String QUAKE_KEY = "quake";
    
    public OreBlob(String id, Point position, List<PImage> images, int resourceLimit,
    int resourceCount, int actionPeriod, int animationPeriod) {
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

    public void executeOreBlobActivity(WorldModel world,
    ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> blobTarget = world.findNearest(this.getPosition(), new Vein(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod));
        long nextPeriod = this.actionPeriod;

        if (blobTarget.isPresent()) {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveToOreBlob(world, blobTarget.get(), scheduler)) {
                ActiveEntity quake = tgtPos.createQuake(imageStore.getImageList(QUAKE_KEY));

                world.addEntity((Entity)quake);
                nextPeriod += this.actionPeriod;
                quake.scheduleActions(scheduler, world, imageStore);
            }
        }
        Activity act = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent((Entity)this,
        act.createActivityAction(world, imageStore), nextPeriod);
    }

    public void scheduleActions(EventScheduler scheduler,
    WorldModel world, ImageStore imageStore) {
        Activity act = new Activity(this, world, imageStore, 0);
        Animation anim = new Animation(this, world, imageStore, 0);
        scheduler.scheduleEvent((Entity)this,
        act.createActivityAction(world, imageStore),
          this.actionPeriod);
          scheduler.scheduleEvent((Entity)this,
          anim.createAnimationAction(0), this.getAnimationPeriod());
    }

    public PImage getCurrentImage()
    {
        return (this).images.get((this).imageIndex);
    }
    

   public boolean moveToOreBlob(WorldModel world, Entity target, EventScheduler scheduler) {
    
    if (position.adjacent(target.getPosition())) {
       world.removeEntity(target);
       scheduler.unscheduleAllEvents(target);
       return true;
    }
    else {
       Point nextPos = nextPositionOreBlob(world, target.getPosition());
 
       if (!this.getPosition().equals(nextPos)) {
          Optional<Entity> occupant = world.getOccupant(nextPos);
          if (occupant.isPresent()) {
             scheduler.unscheduleAllEvents(occupant.get());
          }
          world.moveEntity((Entity)this, nextPos);
       }
       return false;
    }
 }


 public Point nextPositionOreBlob(WorldModel world, Point destPos) {
    int horiz = Integer.signum(destPos.x - this.getPosition().x);
    Point newPos = new Point(this.getPosition().x + horiz,
    this.getPosition().y);

    Optional<Entity> occupant = world.getOccupant(newPos);

    if (horiz == 0 ||
    (occupant.isPresent() && !(occupant.get() instanceof Ore)))
    {
        int vert = Integer.signum(destPos.y - this.getPosition().y);
        newPos = new Point(this.getPosition().x, this.getPosition().y + vert);
        occupant = world.getOccupant(newPos);

        if (vert == 0 || (occupant.isPresent() && !(occupant.get() instanceof Ore))) {
            newPos = this.getPosition();
        }
    }

    return newPos;
}

}