package tests;
/**
 * this will test different aspect of the board
 * class
 * 
 * Authors: Bryan Bielawa, Carissa Burnham, Cooper Harris, Drew Marien
 *
 *
 */
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Board;
import model.Cell;


class TestBoard {
	
	/**
	 * this will test the first constructor in the board class that takes a premade
	 * board and show it to the user
	 */
	@Test
	void testBoardConstructor1() {
		ArrayList<ArrayList<Integer>> game = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row1 = new ArrayList<Integer>();
		ArrayList<Integer> row2 = new ArrayList<Integer>();
		ArrayList<Integer> row3 = new ArrayList<Integer>();
		// add three value to first row
		row1.add(1);
		row1.add(2);
		row1.add(3);
		// add three value to the second row
		row2.add(4);
		row2.add(5);
		row2.add(6);
		// add three value to third row
		row3.add(7);
		row3.add(8);
		row3.add(9);

		game.add(row1);
		game.add(row2);
		game.add(row3);
		System.out.println("this test the first constructor\n");
		Board b = new Board(game);
		b.printBoard();
		System.out.println();
	}

	/**
	 * this will test the second constructor in the board class to see if it works
	 * to and if can make boards based off of the difficulty and show them to the
	 * user
	 */
	@Test
	void testBoardConstructor2() {
		System.out.println("this will test the second board constructort\n");
		// make an easy board
		Board easyBoard = new Board(9, 1);
		// makes a medium board
		Board mediumBoard = new Board(9, 2);
		// make a hard board
		Board hardBoard = new Board(9, 3);
		easyBoard.printBoard();
		System.out.println();
		mediumBoard.printBoard();
		System.out.println();
		hardBoard.printBoard();
		System.out.println();
		
		System.out.println("4x4 board below");
		Board fourBoard1 = new Board(4, 1);
		Board fourBoard2 = new Board(4, 2);
		Board fourBoard3 = new Board(4, 3);
		fourBoard1.printBoard();
		fourBoard2.printBoard();
		fourBoard3.printBoard();
		
	}

	/**
	 * this will test the third consturctor and test to see if you give it a size it
	 * can make the correct size board and print it
	 */
	@Test
	void testBoardConstructor3() {
		System.out.println("This will test the third constructor of the board\n");
		// make an small sized board of a three row and colum
		Board sizeOne = new Board(3);
		// makes a medium sized board of six rows and colums
		Board sizeTwo = new Board(6);
		// make a large sized board board of nine rows and colums
		Board sizeThree = new Board(9);
		Cell curCell = sizeThree.getCell(0, 0);
		curCell.setPencilmark(true);
		sizeOne.printBoard();
		System.out.println();
		sizeTwo.printBoard();
		System.out.println();
		sizeThree.printBoard();
		System.out.println();
	}

	/**
	 * this will test if you can reteve a cell from the board and it value is
	 * correct
	 */
	@Test
	void testGetCell() {
		ArrayList<ArrayList<Integer>> game = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row1 = new ArrayList<Integer>();
		ArrayList<Integer> row2 = new ArrayList<Integer>();
		ArrayList<Integer> row3 = new ArrayList<Integer>();
		// add three value to first row
		row1.add(1);
		row1.add(2);
		row1.add(3);
		// add three value to the second row
		row2.add(4);
		row2.add(5);
		row2.add(6);
		// add three value to third row
		row3.add(7);
		row3.add(8);
		row3.add(9);

		game.add(row1);
		game.add(row2);
		game.add(row3);
		Board b = new Board(game);
		// this check to see if the return value mataches what
		// is in the board at that location
		assertEquals(3, b.getCellInt(0, 2));
		assertEquals(5, b.getCellInt(1, 1));
		assertEquals(9, b.getCellInt(2, 2));
	}

