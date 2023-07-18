package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * This is the main driver of the sudoku game it will create the board out of
 * cell object and set thier value to 0 to mark that they are not filled this
 * also hold the main logic of filling a space in the baord and check the board
 * is not breaking the rules of sudoku and it will check if a game is compleated
 * 
 * Authors: Bryan Bielawa, Carissa Burnham, Cooper Harris, Drew Marien
 *
 *
 */

public class Board {

	private ArrayList<ArrayList<Cell>> boardCells = new ArrayList<>();
	private int size;
	private int difficulty;
	private int total_values;

	// This is primarily for testing board functionality with preset boards
	/**
	 * this can take in a premade board and create one for testing 
	 * perpuposes 
	 * 
	 * @param boardValues the cell values
	 */
	public Board(ArrayList<ArrayList<Integer>> boardValues) {
		size = boardValues.size();
		total_values = 0;
		makeBoard(boardValues);
		difficulty = 0;
	}

	/**
	 *  This will likely be used by the sudoku program as it can generate a random
	 * board for playing. The difficulty setting scales the difficulty: 1 = hard ~=
	 * 11% of board filled 2 = medium ~= 22% of board filled 3 = easy ~= 33% of
	 * board filled
	 * 
	 * @param size the size of the board it could be 4, 6, or 9
	 * @param difficulty it can be easy medium or hard
	 */
	public Board(int size, int difficulty) {
		this.size = size;
		total_values = 0;
		// int run_count=0;
		ArrayList<ArrayList<Integer>> boardValues = null;
		ArrayList<ArrayList<Cell>> possibleBoard;
		this.difficulty = difficulty;
		ArrayList<Integer> anyErrors;
		do {
			total_values = 0;
			while (total_values == 0) {
				boardValues = generateBoardValueArray(size, difficulty);
				total_values = countValues(boardValues);
			}
			possibleBoard = makeTestBoard(boardValues);
			boardCells = possibleBoard;
			anyErrors = findErrorsInBoard();
			// run_count++;
		} while (anyErrors.size() != 0);
		System.out.println("The number of errors " + anyErrors.size());
		System.out.println("Width " + boardValues.size());
		System.out.println("Lenght " + boardValues.get(1).size());
		// System.out.println("This ran "+run_count+" times");
		makeBoard(boardValues);
		printBoard();
	}

	/**
	 * This is also for testing, it will generate an empty board of a given size.
	 * Every value will be zero in accordance to our empty cell standard. sets
	 * difficulty to zero.
	 * @param size the size of the board it could be 9, 6, or 4
	 */
	public Board(int size) {
		total_values = 0;
		ArrayList<ArrayList<Integer>> boardValues = new ArrayList<>();
		for (int y = 0; y < size; y++) {
			boardValues.add(new ArrayList<>());
			for (int x = 0; x < size; x++) {
				boardValues.get(y).add(0);
			}
		}
		difficulty = 0;
		this.size = size;
		makeBoard(boardValues);
	}

