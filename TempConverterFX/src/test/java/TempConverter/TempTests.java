// Cooper Harris

package TempConverter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.TemperatureConverter;

class TempTests {

	@Test
	void test() {
		assertEquals(100.0, TemperatureConverter.FtoC(212.0), 0.01);
		assertEquals(32.0, TemperatureConverter.CtoF(0.0), 0.01);

		assertEquals(37.0, TemperatureConverter.FtoC(98.6), 0.01);
		assertEquals(209.5, TemperatureConverter.CtoF(98.6), 0.01);

		assertEquals(482.4, TemperatureConverter.FtoC(900.3), 0.01);
		assertEquals(92.5, TemperatureConverter.CtoF(33.6), 0.01);

		assertEquals(-17.2, TemperatureConverter.FtoC(1.0), 0.01);
		assertEquals(33.8, TemperatureConverter.CtoF(1.0), 0.01);

	}

}
