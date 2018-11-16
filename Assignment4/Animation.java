import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;


public class Animation extends Action {

	public Animation(Entity entity, WorldModel world,
	ImageStore imageStore, int repeatCount) {
		super(entity, world, imageStore, repeatCount);
	}

	public void executeAction(EventScheduler scheduler) {
		executeAnimationAction(scheduler);
	}

	public void executeAnimationAction(EventScheduler scheduler){
		this.getEntity().nextImage();

		if (this.getRepeatCount() != 1) {
			if (this.getEntity() instanceof MinerFull) {
				scheduler.scheduleEvent(this.getEntity(),
				createAnimationAction(Math.max(this.getRepeatCount() - 1, 0)),
				((MinerFull)(this.getEntity())).getAnimationPeriod());
			}
			else if (this.getEntity() instanceof MinerNotFull) {
				scheduler.scheduleEvent(this.getEntity(),
				createAnimationAction(Math.max(this.getRepeatCount() - 1, 0)),
				((MinerNotFull)(this.getEntity())).getAnimationPeriod());
			}

			else if (this.getEntity() instanceof Ore) {
				scheduler.scheduleEvent(this.getEntity(),
				createAnimationAction(Math.max(this.getRepeatCount() - 1, 0)),
				((Ore)(this.getEntity())).getAnimationPeriod());
			}

			else if (this.getEntity() instanceof OreBlob) {
				scheduler.scheduleEvent(this.getEntity(),
				createAnimationAction(Math.max(this.getRepeatCount() - 1, 0)),
				((OreBlob)(this.getEntity())).getAnimationPeriod());
			}

			else if (this.getEntity() instanceof Quake) {
				scheduler.scheduleEvent(this.getEntity(),
				createAnimationAction(Math.max(this.getRepeatCount() - 1, 0)),
				((Quake)(this.getEntity())).getAnimationPeriod());
			}

			else if (this.getEntity() instanceof Vein) {
				scheduler.scheduleEvent(this.getEntity(),
				createAnimationAction(Math.max(this.getRepeatCount() - 1, 0)),
				((Vein)(this.getEntity())).getAnimationPeriod());
			}

			else {
				throw new UnsupportedOperationException(
				String.format("executeActivityAction not supported for %s",
				"this.getEntity().kind"));
			}
		}
	}


	public Action createAnimationAction(int repeatCount) {
		return new Animation(this.getEntity(), null, null, repeatCount);
	}
}