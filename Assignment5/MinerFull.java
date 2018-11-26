import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

final class MinerFull extends MovingEntity {

    public MinerFull(String id, Point position, List<PImage> images, int resourceLimit,
    int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

    public void executeMinerFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Blacksmith smith = new Blacksmith(this.getId(), this.getPosition(), this.getImages(), 0, 0, this.getActionPeriod(), 0);
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), smith);

        if (fullTarget.isPresent() && 
            moveToFull(world, fullTarget.get(), scheduler)) {
            transformFull(world, scheduler, imageStore);
        }
        else {
            Activity act = new Activity(this, world, imageStore, 0);
           scheduler.scheduleEvent(this,
              act.createActivityAction(world, imageStore),
              this.getActionPeriod());
        }
    }

    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        Entity miner = this.getPosition().createMinerNotFull(this.getId(), this.resourceLimit,
        this.getActionPeriod(), this.getAnimationPeriod(), this.getImages());


        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        ActiveEntity m = (ActiveEntity)miner;
        m.scheduleActions(scheduler, world, imageStore);
    }

    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler) {

        // If miner is next to blacksmith
        if (this.getPosition().adjacent(target.getPosition())) {
            return true;
        }
        else { // Else move miner
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