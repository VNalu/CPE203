import java.util.List;
import java.util.Optional;
import processing.core.PImage;
import java.util.Random;

final class Entity
{
   public EntityKind kind;
   public String id;
   public Point position;
   public List<PImage> images;
   public int imageIndex;
   public int resourceLimit;
   public int resourceCount;
   public int actionPeriod;
   public int animationPeriod;

   // Added by me
//    public static final String BLOB_KEY = "blob";
//    public static final String BLOB_ID_SUFFIX = " -- blob";
//    public static final int BLOB_PERIOD_SCALE = 4;
//    public static final int BLOB_ANIMATION_MIN = 50;
//    public static final int BLOB_ANIMATION_MAX = 150;
//    public static final String QUAKE_KEY = "quake";
//    public static final String ORE_ID_PREFIX = "ore -- ";
//    public static final int ORE_CORRUPT_MIN = 20000;
//    public static final int ORE_CORRUPT_MAX = 30000;
//    public static final String ORE_KEY = "ore";

   public Entity(EntityKind kind, String id, Point position,
      List<PImage> images, int resourceLimit, int resourceCount,
      int actionPeriod, int animationPeriod)
   {
      this.kind = kind;
      this.id = id;
      this.position = position;
      this.images = images;
      this.imageIndex = 0;
      this.resourceLimit = resourceLimit;
      this.resourceCount = resourceCount;
      this.actionPeriod = actionPeriod;
      this.animationPeriod = animationPeriod;
   }

   public int getAnimationPeriod() {
      switch (this.kind) {
      case MINER_FULL:
      case MINER_NOT_FULL:
      case ORE_BLOB:
      case QUAKE:
         return this.animationPeriod;
      default:
         throw new UnsupportedOperationException(
            String.format("getAnimationPeriod not supported for %s",
            this.kind));
      }
   }

   public void nextImage()
   {
      this.imageIndex = (this.imageIndex + 1) % this.images.size();
   }

   public void executeMinerFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
   {
      Optional<Entity> fullTarget = Functions.findNearest(world, this.position,
         EntityKind.BLACKSMITH);

      if (fullTarget.isPresent() && Functions.moveToFull(this, world, fullTarget.get(), scheduler))
      {
        //  this.transformFull(world, scheduler, imageStore);
        Functions.transformFull(this, world, scheduler, imageStore);
      }
      else
      {
         Functions.scheduleEvent(scheduler, this,
            Functions.createActivityAction(this, world, imageStore),
            this.actionPeriod);
      }
   }

   public void executeMinerNotFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
   {
      Optional<Entity> notFullTarget = Functions.findNearest(world, this.position,
         EntityKind.ORE);

      if (!notFullTarget.isPresent() ||
         !Functions.moveToNotFull(this, world, notFullTarget.get(), scheduler) ||
        //  !this.transformNotFull(world, scheduler, imageStore))
            !Functions.transformNotFull(this, world, scheduler, imageStore))
      {
        Functions.scheduleEvent(scheduler, this,
            Functions.createActivityAction(this, world, imageStore),
            this.actionPeriod);
      }
   }

   public void executeOreActivity(WorldModel world,
      ImageStore imageStore, EventScheduler scheduler)
   {
      Point pos = this.position;  // store current position before removing

      Functions.removeEntity(world, this);
      Functions.unscheduleAllEvents(scheduler, this);

      Entity blob = Functions.createOreBlob(this.id + BLOB_ID_SUFFIX,
         pos, this.actionPeriod / BLOB_PERIOD_SCALE,
         BLOB_ANIMATION_MIN +
            Functions.rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN),
            Functions.getImageList(imageStore, BLOB_KEY));

