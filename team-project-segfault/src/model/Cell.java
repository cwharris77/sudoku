package model;

/**
 * this will be making the cell for the board of the sudoku game it will store
 * the value of a given cell in the board. In addition to the pencil mark which
 * is a boolean to show if the cell has a final value that is ready to be
 * checked or not and the user is placing a place holder in the cell.
 * 
 * Authors: Bryan Bielawa, Carissa Burnham, Cooper Harris, Drew Marien
 *
 */

public class Cell {

	/*
	 * We need to decide what this is going to look like
	 * 
	 * it might take a few iterations too
	 * 
	 * We need a way to make these into pencil marks
	 * 
	 */
	private int value;
	private boolean isPencilmark; // starts as false

	// set to -1 if value is meant to be blank
	/**
	 * this is the constructor for the cell class
	 * 
	 * @param value the int containd in the cell
	 */
	public Cell(int value) {
		this.value = value;
		isPencilmark = false;
	}

	/**
	 * this is the second constroctor of cell
	 * that can do a pencil mark as well 
	 * 
	 * @param value the value held in the cell
	 * @param isPencilmark to show a final move verses a non final move
	 */
	public Cell(int value, boolean isPencilmark) {
		this.value = value;
		this.isPencilmark = isPencilmark;
	}

	/**
	 * this will get the value that is stored in the cell
	 * 
	 * @return value is the integeer stored in the cell
	 */
	public int getValue() {
		return value;
	}

	/**
	 * this will set a new value that will be stored in the cell
	 * 
	 * @param newValue a new integer value to be stored in a cell
	 */
	public void setValue(int newValue) {
		value = newValue;
	}

	/**
	 * this will check to see if a the cell is pencil marked that is to say this
	 * will check if the cell value is considered the final value or if it is just a
	 * guess
	 * 
	 * @return true if the cell is pencil marked false if the cell is not penciled
	 *         marked
	 */
	public boolean isPencilmark() {
		return isPencilmark;
	}

	/**
	 * this will set the pencil mark to true of false which will state if the cell
	 * has a final answer or a pencil marked value
	 * 
	 * @param setPencilmarkValue is a boolean of true or false to be set the pencil
	 *                           mark to show if it a final answer or not
	 */
	public void setPencilmark(boolean setPencilmarkValue) {
		isPencilmark = setPencilmarkValue;
	}

	/**
	 * this will changed the pencil mark value to false if true or true if it is
	 * false this is use for easy switching of boolean value after the inital
	 * setting of the pencil mark
	 */
	public void togglePencilmark() {
		isPencilmark = !isPencilmark;
	}

	/**
	 * this will return a string of the cell value this is used mainly for testing
	 * 
	 * @return a string of the cells value
	 */
	public String toString() {
		if (isPencilmark) {
			return "*" + value;
		}
		return "" + value;
	}

}