	/**
	 * this will test if the board is compleated and if the
	 * that is that a full board of a 3x3 of 3x3 follows all
	 * the rules of sudouko and can be shown to be solved correctly
	 */
	@Test
	void testCompleatedBoard() {
		ArrayList<ArrayList<Integer>> game = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row1 = new ArrayList<Integer>();
		ArrayList<Integer> row2 = new ArrayList<Integer>();
		ArrayList<Integer> row3 = new ArrayList<Integer>();
		// add three value to first row
		row1.add(1);
		row1.add(2);
		row1.add(3);
		// add three value to the second row
		row2.add(4);
		row2.add(5);
		row2.add(6);
		// add three value to third row
		row3.add(7);
		row3.add(8);
		row3.add(9);

		game.add(row1);
		game.add(row2);
		game.add(row3);
		Board b = new Board(game);
		// this check to see if the board is complete which this board
		// is not the next board will be
		assertTrue(b.isBoardComplete());

		ArrayList<ArrayList<Integer>> gameTwo = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> twoRow1 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow2 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow3 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow4 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow5 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow6 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow7 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow8 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow9 = new ArrayList<Integer>();
		// add three 9 to first row of the second game
		twoRow1.add(3);
		twoRow1.add(9);
		twoRow1.add(1);
		twoRow1.add(2);
		twoRow1.add(8);
		twoRow1.add(6);
		twoRow1.add(5);
		twoRow1.add(7);
		twoRow1.add(4);

		// add 9 value to the second row of the second game
		twoRow2.add(4);
		twoRow2.add(8);
		twoRow2.add(7);
		twoRow2.add(3);
		twoRow2.add(5);
		twoRow2.add(9);
		twoRow2.add(1);
		twoRow2.add(2);
		twoRow2.add(6);
		// add 9 value to third row of the second game
		twoRow3.add(6);
		twoRow3.add(5);
		twoRow3.add(2);
		twoRow3.add(7);
		twoRow3.add(1);
		twoRow3.add(4);
		twoRow3.add(8);
		twoRow3.add(3);
		twoRow3.add(9);
		// add 9 value to 4th row of the second game
		twoRow4.add(8);
		twoRow4.add(7);
		twoRow4.add(5);
		twoRow4.add(4);
		twoRow4.add(3);
		twoRow4.add(1);
		twoRow4.add(6);
		twoRow4.add(9);
		twoRow4.add(2);
		// add 9 value to 5th row of the second game
		twoRow5.add(2);
		twoRow5.add(1);
		twoRow5.add(3);
		twoRow5.add(9);
		twoRow5.add(6);
		twoRow5.add(7);
		twoRow5.add(4);
		twoRow5.add(8);
		twoRow5.add(5);
		// add 9 value to 6th row of the second game
		twoRow6.add(9);
		twoRow6.add(6);
		twoRow6.add(4);
		twoRow6.add(5);
		twoRow6.add(2);
		twoRow6.add(8);
		twoRow6.add(7);
		twoRow6.add(1);
		twoRow6.add(3);
		// add 9 value to 7th row of the second game
		twoRow7.add(1);
		twoRow7.add(4);
		twoRow7.add(9);
		twoRow7.add(6);
		twoRow7.add(7);
		twoRow7.add(3);
		twoRow7.add(2);
		twoRow7.add(5);
		twoRow7.add(8);
		// add 9 value to 8th row of the second game
		twoRow8.add(5);
		twoRow8.add(3);
		twoRow8.add(8);
		twoRow8.add(1);
		twoRow8.add(4);
		twoRow8.add(2);
		twoRow8.add(9);
		twoRow8.add(6);
		twoRow8.add(7);
		// add 9 value to 9th row of the second game
		twoRow9.add(7);
		twoRow9.add(2);
		twoRow9.add(6);
		twoRow9.add(8);
		twoRow9.add(9);
		twoRow9.add(5);
		twoRow9.add(3);
		twoRow9.add(4);
		twoRow9.add(1);

		// add all the row to the board
		// and then test to see if the board follow
		// the rules which it does
		gameTwo.add(twoRow1);
		gameTwo.add(twoRow2);
		gameTwo.add(twoRow3);
		gameTwo.add(twoRow4);
		gameTwo.add(twoRow5);
		gameTwo.add(twoRow6);
		gameTwo.add(twoRow7);
		gameTwo.add(twoRow8);
		gameTwo.add(twoRow9);
		Board bTwo = new Board(gameTwo);
		assertTrue(bTwo.isBoardComplete());
		assertTrue(bTwo.isBoardComplete());

	}

