public interface Miner extends ActiveEntity {

    // public static final int actionPeriod;
    // public static final int animationPeriod;

    public Point nextPositionMiner(WorldModel world, Point destPos);
}