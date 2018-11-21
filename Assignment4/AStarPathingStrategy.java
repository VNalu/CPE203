import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AStarPathingStrategy implements PathingStrategy {

    public ArrayList<Point> reconstructPath(HashMap<Point, ArrayList<Point>> cameFrom, Point current) {
        ArrayList<Point> totalPath = new ArrayList<Point>();
        totalPath.add(current);

        System.out.println(cameFrom + "\n");
        while (cameFrom.get(current) != null) {
            Point parent = (cameFrom.get(current)).get((cameFrom.get(current)).size()-1);
            if (parent != null) {
                // cameFrom.remove(current);
                // totalPath.add(current);

                totalPath.add(0, parent);
                current = parent;
            }
        }
        System.out.println("Total Path returned: " + totalPath + "\n");
        totalPath.remove(0);
        return totalPath;
    }

    public boolean withinBounds(Point pos) {
        return pos.y >= 0 && pos.y < 30 && pos.x >= 0 && pos.x < 40;
    }

    public ArrayList<Point> getNeighbors(Point point) {
        ArrayList<Point> neighbors = new ArrayList<Point>();
        Point p1 = new Point(point.x, point.y - 1);
        if (withinBounds(p1)) {
            neighbors.add(p1);
        }
        Point p2 = new Point(point.x, point.y + 1);
        if (withinBounds(p2)) {
            neighbors.add(p2);
        }
        Point p3 = new Point(point.x + 1, point.y);
        if (withinBounds(p3)) {
            neighbors.add(p3);
        }
        Point p4 = new Point(point.x - 1, point.y);
        if (withinBounds(p4)) {
            neighbors.add(p4);
        }
        return neighbors;
    }

    public HashMap<Point, ArrayList<Point>> addToCameFromList(HashMap<Point, ArrayList<Point>> cameFrom, Point parent, Point newPoint) {
        ArrayList<Point> prevCameFromList = new ArrayList<Point>();
        if (cameFrom.get(parent) != null) {
            for (Point p : cameFrom.get(parent)) {
                prevCameFromList.add(p);
            }
        }

        prevCameFromList.add(newPoint);
        cameFrom.put(parent, prevCameFromList);
        return cameFrom;
    }

    public List<Point> computePath(Point start, Point end,
        Predicate<Point> canPassThrough,
        BiPredicate<Point, Point> withinReach,
        Function<Point, Stream<Point>> potentialNeighbors) {
        
        // Prints goal's coordinates
        System.out.println("Goal: " + end + "   Start: " + start);

        // The set of nodes already evaluated
        ArrayList<Point> closedSet = new ArrayList<Point>();

        // The set of currently discovered nodes that are not evaluated yet.
        ArrayList<Point> openSet = new ArrayList<Point>();
        openSet.add(start);

        // For each node, which node it can most efficiently be reached from.
        // If a node can be reached from many nodes,
        // cameFrom will eventually contain the most efficient previous step.
        HashMap<Point, ArrayList<Point>> cameFrom = new HashMap<Point, ArrayList<Point>>();


        // For each node, the total cost of getting from the start node to the end
        // by passing by that node. That value is partly known, partly heuristic.
        start.setG(0);
        // For the first node, that value is completely heuristic.
        start.setF(start.calculateHeuristic(end));
        
        while (!(openSet.isEmpty())) {

            // Get Point in openSet with lowest fScore
            Point lowestFPoint = openSet.get(0);
            for (Point p : openSet) {
                if (p.getF() <= lowestFPoint.getF()) {
                    lowestFPoint = p;
                }
            }
            Point current = lowestFPoint;

            int lowestFPointIdx = openSet.indexOf(lowestFPoint);
            openSet.remove(lowestFPointIdx);
            closedSet.add(current);

            List<Point> neighbors = potentialNeighbors.apply(current)
                .filter(canPassThrough)
                .filter(pt ->
                    !pt.equals(start)
                    && !closedSet.contains(pt))
                .collect(Collectors.toList());

            for (Point neighbor : neighbors) {

                if (getNeighbors(neighbor).contains(end)) {
                    System.out.println("Being called from neighbor");
                    addToCameFromList(cameFrom, neighbor, current);

                    return reconstructPath(cameFrom, neighbor);
                }

                // The distance from start to a neighbor
                int tentativeGScore = current.getG() + (int)(Math.sqrt(current.distanceSquared(current, neighbor)));

                if (!(openSet.contains(neighbor))) {
                    openSet.add(neighbor);
                }
                else if (tentativeGScore >= neighbor.getG()) {
                    continue; // This is not a better path.
                }

                // This path is the best until now. Record it!
                addToCameFromList(cameFrom, neighbor, current);
                neighbor.setG(tentativeGScore);
                neighbor.setF(tentativeGScore + neighbor.calculateHeuristic(end));
            }
        }

        System.out.println("Returns reconstructed list without goal end -> Something went wrong");
        return reconstructPath(cameFrom, end);

    }
}
