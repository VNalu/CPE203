import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;


public class Activity extends Action {

	
	public Activity(Entity entity, WorldModel world,
	ImageStore imageStore, int repeatCount) {
		super(entity, world, imageStore, repeatCount);
	}

	public void executeAction(EventScheduler scheduler) {
		executeActivityAction(scheduler);
	}

	public void executeActivityAction(EventScheduler scheduler) {
		
		if (this.getEntity() instanceof MinerFull) {
			((MinerFull)(this.getEntity())).executeMinerFullActivity(this.getWorld(),
			this.getImageStore(), scheduler);
		}
		else if (this.getEntity() instanceof MinerNotFull) {
			((MinerNotFull)(this.getEntity())).executeMinerNotFullActivity(this.getWorld(),
			this.getImageStore(), scheduler);
		}
		else if (this.getEntity() instanceof Ghost) {
			((Ghost)(this.getEntity())).executeGhostActivity(this.getWorld(),
			this.getImageStore(), scheduler);
		}
		else if (this.getEntity() instanceof Ore) {
			((Ore)(this.getEntity())).executeOreActivity(this.getWorld(), this.getImageStore(), scheduler);
		}
		else if (this.getEntity() instanceof OreBlob) {
			((OreBlob)(this.getEntity())).executeOreBlobActivity(this.getWorld(),
			this.getImageStore(), scheduler);
		}
		else if (this.getEntity() instanceof Quake) {
			((Quake)(this.getEntity())).executeQuakeActivity(this.getWorld(), this.getImageStore(),
			scheduler);
		}
		else if (this.getEntity() instanceof Vein) {
			((Vein)(this.getEntity())).executeVeinActivity(this.getWorld(), this.getImageStore(),
			scheduler);
		}
		else {
			throw new UnsupportedOperationException(
			String.format("executeActivityAction not supported for %s",
			"this.getEntity().kind"));
		}
	}


	public Action createActivityAction(WorldModel world,
	ImageStore imageStore) {
		return new Activity(this.getEntity(), world, imageStore, 0);
	}

}
