
/**
 * @author Cooper Harris
 * 
 * This program takes input from the user to play a console based
 * version of boggle
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class BoggleConsole {

	public static void main(String[] args) throws FileNotFoundException {
		// creates a hashtable of valid words from the dictionary file given
		Hashtable<String, String> dict = Boggle.getDictionary("BoggleWords.txt");

		System.out.println("Start a new game of Boggle:\n");
		DiceTray tray = new DiceTray();
		System.out.println(tray.toString());

		System.out.println("(Q represents Qu in this game)\n");
		System.out.println("Enter words or ZZ to quit:\n");

		ArrayList<String> userWords = getInput();

		Hashtable<Integer, ArrayList<String>> scoreTable = Boggle.getScore(userWords, dict, tray);

		/*
		 * hashtable indeces: 0: score 1: foundWords 2: wrongWords 3: missedWords
		 */
		printScore(Integer.parseInt(scoreTable.get(0).get(0)), scoreTable.get(1), scoreTable.get(2), scoreTable.get(3));

	}

	/**
	 * This function takes user input to get all the words they found in the dice
	 * tray
	 * 
	 * @return and arraylist of user words entered
	 */
	public static ArrayList<String> getInput() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> userWords = new ArrayList<>();

		while (scanner.hasNext()) {
			String next = scanner.next();
			if (next.equals("ZZ")) {
				break;
			}
			userWords.add(next);
		}
		scanner.close();
		System.out.println();

		return userWords;
	}

	/**
	 * Prints the users score, words they found, words that were incorrect, and
	 * words the computer found but they missed.
	 * 
	 * @param score
	 * @param foundWords  an arraylist of the words they found
	 * @param wrongWords  an arraylist of the words entered that were not in the
	 *                    dict
	 * @param missedWords words the computer found but the user didn't
	 */
	private static void printScore(int score, ArrayList<String> foundWords, ArrayList<String> wrongWords,
			ArrayList<String> missedWords) {

		System.out.println("Your score: " + score + "\n");

		System.out.println("Words you found:");
		System.out.println("================");

		int i = 10;
		for (String word : foundWords) {
			System.out.print(word + " ");
			// formatting for newline once a few words have been printed
			if (i % 9 == 0) {
				System.out.println();
			}
			i++;
		}
		System.out.println("\n");

		System.out.println("Incorrect words:\r\n" + "================");

		i = 9;
		for (String word : wrongWords) {
			System.out.print(word + " ");
			if (i % 10 == 0) {
				System.out.println();
			}
			i++;
		}
		System.out.println("\n");

		System.out.println("You could have found " + missedWords.size() + " more words.\r\n"
				+ "The computer found all of your words plus these:");
		System.out.println("===============================================");

		i = 10;
		for (String word : missedWords) {
			System.out.print(word + " ");
			if (i % 9 == 0) {
				System.out.println();
			}
			i++;
		}
	}
}
