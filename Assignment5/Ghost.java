import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class Ghost extends MovingEntity {

    public static final String QUAKE_KEY = "quake";
    
    public Ghost(String id, Point position, List<PImage> images, int resourceLimit,
    int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

    public void executeGhostActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        MinerNotFull miner = new MinerNotFull(this.getId(), this.getPosition(), this.getImages(), 0, 0, this.getActionPeriod(), 0);
        Optional<Entity> target = world.findNearest(this.getPosition(), miner);

        long nextPeriod = this.getActionPeriod();

        if (target.isPresent()) {
            Point tgtPos = target.get().getPosition();

            if (moveToGhost(world, target.get(), scheduler)) {
                // make Miner disappear when ghost next to
                ActiveEntity quake = tgtPos.createQuake(imageStore.getImageList(QUAKE_KEY));

                world.addEntity(quake);
                nextPeriod += this.getActionPeriod();
                quake.scheduleActions(scheduler, world, imageStore);
            }
            
        }
        else {
            Activity act = new Activity(this, world, imageStore, 0);
            scheduler.scheduleEvent(this,
              act.createActivityAction(world, imageStore),
              this.getActionPeriod()); //or nextPeriod according to Miner
        }
    }


    public boolean moveToGhost(WorldModel world, Entity target, EventScheduler scheduler) {
    
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