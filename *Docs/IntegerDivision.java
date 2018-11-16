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
        System.out.println("Answer 7: " + k);
        z = i/j * x;
        System.out.println("Answer 8: " + z);
        z = x * i/j;
        System.out.println("Answer 9: " + z);
        z = 4/i;
        System.out.println("Answer 10: " + z);
        z = 4;
        System.out.println("Answer 11: " + z);
        z = z/i * 2;
        System.out.println("Answer 12: " + z);
        z = 1.0 * i/j;
        System.out.println("Answer 13: " + z);
        z = 2.0/i;
        System.out.println("Answer 14: " + z);
        z = (double)i/j;
        System.out.println("Answer 15: " + z);
        z = (double)(i/j);
        System.out.println("Answer 16: " + z);
        z = i/j + x + i/j;
        System.out.println("Answer 17: " + z);
        z = i/j + x * i/j;
        System.out.println("Answer 18: " + z);
        z = i/j + x + i/(double)j;
        System.out.println("Answer 19: " + z);
        z = x + y * 4.0;
        System.out.println("Answer 20: " + z);
        z = (x + y) * 4.0;
        System.out.println("Answer 21: " + z);
        z = x * y + 4.0;
        System.out.println("Answer 22: " + z);
        z = 2.0/i;
        System.out.println("Answer 23: " + z);
        z = x/j;
        System.out.println("Answer 24: " + z);
    }
}