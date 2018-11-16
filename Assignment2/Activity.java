import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;


public class Activity implements Action {

	private Entity entity;
	private WorldModel world;
	private ImageStore imageStore;
	private int repeatCount;

	public Activity(Entity entity, WorldModel world,
	ImageStore imageStore, int repeatCount) {
		this.entity = entity;
		this.world = world;
		this.imageStore = imageStore;
		this.repeatCount = repeatCount;
	}

	public void executeAction(EventScheduler scheduler) {
		executeActivityAction(scheduler);
	}

	// Only getting called on MinerNotFull and Vein
	public void executeActivityAction(EventScheduler scheduler) {
		
		if (this.entity instanceof MinerFull) {
			((MinerFull)(this.entity)).executeMinerFullActivity(this.world,
			this.imageStore, scheduler);
		}
		else if (this.entity instanceof MinerNotFull) {
			((MinerNotFull)(this.entity)).executeMinerNotFullActivity(this.world,
			this.imageStore, scheduler);
		}
		else if (this.entity instanceof Ore) {
			((Ore)(this.entity)).executeOreActivity(this.world, this.imageStore, scheduler);
		}
		else if (this.entity instanceof OreBlob) {
			((OreBlob)(this.entity)).executeOreBlobActivity(this.world,
			this.imageStore, scheduler);
		}
		else if (this.entity instanceof Quake) {
			((Quake)(this.entity)).executeQuakeActivity(this.world, this.imageStore,
			scheduler);
		}
		else if (this.entity instanceof Vein) {
			((Vein)(this.entity)).executeVeinActivity(this.world, this.imageStore,
			scheduler);
		}
		else {
			throw new UnsupportedOperationException(
			String.format("executeActivityAction not supported for %s",
			"this.entity.kind"));
		}
	}


	public Action createActivityAction(WorldModel world,
	ImageStore imageStore) {
		return new Activity((Entity)this.entity, world, imageStore, 0);
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

}