	/**
	 * this will check to see if a board has at least one value no matter the
	 * difficulty level it is used to help make our random board
	 * 
	 * @param vals all the value in a given board
	 * @return count the number of non 0 values found in board
	 */
	private int countValues(ArrayList<ArrayList<Integer>> vals) {
		int count = 0;
		for (int y = 0; y < size; y++) {
			boardCells.add(new ArrayList<>());
			for (int x = 0; x < size; x++) {
				int cell = vals.get(y).get(x);
				if (cell != 0) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * this is used to help generate the random board by taking in an array of
	 * random board values and then choosing a ransom cell and put a value to put in
	 * it based on the board values that could be 0 or a value from the range 1-9
	 * and will shrink based on the size of the board
	 * 
	 * @param boardValues values based on the size of the board to be placed
	 */
	private void makeBoard(ArrayList<ArrayList<Integer>> boardValues) {
		for (int y = 0; y < size; y++) {
			boardCells.add(new ArrayList<>());
			for (int x = 0; x < size; x++) {
				int cell = boardValues.get(y).get(x);
				boardCells.get(y).add(new Cell(cell));
			}
		}
	}

	/**
	 * This will create a 2d arraylist of cells for use in board creation. Takes a
	 * 2d arraylist of integers for argument 1.
	 * 
	 * @param boardValues the cell value
	 * @return a possible board
	 */
	private ArrayList<ArrayList<Cell>> makeTestBoard(ArrayList<ArrayList<Integer>> boardValues) {
		ArrayList<ArrayList<Cell>> retArray = new ArrayList<>();
		for (int y = 0; y < size; y++) {
			retArray.add(new ArrayList<>());
			for (int x = 0; x < size; x++) {
				retArray.get(y).add(new Cell(boardValues.get(y).get(x)));
			}
		}
		return retArray;
	}

	/*
	 * This will print the board with its size and difficulty
	 */
	public void printBoard() {
		System.out.println("Board size=" + size + " Difficulty=" + difficulty);
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (boardCells.get(y).get(x).isPencilmark()) {
					System.out.print(boardCells.get(y).get(x).toString() + " ");
				} else {
					System.out.print(boardCells.get(y).get(x).toString() + "  ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * this will get a cell from the board by using the boardCell to get it row and
	 * colum and return it as an cell classs to the caller
	 * 
	 * @param row the row at which a item in the board is found
	 * @param col the colum at which a item in the board is found
	 * @return a cell object from specified coord
	 */
	public Cell getCell(int row, int col) {
		return boardCells.get(row).get(col);
	}

	/**
	 * this will get a cell from the board by using the boardCell to get it row and
	 * colum and return it as an integer value to the caller
	 * 
	 * @param row the row at which a item in the board is found
	 * @param col the colum at which a item in the board is found
	 * @return the value of the specified cell
	 */
	public int getCellInt(int row, int col) {
		int retCell = Integer.parseInt(boardCells.get(row).get(col).toString());
		return retCell;
	}

	/**
	 * This will set toggle the pencilmark/pencilmark value of a cell. If it is a
	 * pencil mark if will become a regular value. If it isn't a pencil mark it will
	 * become one.
	 * 
	 * @param row the row at which a item in the board is found
	 * @param col the colum at which a item in the board is found
	 */
	public void togglePencilmark(int row, int col) {
		boolean value = boardCells.get(row).get(col).isPencilmark();
		boardCells.get(row).get(col).setPencilmark(!value);
	}

	/**
	 * This will return true if a specified cell is a pencilmark
	 * 
	 * @param row the row at which a item in the board is found
	 * @param col the colum at which a item in the board is found
	 */
	public boolean isCellPencilmark(int row, int col) {
		return boardCells.get(row).get(col).isPencilmark();
	}

	/**
	 * If the size of the board is 9 and the difficulty is set that there are 2 or
	 * less pre-generated values per every 3x3 that means there is 1 or less values
	 * per every 4.5 cells. Out of 81 cells there will be no more than 18 values.
	 * 
	 * This is also challenging because if a board is different sizes that can mess
	 * things up.
	 * 
	 * @param A   Random object, just so I don't need to keep creating them
	 * @param the difficulty function
	 */
	private boolean calcProbability(Random rand, int diff) {
		if (diff == 3) {
			return rand.nextInt(100) <= 33; // I want 30%
		} else if (diff == 2) {
			return rand.nextInt(100) <= 22;// I want 22%
		} else {// if(diff==1)
			return rand.nextInt(100) <= 11;// I want 11%
		}
	}

	/**
	 *  * making this public to help with testing the spread parameter is the max
	 * number of values that can be generated per every 3x3
	 * 
	 * @param width is the size of the board.
	 * @param spread how much of the board is filled
	 * @return the final board 
	 */
	public ArrayList<ArrayList<Integer>> generateBoardValueArray(int width, int spread) {
		ArrayList<ArrayList<Integer>> retArray = new ArrayList<>();
		Random rand = new Random();
		// rand.nextInt(spread) + 1;
		for (int y = 0; y < width; y++) {
			retArray.add(new ArrayList<>());
			for (int x = 0; x < width; x++) {
				if (calcProbability(rand, spread)) {
					retArray.get(y).add(rand.nextInt(width) + 1);
				} else {
					retArray.get(y).add(0);
				}
			}
		}
		return retArray;
	}

	/**
	 * this will return true if the board obeys all the rules of sudoku There is
	 * additional functionality with the parameter. If it is set to true, then it
	 * will behave as if the board is currently full and return true if every number
	 * on the board is valid within the rules. If it is false, it will anticipate
	 * finding pencilmarks and empty cells. The purpose is of the false value is to
	 * verify if a randomly generated board is playable.
	 * 
	 * @return return true if boad is solved
	 */
	public boolean isBoardComplete() {
		ArrayList<Integer> errorInBoard = findErrorsInBoard();
		if (errorInBoard.size() == 0 && isNotEmpty() == true && isNotPencilMarked() == true) {
			return true;
		}
		return false;
	}

	/**
	 * this will search through the board and check to see if all the possible spot
	 * are not pencil marked meaning that the board is full with final answers that
	 * the user want to submit not answers that the user thought might be correct
	 * 
	 * @return true if there is no cell that is pencilMarked as a possible input
	 *         false if there is a cell that is pencilMakred as a possible input
	 */
	private boolean isNotPencilMarked() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Cell curCell = boardCells.get(i).get(j);
				if (curCell.isPencilmark()) {
					return false;
				}
			}
		}
		return true;

	}

	/**
	 * this will search through the board and see if the board is filled with values
	 * if so it will return true otherwise it will return false since there are
	 * empty spaces in the board
	 * 
	 * @return true is the board is not empty and every value is filled false if the
	 *         board has empty spaces
	 */
	private boolean isNotEmpty() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int curVal = boardCells.get(i).get(j).getValue();
				if (curVal == 0) {
					return false;
				}
			}
		}
		return true;

	}

