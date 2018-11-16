public class IntegerDivision {
    public static void main(String[] args) {
        int i, j, k;
        double x, y, z;

        i=3;
        j=2;
        x=3.0;
        y=2.0;

        k = i/j;
        System.out.println("Answer: " + k);
        z = i/j;
        System.out.println("Answer: " + z);
        k = (int)i/j;
        System.out.println("Answer: " + k);
        z = x/j;
        System.out.println("Answer: " + z);
        z = x + i/j;
        System.out.println("Answer: " + z);
        z = x + x/j;
        System.out.println("Answer: " + z);
        k = (int)(x + x/j);
        System.out.println("Answer: " + k);
        z = i/j * x;
        System.out.println("Answer: " + z);
        z = x * i/j;
        System.out.println("Answer: " + z);
        z = 4/i;
        System.out.println("Answer: " + z);
        z = 4;
        System.out.println("Answer: " + z);
        z = z/i * 2;
        System.out.println("Answer: " + z);
        z = 1.0 * i/j;
        System.out.println("Answer: " + z);
        z = 2.0/i;
        System.out.println("Answer: " + z);
        z = (double)i/j;
        System.out.println("Answer: " + z);
        z = (double)(i/j);
        System.out.println("Answer: " + z);
        z = i/j + x + i/j;
        System.out.println("Answer: " + z);
        z = i/j + x * i/j;
        System.out.println("Answer: " + z);
        z = i/j + x + i/(double)j;
        System.out.println("Answer: " + z);
        z = x + y * 4.0;
        System.out.println("Answer: " + z);
        z = (x + y) * 4.0;
        System.out.println("Answer: " + z);
        z = x * y + 4.0;
        System.out.println("Answer: " + z);
        z = 2.0/i;
        System.out.println("Answer: " + z);
        z = x/j;
        System.out.println("Answer: " + z);
    }
}