import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


public class writePoints {
    public static void main(String args[]) {        
        try {
            File fileIn = new File("initialPoints");
            File fileOut = new File("filteredPoints");
            Scanner sc = new Scanner(fileIn);

            ArrayList<String> lines = new ArrayList<>();
            while(sc.hasNextLine()) {
                String s = sc.nextLine();
                lines.add(s);
            }
            
            PrintWriter printer = new PrintWriter(fileOut);
            double x, y, z;
            ArrayList<Point> points = new ArrayList<>();
            println("there are " + lines.size());
            
            for (int i=0; i < lines.size(); i++){
                if (lines.get(i).length() > 0 ) {
                    String[] rawPoint= lines.get(i).split(",");
                    x = Double.parseDouble(rawPoint[0]);
                    y = Double.parseDouble(rawPoint[1]);
                    z = Double.parseDouble(rawPoint[2]);
                    println("xy: " + x + " " + y);

                    Point newP = new Point(x, y, z);
                    points.add(newP);
                }
            }

            for (Point p : points){
                printer.write("xy: " + p.getX() + " " + p.getY());
            }
            printer.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found. Please scan in new file.");
        }

    }
}
