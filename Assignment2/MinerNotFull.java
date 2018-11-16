import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;

public class MinerNotFull implements Miner {
    
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    public int resourceLimit;
    public int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

public MinerNotFull(String id, Point position, List<PImage> images,
 int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
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

public void executeMinerNotFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
    Optional<Entity> notFullTarget = world.findNearest(this.getPosition(),
    new Ore(id, position, images, 0, 0, actionPeriod, 0));

    if (!notFullTarget.isPresent() ||
    !moveToNotFull(world, notFullTarget.get(), scheduler) ||
    !transformNotFull(world, scheduler, imageStore))
    {
        Activity act = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent((Entity)this,
        act.createActivityAction(world, imageStore),
        this.actionPeriod);
    }
}


public boolean moveToNotFull(WorldModel world, Entity target, EventScheduler scheduler) {
    if (position.adjacent(target.getPosition())) {
       this.setResourceCount(this.getResourceCount() + 1);
       world.removeEntity(target);
       scheduler.unscheduleAllEvents(target);

       return true;
    }
    else {
       Point nextPos = nextPositionMiner(world, target.getPosition());

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

    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (this.getResourceCount() >= this.getResourceLimit()) {
            
            Entity miner = (this.getPosition()).createMinerFull(this.id, this.resourceLimit,
            this.actionPeriod, this.animationPeriod, this.images);

            world.removeEntity((Entity)this);
            scheduler.unscheduleAllEvents((Entity)this);

            world.addEntity((Entity)miner);
            ActiveEntity m = (ActiveEntity)miner;
            m.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    public Point nextPositionMiner(WorldModel world, Point destPos) { //also in MF
        int horiz = Integer.signum(destPos.x - this.getPosition().x);
        Point newPos = new Point(this.getPosition().x + horiz,
            this.getPosition().y);
    
        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.y - this.getPosition().y);
            newPos = new Point(this.getPosition().x,
                this.position.y + vert);
    
            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = this.position;
            }
        }
    
        return newPos;
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

}