            Functions.addEntity(world, blob);
    //   blob.scheduleActions(scheduler, world, imageStore);
            Functions.scheduleActions(blob, scheduler, world, imageStore);
   }

   public void executeOreBlobActivity(WorldModel world,
      ImageStore imageStore, EventScheduler scheduler)
   {
      Optional<Entity> blobTarget = Functions.findNearest(world,
         this.position, EntityKind.VEIN);
      long nextPeriod = this.actionPeriod;

      if (blobTarget.isPresent())
      {
         Point tgtPos = blobTarget.get().position;

         if (Functions.moveToOreBlob(this, world, blobTarget.get(), scheduler))
         {
            Entity quake = Functions.createQuake(tgtPos,
            Functions.getImageList(imageStore, QUAKE_KEY));

            Functions.addEntity(world, quake);
            nextPeriod += this.actionPeriod;
            // quake.scheduleActions(scheduler, world, imageStore);
            Functions.scheduleActions(quake, scheduler, world, imageStore);
         }
      }
    }

    public void executeQuakeActivity(WorldModel world,
    ImageStore imageStore, EventScheduler scheduler) {
        Functions.unscheduleAllEvents(scheduler, this);
        Functions.removeEntity(world, this);
    }

    public void executeVeinActivity(WorldModel world,ImageStore imageStore, EventScheduler scheduler)
    {
    Optional<Point> openPt = Functions.findOpenAround(world, this.position);

    if (openPt.isPresent()) {
       Entity ore = Functions.createOre(Functions.ORE_ID_PREFIX + this.id,
          openPt.get(), Functions.ORE_CORRUPT_MIN +
             Functions.rand.nextInt(Functions.ORE_CORRUPT_MAX - Functions.ORE_CORRUPT_MIN),
             Functions.getImageList(imageStore, Functions.ORE_KEY));
             Functions.addEntity(world, ore);
            //  ore.scheduleActions(scheduler, world, imageStore);
             Functions.scheduleActions(ore, scheduler, world, imageStore);
    }

    Functions.scheduleEvent(scheduler, this,
    Functions.createActivityAction(this, world, imageStore),
       this.actionPeriod);
    }

    public void scheduleActions(EventScheduler scheduler,
    WorldModel world, ImageStore imageStore)
 {
    switch (this.kind)
    {
    case MINER_FULL:
    Functions.scheduleEvent(scheduler, this,
       Functions.createActivityAction(this, world, imageStore),
          this.actionPeriod);
          Functions.scheduleEvent(scheduler, this, Functions.createAnimationAction(this, 0),
          this.getAnimationPeriod());
       break;

    case MINER_NOT_FULL:
    Functions.scheduleEvent(scheduler, this,
       Functions.createActivityAction(this, world, imageStore),
          this.actionPeriod);
          Functions.scheduleEvent(scheduler, this,
       Functions.createAnimationAction(this, 0), this.getAnimationPeriod());
       break;

    case ORE:
    Functions.scheduleEvent(scheduler, this,
       Functions.createActivityAction(this, world, imageStore),
          this.actionPeriod);
       break;

    case ORE_BLOB:
    Functions.scheduleEvent(scheduler, this,
       Functions.createActivityAction(this, world, imageStore),
          this.actionPeriod);
       Functions.scheduleEvent(scheduler, this,
       Functions.createAnimationAction(this, 0), this.getAnimationPeriod());
       break;

    case QUAKE:
    Functions.scheduleEvent(scheduler, this, Functions.createActivityAction(this, world, imageStore),
          this.actionPeriod);
          Functions.scheduleEvent(scheduler, this,
          Functions.createAnimationAction(this, Functions.QUAKE_ANIMATION_REPEAT_COUNT),
          this.getAnimationPeriod());
       break;

    case VEIN:
    Functions.scheduleEvent(scheduler, this, Functions.createActivityAction(this, world, imageStore),
          this.actionPeriod);
       break;

    default:
    }
 }

 public boolean transformNotFull(WorldModel world,
 EventScheduler scheduler, ImageStore imageStore) {
 if  (this.resourceCount >= this.resourceLimit) {
    Entity miner = Functions.createMinerFull(this.id, this.resourceLimit,
     this.position, this.actionPeriod, this.animationPeriod,
     this.images);

    Functions.removeEntity(world, this);
    Functions.unscheduleAllEvents(scheduler, this);

    Functions.addEntity(world, miner);
    this.scheduleActions(scheduler, world, imageStore);

    return true;
 }

 return false;
}

    public void transformFull( WorldModel world,
    EventScheduler scheduler, ImageStore imageStore) {
        Entity miner = Functions.createMinerNotFull(this.id, this.resourceLimit,
        this.position, this.actionPeriod, this.animationPeriod,
        this.images);

        Functions.removeEntity(world, this);
        Functions.unscheduleAllEvents(scheduler, this);

        Functions.addEntity(world, miner);
        this.scheduleActions(scheduler, world, imageStore);
    }

}
