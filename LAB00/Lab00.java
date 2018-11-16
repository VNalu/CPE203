public class Lab00
{
    public static void main(String[] args)
    {
	   int x = 5;
	   String y = "hello";
	   double z = 9.8; //or float z = 9.8 (float)
	   
	   // printing the variables
	   System.out.println("x: " + x + " y: " + y + " z: " + z);

	   // an array
	   int[] nums = {3, 6, -1, 2};
	   for (int i : nums){
	   	System.out.println(i);
	   }

	   // call a function
	   int numFound = char_count(y, 'l');
	   System.out.println("Found:" + numFound);

	   // a counting for loop
	   for (int i=1; i<11; i++){
	   	System.out.print(i + " ");
	   }

	   System.out.println();

    }

    public static int char_count(String s, char c) {
    	int count = 0;
    	char[] charArray = s.toCharArray();
    	for (char ch : charArray){
    		if (ch == c){
    			count++;
    		}
    	}
    	return count;
    }

}