	/**
	 * this test will see if the board is compleated correctly
	 * but it is not since there are values that go beyond the
	 * value of 9 which break a sudoku rule
	 */
	@Test
	void testBoardBadInput() {
		ArrayList<ArrayList<Integer>> gameTwo1 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> twoRow11 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow21 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow31 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow41 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow51 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow61 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow71 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow81 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow91 = new ArrayList<Integer>();
		// add three 9 to first row of the second game
		twoRow11.add(3);
		twoRow11.add(9);
		twoRow11.add(1);
		twoRow11.add(2);
		twoRow11.add(8);
		twoRow11.add(6);
		twoRow11.add(5);
		twoRow11.add(7);
		twoRow11.add(4);

		// add 9 value to the second row of the second game
		twoRow21.add(4);
		twoRow21.add(8);
		twoRow21.add(7);
		twoRow21.add(3);
		twoRow21.add(5);
		twoRow21.add(9);
		twoRow21.add(1);
		twoRow21.add(2);
		twoRow21.add(6);
		// add 9 value to third row of the second game
		twoRow31.add(6);
		twoRow31.add(5);
		twoRow31.add(2);
		twoRow31.add(7);
		twoRow31.add(1);
		twoRow31.add(4);
		twoRow31.add(8);
		twoRow31.add(3);
		twoRow31.add(9);
		// add 9 value to 4th row of the second game
		twoRow41.add(8);
		twoRow41.add(7);
		twoRow41.add(5);
		twoRow41.add(4);
		twoRow41.add(3);
		twoRow41.add(1);
		twoRow41.add(6);
		twoRow41.add(9);
		twoRow41.add(2);
		// add 9 value to 5th row of the second game
		twoRow51.add(2);
		twoRow51.add(1);
		twoRow51.add(3);
		twoRow51.add(9);
		twoRow51.add(6);
		twoRow51.add(7);
		twoRow51.add(4);
		twoRow51.add(8);
		twoRow51.add(5);
		// add 9 value to 6th row of the second game
		twoRow61.add(9);
		twoRow61.add(6);
		twoRow61.add(4);
		twoRow61.add(5);
		twoRow61.add(1);
		twoRow61.add(8);
		twoRow61.add(7);
		twoRow61.add(1);
		twoRow61.add(3);
		// add 9 value to 7th row of the second game
		twoRow71.add(1);
		twoRow71.add(4);
		twoRow71.add(9);
		twoRow71.add(6);
		twoRow71.add(52);
		twoRow71.add(3);
		twoRow71.add(2);
		twoRow71.add(5);
		twoRow71.add(8);
		// add 9 value to 8th row of the second game
		twoRow81.add(5);
		twoRow81.add(3);
		twoRow81.add(0);
		twoRow81.add(1);
		twoRow81.add(4);
		twoRow81.add(0);
		twoRow81.add(50);
		twoRow81.add(6);
		twoRow81.add(7);
		// add 9 value to 9th row of the second game
		twoRow91.add(7);
		twoRow91.add(2);
		twoRow91.add(6);
		twoRow91.add(8);
		twoRow91.add(9);
		twoRow91.add(5);
		twoRow91.add(100);
		twoRow91.add(4);
		twoRow91.add(1);

		gameTwo1.add(twoRow11);
		gameTwo1.add(twoRow21);
		gameTwo1.add(twoRow31);
		gameTwo1.add(twoRow41);
		gameTwo1.add(twoRow51);
		gameTwo1.add(twoRow61);
		gameTwo1.add(twoRow71);
		gameTwo1.add(twoRow81);
		gameTwo1.add(twoRow91);
		Board bthree = new Board(gameTwo1);
		assertFalse(bthree.isBoardComplete());
	}

	/**
	 * this will test a board that is outside 
	 * the range of the largest board that can 
	 * be solved in sudoku
	 */
	@Test
	void testBoardBadInput2() {
		Board EmptyBoard = new Board(10);
		assertFalse(EmptyBoard.isBoardComplete());
		assertFalse(EmptyBoard.isBoardComplete());
	}
	