	/**
	 * this will return the current board
	 * 
	 * @return the current board
	 */
	public ArrayList<ArrayList<Cell>> getBoard() {
		return boardCells;
	}

	/**
	 * this will get the current size of the board
	 * 
	 * @return size the size of the board
	 */
	public int getSize() {
		return size;
	}

	/**
	 * this will update the value of a cell in the board so that the game can be
	 * played it will get the cell form the board and set it to a new value denoted
	 * by the user
	 * 
	 * @param row    the row at which the cell to be updated resides
	 * @param col    the colum at which the cell to be updated resides
	 * @param newVal the value the cell will be changed to
	 */
	public void updateBoardVal(int row, int col, int newVal) {
		Cell curCell = boardCells.get(row).get(col);
		curCell.setValue(newVal);
	}

	/**
	 * this will find the cordinates of all errors that can be found in the given
	 * board if they exist and add them to an array that way it can be marked on the
	 * gui where errors from the user accured
	 * 
	 * @return ArrayList<ints> this stores all the coords where a error accured and
	 *         return it to the user
	 */
	public ArrayList<Integer> findErrorsInBoard() {
		ArrayList<Integer> errors = new ArrayList<Integer>();
		HashSet<String> seen = new HashSet<String>();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int curVal = boardCells.get(i).get(j).getValue();

				if (curVal != 0) {
					if (size == 9) {
						if (!seen.add(curVal + " found in row " + i) || !seen.add(curVal + " found in column " + j)
								|| !seen.add(curVal + "found in sub box " + i / 3 + "-" + j / 3)) {
							errors.add(i);
							errors.add(j);
						}
					}
					if (size == 6) {
						if (!seen.add(curVal + " found in row " + i) || !seen.add(curVal + " found in column " + j)
								|| !seen.add(curVal + "found in sub box " + i / 2 + "-" + j / 3)) {
							errors.add(i);
							errors.add(j);
						}
					}
					if (size == 4) {
						if (!seen.add(curVal + " found in row " + i) || !seen.add(curVal + " found in column " + j)
								|| !seen.add(curVal + "found in sub box " + i / 2 + "-" + j / 2)) {
							errors.add(i);
							errors.add(j);
						}
					}
				}
			}

		}
		return errors;
	}
}
