
import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

final class Point
{
   public int x;
   public int y;

   private final String QUAKE_ID = "quake";
   private final int QUAKE_ACTION_PERIOD = 1100;
   private final int QUAKE_ANIMATION_PERIOD = 100;

   // Below Variables were added for A*
   public int INFINITE = 2147483647;
   private int g = INFINITE; // Distance from Start
   private int h; // Heuristic Distance
   private int f = INFINITE; // Total Distance

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public boolean equals(Object other)
   {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y;
   }

   public int hashCode()
   {
      int result = 17;
      result = result * 31 + x;
      result = result * 31 + y;
      return result;
   }

   public int distanceSquared(Point p1, Point p2)
   {
      int deltaX = p1.x - p2.x;
      int deltaY = p1.y - p2.y;

      return deltaX * deltaX + deltaY * deltaY;
   }


   public Obstacle createObstacle(String id, List<PImage> images)
   {
      return new Obstacle(id, this, images, 0, 0, 0, 0);
   }

   public Ore createOre(String id, int actionPeriod,
      List<PImage> images)
   {
      return new Ore(id, this, images, 0, 0, actionPeriod, 0);
   }

   public Quake createQuake(List<PImage> images)
   {
      return new Quake(QUAKE_ID, this, images, 0, 0, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
   }

   public Vein createVein(String id, int actionPeriod,
      List<PImage> images)
   {
      return new Vein(id, this, images, 0, 0, actionPeriod, 0);
   }


   public Blacksmith createBlacksmith(String id,
      List<PImage> images)
   {
      return new Blacksmith(id, this, images, 0, 0, 0, 0);
   }

   public MinerFull createMinerFull(String id, int resourceLimit,
      int actionPeriod, int animationPeriod,
      List<PImage> images)
   {
      return new MinerFull(id, this, images,
         resourceLimit, resourceLimit, actionPeriod, animationPeriod);
   }

   public MinerNotFull createMinerNotFull(String id, int resourceLimit,
      int actionPeriod, int animationPeriod,
      List<PImage> images)
   {
      return new MinerNotFull(id, this, images,
         resourceLimit, 0, actionPeriod, animationPeriod);
   }


   public Ghost createGhost(String id, int resourceLimit,
      int actionPeriod, int animationPeriod,
      List<PImage> images)
   {
      return new Ghost(id, this, images,
         resourceLimit, 0, actionPeriod, animationPeriod);
   }



   // Calculates nearest entity from list using pythagorean theorem
   public Optional<Entity> nearestEntity(List<Entity> entities)
   {
      if (entities.isEmpty()) {
            return Optional.empty();
      }
      else {
            // takes first entity and finds its distance from this
            Entity nearest = entities.get(0);
            int nearestDistance = distanceSquared(nearest.getPosition(), this);

            // compares distance of nearest entity to this with all same entities until nearest found
            for (Entity other : entities) {
                  int otherDistance = distanceSquared(other.getPosition(), this);

                  if (otherDistance < nearestDistance) {
                        nearest = other;
                        nearestDistance = otherDistance;
                  }
            }

         return Optional.of(nearest);
      }
   }

   public boolean adjacent(Point p2)
   {
      return (this.x == p2.x && Math.abs(this.y - p2.y) == 1) ||
         (this.y == p2.y && Math.abs(this.x - p2.x) == 1);
   }

   // Functions Below were added for A*

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public int getG() {
      return g;
   }

   public void setG(int newG) {
      this.g = newG;
   }

   public int getH() {
      return h;
   }

   public void setH(int newH) {
      this.h = newH;
   }

   public int getF() {
      return f;
   }

   public void setF(int newF) {
      this.f = newF;
   }

   public int calculateHeuristic(Point goal) { // Meant to be set to h
      return Math.abs(goal.getY() - this.getY()) + Math.abs(goal.getX() - this.getX());
   }

}