	/**
	 * this will test a board who has missing values
	 * being that they are not filled in 
	 */
	@Test
	void testBoardbadInput3() {
		ArrayList<ArrayList<Integer>> gameTwo1 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> twoRow11 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow21 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow31 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow41 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow51 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow61 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow71 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow81 = new ArrayList<Integer>();
		ArrayList<Integer> twoRow91 = new ArrayList<Integer>();
		// add three 9 to first row of the second game
		twoRow11.add(5);
		twoRow11.add(9);
		twoRow11.add(1);
		twoRow11.add(2);
		twoRow11.add(8);
		twoRow11.add(6);
		twoRow11.add(5);
		twoRow11.add(7);
		twoRow11.add(4);

		// add 9 value to the second row of the second game
		twoRow21.add(4);
		twoRow21.add(8);
		twoRow21.add(7);
		twoRow21.add(3);
		twoRow21.add(5);
		twoRow21.add(9);
		twoRow21.add(1);
		twoRow21.add(0);
		twoRow21.add(6);
		// add 9 value to third row of the second game
		twoRow31.add(6);
		twoRow31.add(5);
		twoRow31.add(2);
		twoRow31.add(7);
		twoRow31.add(1);
		twoRow31.add(4);
		twoRow31.add(8);
		twoRow31.add(3);
		twoRow31.add(9);
		// add 9 value to 4th row of the second game
		twoRow41.add(8);
		twoRow41.add(7);
		twoRow41.add(5);
		twoRow41.add(4);
		twoRow41.add(3);
		twoRow41.add(1);
		twoRow41.add(6);
		twoRow41.add(9);
		twoRow41.add(2);
		// add 9 value to 5th row of the second game
		twoRow51.add(2);
		twoRow51.add(1);
		twoRow51.add(3);
		twoRow51.add(9);
		twoRow51.add(6);
		twoRow51.add(7);
		twoRow51.add(4);
		twoRow51.add(8);
		twoRow51.add(5);
		// add 9 value to 6th row of the second game
		twoRow61.add(9);
		twoRow61.add(6);
		twoRow61.add(4);
		twoRow61.add(5);
		twoRow61.add(0);
		twoRow61.add(8);
		twoRow61.add(7);
		twoRow61.add(1);
		twoRow61.add(3);
		// add 9 value to 7th row of the second game
		twoRow71.add(1);
		twoRow71.add(4);
		twoRow71.add(9);
		twoRow71.add(0);
		twoRow71.add(7);
		twoRow71.add(3);
		twoRow71.add(2);
		twoRow71.add(5);
		twoRow71.add(8);
		// add 9 value to 8th row of the second game
		twoRow81.add(5);
		twoRow81.add(3);
		twoRow81.add(0);
		twoRow81.add(1);
		twoRow81.add(4);
		twoRow81.add(2);
		twoRow81.add(9);
		twoRow81.add(6);
		twoRow81.add(7);
		// add 9 value to 9th row of the second game
		twoRow91.add(7);
		twoRow91.add(2);
		twoRow91.add(6);
		twoRow91.add(0);
		twoRow91.add(9);
		twoRow91.add(5);
		twoRow91.add(0);
		twoRow91.add(4);
		twoRow91.add(1);

		gameTwo1.add(twoRow11);
		gameTwo1.add(twoRow21);
		gameTwo1.add(twoRow31);
		gameTwo1.add(twoRow41);
		gameTwo1.add(twoRow51);
		gameTwo1.add(twoRow61);
		gameTwo1.add(twoRow71);
		gameTwo1.add(twoRow81);
		gameTwo1.add(twoRow91);
		Board bthree = new Board(gameTwo1);
		Cell curCell = bthree.getCell(0, 0);
		Cell SecondCell = bthree.getCell(8, 3);
		curCell.setPencilmark(true);
		curCell.setValue(5);
		assertFalse(bthree.isBoardComplete());
		assertFalse(bthree.isBoardComplete());
		curCell.togglePencilmark();
		SecondCell.setValue(8);
		SecondCell.toString();
		Cell newCell = new Cell(5, true);
		assertTrue(newCell.isPencilmark());
		newCell.togglePencilmark();
		assertFalse(newCell.isPencilmark());
		assertFalse(bthree.isBoardComplete());
		assertFalse(bthree.isBoardComplete());
		
	}
	
	/**
	 * this will test a of all empty numbers
	 */
	@Test
	void testBoardBadInput4() {
		Board EmptyBoard = new Board(9);
		assertFalse(EmptyBoard.isBoardComplete());
		assertFalse(EmptyBoard.isBoardComplete());
	}

	/**
	 * this will test the test that the size of the board can be correctly retrived
	 */
	@Test
	void testBoardGetSize() {
		// make an small sized board of a three row and colum
		Board sizeOne = new Board(3);
		// makes a medium sized board of six rows and colums
		Board sizeTwo = new Board(6);
		// make a large sized board board of nine rows and colums
		Board sizeThree = new Board(9);
		assertEquals(3, sizeOne.getSize());
		assertEquals(6, sizeTwo.getSize());
		assertEquals(9, sizeThree.getSize());
	}

