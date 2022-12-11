import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x, y, sum;
        System.out.print("Please input a number x: ");
        x = sc.nextInt();
        System.out.print("Please input a number y: ");
        y = sc.nextInt();
        sum = x + y;
        System.out.println("The sum of x and y is: " + sum);
    }
}