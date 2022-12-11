import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text, reversedText;
        System.out.println("Please input text to reverse: ");
        text = sc.nextLine();
        reversedText = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversedText += text.charAt(i);
        }
        System.out.println("The reversed text is: " + reversedText);
    }
}