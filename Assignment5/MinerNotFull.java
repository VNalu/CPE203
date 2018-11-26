import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class MinerNotFull extends MovingEntity {

public MinerNotFull(String id, Point position, List<PImage> images,
 int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
    super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
}


public void executeMinerNotFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
    Optional<Entity> notFullTarget = world.findNearest(this.getPosition(),
    new Ore(this.getId(), this.getPosition(), this.getImages(), 0, 0, this.getActionPeriod(), 0));

    if (!notFullTarget.isPresent() ||
    !moveToNotFull(world, notFullTarget.get(), scheduler) ||
    !transformNotFull(world, scheduler, imageStore))
    {
        Activity act = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent(this,
        act.createActivityAction(world, imageStore),
        this.getActionPeriod());
    }
}


public boolean moveToNotFull(WorldModel world, Entity target, EventScheduler scheduler) {
    if (this.getPosition().adjacent(target.getPosition())) {
       this.setResourceCount(this.getResourceCount() + 1);
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

    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (this.getResourceCount() >= this.getResourceLimit()) {
            
            Entity miner = (this.getPosition()).createMinerFull(this.getId(), this.resourceLimit,
            this.getActionPeriod(), this.getAnimationPeriod(), this.getImages());

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(miner);
            ActiveEntity m = (ActiveEntity)miner;
            m.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }


}
