//package tests;
//
///**
// * this will test different aspect of the game
// * class
// * 
// * Authors: Bryan Bielawa, Carissa Burnham, Cooper Harris, Drew Marien
// *
// *
// */
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.Test;
//
//import model.Game;
//
//class TestGame {
//
//	/**
//	 * this test the first constructor of the game class
//	 */
//	@Test
//	void testConstructorOne() {
//		Game g = new Game(6, 3);
//		g.printState();
//		Game gTwo = new Game(3, 2);
//		gTwo.printState();
//		Game gThree = new Game(9, 1);
//		gThree.printState();
//	}
//
//	/**
//	 * this test the second constructor
//	 */
//	@Test
//	void testConstructorTwo() {
//		Game g = new Game(6);
//	}
//
//	/**
//	 * this test that a new game can be made that is different then the current game
//	 */
//	@Test
//	void testNewGame() {
//		System.out.println();
//		System.out.println();
//		Game g = new Game(9, 3);
//		g.printState();
//		g.newGame(6, 3);
//		g.printState();
//
//	}
//
//	/**
//	 * this check that the game size can be return correctly
//	 */
//	@Test
//	void testGetSize() {
//		System.out.println();
//		Game g = new Game(9, 3);
//		assertEquals(9, g.getSize());
//
//	}
//
//	/**
//	 * this test that a value of a cell can be retreived correctly
//	 */
//	@Test
//	void testGetCell() {
//		System.out.println();
//		Game g = new Game(9, 1);
//		g.makeMove(0, 0, 2, false);
//		assertEquals(2, g.getCellValue(0, 0));
//
//	}
//
//	/**
//	 * this checks that the media player workes
//	 */
//	@Test
//	void testPlayNewGameSound() {
//		System.out.println();
//		Game g = new Game(9, 1);
//		g.playNewGameSound();
//
//	}
//
//	/**
//	 * this will test that a move can be made and that it value is correctly stored
//	 * in the cell
//	 */
//	@Test
//	void testMakeMove() {
//		System.out.println();
//		Game g = new Game(9, 1);
//		g.makeMove(0, 0, 1, false);
//		assertEquals(1, g.getCellValue(0, 0));
//		g.makeMove(0, 0, 5, true);
//		assertEquals(5, g.getCellValue(0, 0));
//
//	}
//
//	/**
//	 * this will test to make sure that a water mark can be toggled on a cell
//	 * correctly
//	 */
//	@Test
//	void testTogglePencilMark() {
//		System.out.println();
//		Game g = new Game(9, 1);
//		g.makeMove(0, 0, 1, false);
//		assertEquals(1, g.getCellValue(0, 0));
//		g.togglePencilMark(0, 0);
//		assertTrue(g.isCellPencilMark(0, 0));
//
//	}
//
//	/**
//	 * this will check that a game that is done correctly can be marked as
//	 * compleated
//	 */
//	@Test
//	void testIsWon() {
//		System.out.println();
//		Game g = new Game(4, 1);
//		assertFalse(g.isWon());
//		g.makeMove(0, 0, 2, false);
//		g.makeMove(0, 1, 3, false);
//		g.makeMove(0, 2, 1, false);
//		g.makeMove(0, 3, 4, false);
//
//		g.makeMove(1, 0, 4, false);
//		g.makeMove(1, 1, 1, false);
//		g.makeMove(1, 2, 3, false);
//		g.makeMove(1, 3, 2, false);
//
//		g.makeMove(2, 0, 3, false);
//		g.makeMove(2, 1, 4, false);
//		g.makeMove(2, 2, 2, false);
//		g.makeMove(2, 3, 1, false);
//
//		g.makeMove(3, 0, 1, false);
//		g.makeMove(3, 1, 2, false);
//		g.makeMove(3, 2, 4, false);
//		g.makeMove(3, 3, 3, false);
//
//		assertTrue(g.isWon());
//
//	}
//
//	/**
//	 * this will test that an error can be found in the board
//	 */
//	@Test
//	void testGetErrors() {
//		System.out.println();
//		Game g = new Game(4, 1);
//		g.makeMove(0, 0, 2, false);
//		g.makeMove(0, 1, 3, false);
//		g.makeMove(0, 2, 1, false);
//		g.makeMove(0, 3, 4, false);
//
//		g.makeMove(1, 0, 4, false);
//		g.makeMove(1, 1, 1, false);
//		g.makeMove(1, 2, 3, false);
//		g.makeMove(1, 3, 2, false);
//
//		g.makeMove(2, 0, 3, false);
//		g.makeMove(2, 1, 4, false);
//		g.makeMove(2, 2, 2, false);
//		g.makeMove(2, 3, 1, false);
//
//		g.makeMove(3, 0, 1, false);
//		g.makeMove(3, 1, 2, false);
//		g.makeMove(3, 2, 4, false);
//		g.makeMove(3, 3, 3, false);
//
//		ArrayList<Integer> curArry = g.getErrorsArray();
//		assertTrue(curArry.isEmpty());
//		g.makeMove(2, 1, 3, false);
//		ArrayList<Integer> curArryTwo = g.getErrorsArray();
//		assertTrue(curArry.isEmpty());
//		g.makeMove(2, 1, 3, false);
//		assertFalse(curArryTwo.isEmpty());
//	}
//	
//	/**
//	 * this will test that an error can be found in the board
//	 * and return as a string
//	 */
//	@Test
//	void testGetErrorsStr() {
//		System.out.println();
//		Game g = new Game(4, 1);
//		g.makeMove(0, 0, 2, false);
//		g.makeMove(0, 1, 3, false);
//		g.makeMove(0, 2, 1, false);
//		g.makeMove(0, 3, 4, false);
//
//		g.makeMove(1, 0, 4, false);
//		g.makeMove(1, 1, 1, false);
//		g.makeMove(1, 2, 3, false);
//		g.makeMove(1, 3, 2, false);
//
//		g.makeMove(2, 0, 3, false);
//		g.makeMove(2, 1, 4, false);
//		g.makeMove(2, 2, 2, false);
//		g.makeMove(2, 3, 1, false);
//
//		g.makeMove(3, 0, 1, false);
//		g.makeMove(3, 1, 2, false);
//		g.makeMove(3, 2, 4, false);
//		g.makeMove(3, 3, 3, false);
//		
//		ArrayList<Integer> curArry = g.getErrorsArray();
//		assertTrue(curArry.isEmpty());
//		g.makeMove(2, 1, 3, false);
//		g.getErrorsStr();
//		
//	}
//
//}
