class SimpleIf
{
   public static double max(double x, double y)
   {
        /* TO DO: Write an if statement to determine which
         argument is larger and return that value.
        */

        double maximum = x;
        if (maximum < y){
            maximum = y;
        }

        return maximum; // clearly not correct -- but testable
   }
}
