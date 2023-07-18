/**
 * @author Cooper Harris
 * 
 * This class created a TicTacToe game that will
 * be played in the console by entering a row and column
 * The AI will move automatically after the player enters
 * their move
 * 
 */

package views;

import java.util.Scanner;

import model.TicTacToeGame;

public class ConsoleTicTacToe {
	
	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		Scanner input = new Scanner(System.in);
		
		int row, col;
		
		while (game.stillRunning()) {
			System.out.print("Enter row and column: ");
			
			row = input.nextInt();
			col = input.nextInt();
			// if the numbers entered are out of range
			if ((row < 0 || row > 3) || (col < 0 || col > 3)) {
				System.out.println("\nNumbers should be be 0-2\n");
				continue;
			}
			// If the spot entered is available place the piece
			// there and print the state of the board
			if (game.available(row, col)) {
				game.humanMove(row, col, false);
				
				System.out.println(game.toString() + "\n");
			} else {
				System.out.println("Invalid choice");
			}
		}
		input.close();
		
		if (game.didWin('X')) {
			System.out.println(" X wins");
		} else if (game.didWin('O')) {
			System.out.println("O wins");
		} else {
			System.out.println("Tie");
		}
	}
}
