// Cooper Harris
package model;

public class TemperatureConverter {

	public static double CtoF(double cTemp) {
		double temp = (1.8 * cTemp) + 32;
		return Math.round(temp*Math.pow(10,1))/Math.pow(10,1);
	}

	public static double FtoC(double fTemp) {
		double temp = ((fTemp - 32) * 5) / 9;
		return Math.round(temp*Math.pow(10,1))/Math.pow(10,1);
	}

}
