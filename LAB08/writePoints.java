import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import processing.core.*;

public class writePoints extends PApplet{

    private static ArrayList<Point> processPoint(String[] args) {
        String fileInName = args[0]; // "initialPoints.txt"

		double x, y, z;
        ArrayList<Point> points = new ArrayList<>();
        // read API docs more, processingPoint isn't part of PApplet
        // so can't call Processing method loadStrings
        // may have static version of method
		String[] lines = loadStrings(fileInName);
            
        println("there are " + lines.length);
        
        for (int i=0; i < lines.length; i++){
            if (lines[i].length() > 0 ) {
                String[] rawPoint= lines[i].split(",");
                x = Double.parseDouble(rawPoint[0]);
                y = Double.parseDouble(rawPoint[1]);
                z = Double.parseDouble(rawPoint[2]);
                println("xy: " + x + " " + y);

                Point newP = new Point(x, y, z);
                points.add(newP);
            }
        }
        return points;
    }

    // Takes fileOutName and writes already filtered points to file
    private static void writeToFile(String[] args, ArrayList<Point> points) {

        String fileOutName = args[1]; // "filteredPoints.txt"
        PrintWriter fileOut = new PrintWriter(fileOutName);
        for (Point p : points){
			fileOut.println("xy: " + p.getX() + " " + p.getY());
        }
        fileOut.close();
    }

    public static void main(String[] args)
    {
        ArrayList<Point> unFilteredPoints = processPoint(args);
        writeToFile(args, unFilteredPoints);
    }
}
