import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;


public class writePoints {
    public static void main(String args[]) {        
        try {
            File fileIn = new File("initialPoints.txt");
            File fileOut = new File("filteredPoints.txt");
            Scanner sc = new Scanner(fileIn);

            ArrayList<String> lines = new ArrayList<>();
            while(sc.hasNextLine()) {
                String s = sc.nextLine();
                lines.add(s);
            }
            
            PrintWriter printer = new PrintWriter(fileOut);
            double x, y, z;
            ArrayList<Point> points = new ArrayList<>();
            
            for (int i=0; i < lines.size(); i++){
                if (lines.get(i).length() > 0 ) {
                    String[] rawPoint= lines.get(i).split(",");
                    x = Double.parseDouble(rawPoint[0]);
                    y = Double.parseDouble(rawPoint[1]);
                    z = Double.parseDouble(rawPoint[2]);
                    // printer.write(x + ", " + y + ", " + z + "\n");

                    Point newP = new Point(x, y, z);
                    points.add(newP);
                }
            }

            List<Point> filteredPoints = points.stream() 
            .filter(p -> p.getZ() <= 2.0)
            .map(p -> new Point(p.getX() / 2 - 150,
                                p.getY() / 2 - 37,
                                p.getZ() / 2))
            .collect(Collectors.toList());


            for (Point fp : filteredPoints){
                printer.write(fp.getX() + ", " + fp.getY() + ", " + fp.getZ() + "\n");
            }

            printer.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found. Please scan in new file.");
        }

    }
}
