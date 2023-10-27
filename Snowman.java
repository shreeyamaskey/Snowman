/**
 * Add comments at the heading describing what the program does
 * as well as who authored it.
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.lang.*;

public class Snowman {
	List<String> wordList = new ArrayList<String>();
	Set<Character> lettersUsed;
	
	
	int incorrect = 6;
	int guesses = 0;

	/**
	 * Read in the list of words
	 */
	public void readWords(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));

		while (s.hasNext())
			wordList.add(s.next());

		s.close();
	}

	/**
	 * Selects a random word from the list
	 * and returns it.
	 */
	public String getWord() {
		// returns a random word from wordList

		Random r = new Random();

		return wordList.get(r.nextInt(wordList.size()));
	}
	

	/**
	 * Plays the game. Currently very limited functionality.
	 */
	public void playGame(String word) {
		char nextChoice;
		
		// an array of characters representing
		// the guessed word.
		char[] guess = new char[word.length()];
		
		for (int i = 0; i < guess.length; i++) {
			guess[i] = '_';
		}
		
		lettersUsed = new ArraySet<Character>();
		Scanner reader = new Scanner(System.in);
		
		boolean flag;
		int counter = 0; 
		
		for (int i = 0; i < guess.length; i++) {
			System.out.print(" " + guess[i] + " ");
		}
		System.out.println();
		
		while (guesses < incorrect) {
			
			flag = false;

		

			System.out.print("Your choice: ");
			nextChoice = reader.next().charAt(0);
			nextChoice = Character.toLowerCase(nextChoice);

			System.out.println("you entered " + nextChoice);
			
			if (Character.isLetter(nextChoice)) {
				if(!(lettersUsed.contains(nextChoice))) {
					lettersUsed.add(nextChoice);
				}
				else {
					System.out.println("You have already guessed this letter.");
					continue;
				}
			}
			else {
				System.out.println("Please enter a character in between \'a...z\' and \'A...Z\'");
				continue;
			}
			
			
			
			for (int i = 0; i < guess.length; i++) {
				//guess[i] = '_';
				if(nextChoice == word.charAt(i)) {
					guess[i] = nextChoice;
					flag = true;
					counter++;
				}

			
			}
			 //boolean variable as a flag
			if (!flag) {
				System.out.println(nextChoice + " does not appear.");
				guesses++;;
			}
			
			for (int i = 0; i < guess.length; i++) {
				System.out.print(" " + guess[i] + " ");
			}
			System.out.println();

			System.out.println("Incorrect guesses: " + guesses);
			
			if (counter == guess.length) {
				System.out.println("Congrats! You won!");
				break;
			}
			
		}
		
		if(guesses == 6) {
			System.out.println("I'm sorry, you lost!");
			System.out.print("The word was " + word);
		}
		
		
	}

	public static void main(String[] args) {
		Snowman game = new Snowman();

		try {
			game.readWords("words.txt");
			
			String word = game.getWord();	
			System.out.println(word);
			game.playGame(word);
			
		} catch (FileNotFoundException fnf) {
			System.err.println("words.txt file Not Found");
		}

		
	}

}