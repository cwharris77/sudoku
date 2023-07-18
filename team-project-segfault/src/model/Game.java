package model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import view.BoardGUI;

/**
 * 
 * Authors: Bryan Bielawa, Carissa Burnham, Cooper Harris, Drew Marien
 *
 *
 * This class is used to facilitate all interactions between Board.java and
 * BoardGUI.java. This class also plays sound for when the gui takes certain
 * actions. All sound is directed through a media player
 */

public class Game {
	private Board board;
	private int size;
	//private MediaPlayer mediaPlayer;

	/**
	 * This will generate a board of a given size with a spread of randomly
	 * generated values.
	 * 
	 * @param size       is the size of the board, please use either 4,6, or 9
	 * @param difficulty is the difficulty, 3=easy, 2=medium, 1=hard
	 */
	public Game(int size, int difficulty) {
		this.size = size;
		board = new Board(size, difficulty);
	}

	/**
	 * Generates an empty board of the given size This constructor is used to
	 * initalize the toolkit for the sound to play. DO NOT TOUCH THIS
	 * 
	 * @param int size of board
	 */
	public Game(int size) {
		this.size = size;
		playSound("wii_startup");
	}

	/**
	 * This will reset the game and create a new board
	 * 
	 * @param newSize       is the new size of the board, use 4, 6, or 9
	 * @param newDifficulty is the difficulty, 3=easy, 2=medium, 1=hard
	 */
	public void newGame(int newSize, int newDifficulty) {
		size = newSize;
		board = new Board(size, newDifficulty);
	}

	/**
	 *  This will play the level_up.mp3 sound. 
	 *  This should be used to play a sound after an action.
	 */
	public void playNewGameSound() {
		playSound("level_up");
	}

	/**
	 * This will print the board into the console. Pencil mark values will have a *
	 * in front of the value This will also print the board size and difficulty.
	 */
	public void printState() {
		board.printBoard();
	}

	/**
	 * This function will return the width of the board as an int value. The
	 * width/size value of the board is set when a new board is created.
	 * 
	 * @return int value of board size.
	 */
	public int getSize() {
		return board.getSize();
	}

	/**
	 * This will return an int value for the value of the specified coordinate value
	 * on the board. Board range is 0 to size-1
	 * 
	 * @param row int of the board value
	 * @param col int of the board value
	 * @return int value of board position
	 */
	public int getCellValue(int row, int col) {
		return board.getCellInt(row, col);
	}

	/**
	 * This will change a value of the specified coordinate on the board.
	 * 
	 * @param row     int of the board position to change
	 * @param col     int of the board position to change
	 * @param value   is the new int value for the board positon
	 */
	public void makeMove(int row, int col, int value) {
		board.updateBoardVal(row, col, value);
	}

	/**
	 * This will toggle the pencil mark value of a specified coordinate on the
	 * board. If the value is true, it switches to false and if it is true it turns
	 * false.
	 * 
	 * @param row int of the board position to change
	 * @param col int of the board position to change
	 */
	public void togglePencilMark(int row, int col) {
		board.togglePencilmark(row, col);
	}

	/**
	 * This will return the boolean pencil mark value of a specified coordinate on
	 * the board.
	 * 
	 * @param row int of the board position
	 * @param col int of the board position
	 * @return boolean pencilmark value of board cell
	 */
	public boolean isCellPencilMark(int row, int col) {
		return board.isCellPencilmark(row, col);
	}

	/**
	 * This will run the isBoardComplete() function from Board class. This is a
	 * complex function that searches for any error within the sudoku board. If any
	 * error or empty coordinate value is found this will return false, if not then
	 * true. This will also play a victory sound.
	 * 
	 * @return boolean value for board completion
	 */
	public boolean isWon() {
		boolean isWonTrue = board.isBoardComplete();
		if (isWonTrue) {
			playSound("win_sound");
		}
		return isWonTrue;
	}

	/**
	 * If any errors are present on the board, this function will find it. This
	 * should only be run when the function complete.
	 * 
	 * @return A string containing coordinate x,y values for coordinate on the board
	 *         that violate the rules of sudoku.
	 */
	public String getErrorsStr() {
		ArrayList<Integer> retArray = board.findErrorsInBoard();
		String retStr = (retArray.size() / 2) + " Errors in board found at coordinates: " + "\n";
		for (int i = 0; i < retArray.size(); i += 2) {
			retStr += " (" + retArray.get(i) + ", " + retArray.get(i + 1) + ") ";
		}
		return retStr;
	}

	/**
	 * If any errors are present on the board, this function will find it. This
	 * should only be run when the function complete.
	 * 
	 * @return ArrayList<Integer> containing coordinate x,y values for coordinate on
	 *         the board that violate the rules of sudoku.
	 */
	public ArrayList<Integer> getErrorsArray() {
		ArrayList<Integer> retArray = board.findErrorsInBoard();
		if (retArray.size() > 0) {
			System.out.println("Errors in board found at (x,y) -> " + retArray.toString());
		}
		return retArray;
	}

	/**
	 * This is the current playSound function. This has gone through several
	 * iterations and its purpose has remained the same. It takes a soundfile name,
	 * then plays that sound with an AudioClip. We decided to use an AudioClip
	 * rather than a MediaPlayer because our .mp3s are smaller files than longer
	 * soungs and AudioClips are optimized for shorter audio files.
	 * 
	 * @param soundname
	 */
	private void playSound(String soundname) {
		String path = "soundfiles/" + soundname + ".mp3";
		File file = new File(path);
		URI uri = file.toURI();
		System.out.println("URI: " + uri);
		// Platform.startup(new Waiter());
		Media media = new Media(uri.toString());
		AudioClip sound = new AudioClip(media.getSource());
		sound.play();
	}
}

/**
 * This is the runable for the media player
 * @author Bryan Bielawa, Carissa Burnham, Cooper Harris, Drew Marien
 * This is only used for sound.
 */
class Waiter implements Runnable {
	@Override
	public void run() {
		Platform.exit();
	}
}
