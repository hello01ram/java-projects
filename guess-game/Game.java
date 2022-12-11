import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer, noGuesses, guess;
        answer = (int) Math.floor(Math.random() * (100-0+1) + 0);
        noGuesses = (int) Math.floor(Math.random() * (6-1+1) + 1);
        
        System.out.println("Guess a number between 0 and 99");
        System.out.println("You have " + noGuesses + " number of guesses");

        while (noGuesses > 0) {
            System.out.print("Input your guess: ");
            guess = sc.nextInt();
            if (guess > answer) {
                System.out.println("Your answer is high");
            } else if (guess < answer) {
                System.out.println("Your answer is low");
            } else {
                System.out.println("Your guess is correct");
                break;
            }
            noGuesses--;
        }

        if (noGuesses == 0) {
            System.out.println("You lost the game. The answer is: " + answer);
        }

    }
}