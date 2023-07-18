import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Model the tray of dice in the game Boggle. A DiceTray can be constructed with
 * a 4x4 array of characters for testing.
 *
 * A 2nd constructor with no arguments can be added later to simulate the
 * shaking of 16 dice and selection of one side.
 *
 * @author Cooper Harris
 */
public class DiceTray {

	private char[][] theBoard;

	/**
	 * Construct a DiceTray object using a hard-coded 2D array of chars. One is
	 * provided in the given unit test. You can create others.
	 */
	public DiceTray(char[][] newBoard) {
		theBoard = newBoard;
	}

	public DiceTray() {
		// TODO: This 2nd constructor in DiceTray rolls and randomly places all 16
		// Boggle dice in the tray
		this.theBoard = new char[4][4];

		char[] sides = new char[] { 
				new Die(new char[] { 'L', 'R', 'Y', 'T', 'T', 'E' }).getRandomSide(),
				new Die(new char[] { 'A', 'N', 'A', 'E', 'E', 'G' }).getRandomSide(),
				new Die(new char[] { 'A', 'F', 'P', 'K', 'F', 'S' }).getRandomSide(),
				new Die(new char[] { 'Y', 'L', 'D', 'E', 'V', 'R' }).getRandomSide(),

				new Die(new char[] { 'V', 'T', 'H', 'R', 'W', 'E' }).getRandomSide(),
				new Die(new char[] { 'I', 'D', 'S', 'Y', 'T', 'T' }).getRandomSide(),
				new Die(new char[] { 'X', 'L', 'D', 'E', 'R', 'I' }).getRandomSide(),
				new Die(new char[] { 'Z', 'N', 'R', 'N', 'H', 'L' }).getRandomSide(),

				new Die(new char[] { 'E', 'G', 'H', 'W', 'N', 'E' }).getRandomSide(),
				new Die(new char[] { 'O', 'A', 'T', 'T', 'O', 'W' }).getRandomSide(),
				new Die(new char[] { 'H', 'C', 'P', 'O', 'A', 'S' }).getRandomSide(),
				new Die(new char[] { 'N', 'M', 'I', 'H', 'U', 'Q' }).getRandomSide(),

				new Die(new char[] { 'S', 'E', 'O', 'T', 'I', 'S' }).getRandomSide(),
				new Die(new char[] { 'M', 'T', 'O', 'I', 'C', 'U' }).getRandomSide(),
				new Die(new char[] { 'E', 'N', 'S', 'I', 'E', 'U' }).getRandomSide(),
				new Die(new char[] { 'O', 'B', 'B', 'A', 'O', 'J' }).getRandomSide() };

		Random r = new Random();
		int sidesIndex = r.nextInt(16);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				while (sides[sidesIndex] == ' ') {
					sidesIndex = r.nextInt(16);
				}
				theBoard[i][j] = sides[sidesIndex];
				sides[sidesIndex] = ' ';
			}
		}
	}

	/**
	 * Return true if attempt can found on the board following the rules of Boggle
	 * like the same letter can only be used once.
	 *
	 * @param attempt - A word that may be in the board by connecting consecutive
	 *                letters
	 *
	 * @return True if search is found
	 */
	public boolean found(String attempt) {
		attempt = attempt.toUpperCase();

		if (attempt.length() < 3) {
			return false;
		}

		// remove the u from attempt string
		for (int x = 0; x < attempt.length(); x++) {
			if (attempt.charAt(x) == 'Q') {
				attempt = attempt.substring(0, x + 1) + attempt.substring(x + 2);
			}
		}

		// start from every position in the grid
		for (int x = 0; x < theBoard[0].length; x++) {
			for (int y = 0; y < theBoard.length; y++) {
				boolean wordFound = backtrack(attempt, x, y, 0);

				if (wordFound) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * A helper method to recurse on the given position and check all neighbors for
	 * the next letter in the word
	 * 
	 * @param attempt - the user guess
	 * @param x       - the current x position
	 * @param y       - the current y position
	 * @param index   - the index of the letter being searched for in the attempt
	 * @return - true if the word is found
	 */
	private boolean backtrack(String attempt, int x, int y, int index) {
		if (!isValid(x, y)) {
			return false;
		} else if (theBoard[x][y] != attempt.charAt(index)) {
			return false;
		} else if (index == attempt.length() - 1) {
			return true;
		}

		// choose
		char curLetter = theBoard[x][y];
		// replace the current character with a placeholder to make
		// sure the same spot isn't chose again
		theBoard[x][y] = ' ';

		// explore
		boolean wordFound =
				// up, down, left, right
				backtrack(attempt, x, y - 1, index + 1) || backtrack(attempt, x, y + 1, index + 1)
						|| backtrack(attempt, x - 1, y, index + 1) || backtrack(attempt, x + 1, y, index + 1) ||

						// up-right, down-right, up-left, down-left
						backtrack(attempt, x + 1, y + 1, index + 1) || backtrack(attempt, x + 1, y - 1, index + 1)
						|| backtrack(attempt, x - 1, y + 1, index + 1) || backtrack(attempt, x - 1, y - 1, index + 1);

		// unchoose
		theBoard[x][y] = curLetter;

		return wordFound;
	}

	/**
	 * Private helper to determine if a given x, y coordinate is in the grid
	 * 
	 * @param x
	 * @param y
	 * @return true if the position is in the grid
	 */
	private boolean isValid(int x, int y) {
		return (x < theBoard[0].length && x >= 0) && (y < theBoard.length && y >= 0);
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < theBoard.length; i++) {
			s += Arrays.toString(theBoard[i]);
			s += '\n';
		}
		return s;
	}

	private class Die {
		private char[] sides;

		public Die(char[] sides) {
			this.sides = sides;
		}

		public char getRandomSide() {
			Random r = new Random();
			int i = r.nextInt(6);

			return sides[i];
		}

	}
}