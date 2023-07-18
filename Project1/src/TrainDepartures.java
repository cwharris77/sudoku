/* Cooper Harris 
 * 
 * This program takes user input using a scanner in the form of two ints
 * representing times on a 24 hour clock. It then prints the difference
 * between the two times in hours and minutes. 
 */

import java.util.Scanner;

public class TrainDepartures {

	public static void main(String[] args) {
		Scanner minutesInput = new Scanner(System.in);

		System.out.print("Train A departs at: ");
		int trainOne = minutesInput.nextInt();

		System.out.print("Train B departs at: ");
		int trainTwo = minutesInput.nextInt();

		minutesInput.close();

		System.out.println();

		// convert the hours given to minutes
		int trainOneHours = trainOne / 100 * 60;
		int trainTwoHours = trainTwo / 100 * 60;

		// extract minutes from the time given
		int trainOneMins = trainOne % 100;
		int trainTwoMins = trainTwo % 100;

		// diff = the largest difference between the two times
		int diff = Math.abs(trainOneHours + trainOneMins - (trainTwoHours + trainTwoMins));

		System.out.println("Difference: " + diff / 60 + " hours and " + diff % 60 + " minutes");
	}
}
