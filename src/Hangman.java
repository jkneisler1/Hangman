import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        // Initialize the game
        String[] hangmanWords = {"tree", "rain", "bear", "encourage", "soup", "chess", "insurance", "pancakes", "stream"};
        Scanner key = new Scanner(System.in);
        Random rand = new Random();
        int n = rand.nextInt(hangmanWords.length + 1);              // Used to randomly select one of the hangmanWords

        char guess;
        char[] assembleWord = new char[hangmanWords[n].length()];           // Tracks and assembles correct guesses.
        int wrongGuess = 0;                                                 // Counter to track the number of wrong guesses
        String status = "ContinueGame";
        boolean foundChar = false;
        boolean completedWord = true;

        for (int i = 0; i < hangmanWords[n].length(); i++) {                // Set the "blanks" for assembleWord
            assembleWord[i] = '_';
        }

        // Start the game
        System.out.println("Welcome, let's play hangman!");
        System.out.println();
        System.out.print("Here is the word that I am thinking of: ");
        for (int i = 0; i < hangmanWords[n].length(); i++) {
            System.out.print(assembleWord[i] + " ");
        }
        System.out.println();
        System.out.println();

        do {                                                                // Process the game
            System.out.print("Enter your guess: ");
            guess = key.next().charAt(0);

            // A good guess enters the if statement, adds the character to assembleWord, and puts foundChar to true
            for ( int i = 0; i < hangmanWords[n].length(); i++ ) {
                if (guess == (hangmanWords[n].charAt(i))) {
                    assembleWord[i] = hangmanWords[n].charAt(i);            // Assembles the hidden word
                    foundChar = true;
                }
            }

            // Since we found a correct character, check to determine if the word is complete.
            if (foundChar) {
                for (int i = 0; i < hangmanWords[n].length(); i++) {
                    if (assembleWord[i] != hangmanWords[n].charAt(i)) {
                        completedWord = false;                              // never set if the word is guessed
                    }
                }
            }
            else {
                wrongGuess++;
                completedWord = false;                                      // the word is not completed yet
                System.out.println("You have guessed incorrectly " + wrongGuess + "/6 times.");
                if (wrongGuess == 6) {
                    System.out.println("Sorry, you have no more guesses left. The word was " + hangmanWords[n]);
                    status = "EndGame";
                }
            }

            if (completedWord) {
                System.out.println("You've won!  The word was " + hangmanWords[n] + ".");
                status = "EndGame";
            }
            else {
                System.out.print("Your guess so far:  ");
                for (int i = 0; i < hangmanWords[n].length(); i++) {
                    System.out.print(assembleWord[i] + " ");
                }
            }

            System.out.println();
            System.out.println();

            foundChar = false;
            completedWord = true;
        }
        while(! status.equals("EndGame"));

        System.out.println("Thank you for playing!");
    }
}