	/**
	 * this will test the second constructor in the board class to see if it works
	 * to and if can make boards based off of the difficulty and show them to the
	 * user it will test it on a 6x6 board
	 */
	@Test
	void testBoardConstructor2WithDifferentSize() {
		System.out.println("this will test the second constructor on size 6");
		// make an easy board
		Board easyBoard = new Board(6, 1);
		// makes a medium board
		Board mediumBoard = new Board(6, 2);
		// make a hard board
		Board hardBoard = new Board(6, 3);
		easyBoard.printBoard();
		System.out.println();
		mediumBoard.printBoard();
		System.out.println();
		hardBoard.printBoard();
		System.out.println();
	}
	



	/**
	 * this will test the second constructor in the board class to see if it works
	 * to and if can make boards based off of the difficulty and show them to the
	 * user and that the board contains no errors then it will add and error and 
	 * print it out to the user 
	 */
	@Test
	void testFindErrorsOnSizeSix() {
		System.out.print("this will test the find errors for errors in a row\n");
		Board hardBoard = new Board(6, 3);
		ArrayList<Integer> curBoard = hardBoard.findErrorsInBoard();
		assertEquals(0, curBoard.size());
		hardBoard.updateBoardVal(0, 0, 1);
		hardBoard.updateBoardVal(0, 1, 1);
		hardBoard.printBoard();
		System.out.println();
		curBoard = hardBoard.findErrorsInBoard();
		for(int i = 0; i < curBoard.size();i += 2) {
			System.out.println("there is an error at coords ("+curBoard.get(i)+","+curBoard.get(i+1)+")");
		}
		System.out.println();
		
	}
	
	/**
	 * this will test the second constructor in the board class to see if it works
	 * to and if can make boards based off of the difficulty and show them to the
	 * user and that the board contains no errors then it will add and error and 
	 * print it out to the user 
	 */
	@Test
	void testFindErrorsOnSizeFour() {
		System.out.print("this will test the find errors for errors in a row\n");
		Board hardBoard = new Board(4, 3);
		ArrayList<Integer> curBoard = hardBoard.findErrorsInBoard();
		assertEquals(0, curBoard.size());
		hardBoard.updateBoardVal(0, 0, 1);
		hardBoard.updateBoardVal(0, 1, 1);
		hardBoard.printBoard();
		System.out.println();
		curBoard = hardBoard.findErrorsInBoard();
		for(int i = 0; i < curBoard.size();i += 2) {
			System.out.println("there is an error at coords ("+curBoard.get(i)+","+curBoard.get(i+1)+")");
		}
		System.out.println();
		
	}
	
	/**
	 * this will test the second constructor in the board class to see if it works
	 * to and if can make boards based off of the difficulty and show them to the
	 * user and that the board contains no errors then it will add and error and 
	 * print it out to the user 
	 */
	@Test
	void testFindErrorsOnSizeNine() {
		System.out.print("this will test the find errors for errors in a row\n");
		Board hardBoard = new Board(9, 3);
		ArrayList<Integer> curBoard = hardBoard.findErrorsInBoard();
		assertEquals(0, curBoard.size());
		hardBoard.updateBoardVal(0, 0, 1);
		hardBoard.updateBoardVal(0, 1, 1);
		hardBoard.printBoard();
		System.out.println();
		curBoard = hardBoard.findErrorsInBoard();
		for(int i = 0; i < curBoard.size();i += 2) {
			System.out.println("there is an error at coords ("+curBoard.get(i)+","+curBoard.get(i+1)+")");
		}
		System.out.println();
		
	}
	
	/**
	 * this test will test all the functionality of a pencilmark
	 * to see if a board can mark moves that are not final and if
	 * a board has a pencilmark then it is not consider solved 
	 */
	@Test
	void testWaterMarkedBoard() {
		ArrayList<ArrayList<Integer>> game = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row1 = new ArrayList<Integer>();
		ArrayList<Integer> row2 = new ArrayList<Integer>();
		ArrayList<Integer> row3 = new ArrayList<Integer>();
		// add three value to first row
		row1.add(1);
		row1.add(2);
		row1.add(3);
		// add three value to the second row
		row2.add(4);
		row2.add(5);
		row2.add(6);
		// add three value to third row
		row3.add(7);
		row3.add(8);
		row3.add(9);

		game.add(row1);
		game.add(row2);
		game.add(row3);
		System.out.println("this test the first constructor\n");
		Board b = new Board(game);
		b.printBoard();
		assertFalse(b.isCellPencilmark(0, 0));
		b.togglePencilmark(0, 0);
		assertTrue(b.isCellPencilmark(0, 0));
		assertFalse(b.isBoardComplete());
		b.togglePencilmark(0, 0);
		assertFalse(b.isCellPencilmark(0, 0));
		b.getBoard();
	}
	

}