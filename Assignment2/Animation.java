import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;


public class Animation implements Action {

	private Entity entity;
	private WorldModel world;
	private ImageStore imageStore;
	private int repeatCount;

	public Animation(Entity entity, WorldModel world,
	ImageStore imageStore, int repeatCount) {
		this.entity = entity;
		this.world = world;
		this.imageStore = imageStore;
		this.repeatCount = repeatCount;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity newEnt) {
		this.entity = newEnt;
	}

	public WorldModel getWorld() {
		return world;
	}

	public void setWorld(WorldModel newWM) {
		this.world = newWM;
	}

	public ImageStore getImageStore() {
		return imageStore;
	}

	public void setImageStore(ImageStore newIS) {
		this.imageStore = newIS;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int newRC) {
		this.repeatCount = newRC;
	}



	public void executeAction(EventScheduler scheduler)
	{
		executeAnimationAction(scheduler);
	}

	public void executeAnimationAction(EventScheduler scheduler){
		this.entity.nextImage();

		if (this.repeatCount != 1) {
			if (this.entity instanceof MinerFull) {
				scheduler.scheduleEvent(this.entity,
				createAnimationAction(Math.max(this.repeatCount - 1, 0)),
				((MinerFull)(this.entity)).getAnimationPeriod());
			}
			else if (this.entity instanceof MinerNotFull) {
				scheduler.scheduleEvent(this.entity,
				createAnimationAction(Math.max(this.repeatCount - 1, 0)),
				((MinerNotFull)(this.entity)).getAnimationPeriod());
			}

			else if (this.entity instanceof Ore) {
				scheduler.scheduleEvent(this.entity,
				createAnimationAction(Math.max(this.repeatCount - 1, 0)),
				((Ore)(this.entity)).getAnimationPeriod());
			}

			else if (this.entity instanceof OreBlob) {
				scheduler.scheduleEvent(this.entity,
				createAnimationAction(Math.max(this.repeatCount - 1, 0)),
				((OreBlob)(this.entity)).getAnimationPeriod());
			}

			else if (this.entity instanceof Quake) {
				scheduler.scheduleEvent(this.entity,
				createAnimationAction(Math.max(this.repeatCount - 1, 0)),
				((Quake)(this.entity)).getAnimationPeriod());
			}

			else if (this.entity instanceof Vein) {
				scheduler.scheduleEvent(this.entity,
				createAnimationAction(Math.max(this.repeatCount - 1, 0)),
				((Vein)(this.entity)).getAnimationPeriod());
			}

			else {
				throw new UnsupportedOperationException(
				String.format("executeActivityAction not supported for %s",
				"this.entity.kind"));
			}
		}
	}


	public Action createAnimationAction(int repeatCount) {
		return new Animation(this.entity, null, null, repeatCount);
	}
}