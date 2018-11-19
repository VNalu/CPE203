import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class OreBlob extends MovingEntity {

    public static final String BLOB_ID_SUFFIX = " -- blob";
    public static final int BLOB_PERIOD_SCALE = 4;

    public static final String QUAKE_KEY = "quake";
    
    public OreBlob(String id, Point position, List<PImage> images, int resourceLimit,
    int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

    public void executeOreBlobActivity(WorldModel world,
    ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> blobTarget = world.findNearest(this.getPosition(), new Vein(this.getId(), this.getPosition(), this.getImages(), resourceLimit, resourceCount, this.getActionPeriod(), this.getAnimationPeriod()));
        long nextPeriod = this.getActionPeriod();

        if (blobTarget.isPresent()) {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveToOreBlob(world, blobTarget.get(), scheduler)) {
                ActiveEntity quake = tgtPos.createQuake(imageStore.getImageList(QUAKE_KEY));

                world.addEntity(quake);
                nextPeriod += this.getActionPeriod();
                quake.scheduleActions(scheduler, world, imageStore);
            }
        }
        Activity act = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent(this,
        act.createActivityAction(world, imageStore), nextPeriod);
    }


   public boolean moveToOreBlob(WorldModel world, Entity target, EventScheduler scheduler) {
    
    if (this.getPosition().adjacent(target.getPosition())) {
       world.removeEntity(target);
       scheduler.unscheduleAllEvents(target);
       return true;
    }
    else {
       Point nextPos = nextPosition(world, target.getPosition());
 
       if (!this.getPosition().equals(nextPos)) {
          Optional<Entity> occupant = world.getOccupant(nextPos);
          if (occupant.isPresent()) {
             scheduler.unscheduleAllEvents(occupant.get());
          }
          world.moveEntity(this, nextPos);
       }
       return false;
    }
 }

}