import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Grader tests for BoggleTray. Just tests word searches. We are not seeking
 * words in a dictionary, just strings for now.
 *
 * @author mercer, michaels, cooper harris
 * @version 1.2
 */

public class DiceTrayTest {

	private char[][] longWords = 
		{{ 'A', 'B', 'S', 'E' }, 
		 { 'I', 'M', 'T', 'N' }, 
		 { 'N', 'D', 'E', 'D' },
		 { 'S', 'S', 'E', 'N' } };

	private DiceTray tray = new DiceTray(longWords);
	
	private char[][] longWords2 = 
		{{ 'Q', 'E', 'N', 'C' }, 
		 { 'I', 'L', 'T', 'H' }, 
		 { 'C', 'I', 'O', 'K' },
		 { 'K', 'M', 'M', 'N' } };

	private DiceTray tray2 = new DiceTray(longWords2);

	@Test
	public void testForSmallStringsNotRealWords() {
		// We are not looking for words in a dictionary now, just strings.
		//
		// searchBoard must return false for strings < length() of 3
		// asserts can take a string argument that prints when the assert fails.
		//
		assertFalse(tray.found(""));
		assertFalse(tray.found("TS"));
		assertFalse(tray.found("Quarry"));
		assertFalse(tray.found("Quarqury"));

		assertTrue(tray.found("TMI"));
		assertTrue(tray.found("aBs")); // Case insensitive
		assertTrue(tray.found("AbS"));

	}

	@Test
	public void testForBiggerStrings() {
		assertTrue(tray.found("sent"));
		assertTrue(tray.found("SENT"));
		assertTrue(tray.found("minded"));
		assertTrue(tray.found("teen"));
		assertTrue(tray.found("dibtd"));
	}

	@Test
	public void testForLongStrings() {
		assertTrue(tray.found("NTMINDED"));
		assertTrue(tray.found("ABSENTMINDEDNESS"));
	}
	
	@Test
	public void testQ() {
		assertTrue(tray2.found("quick"));
		assertTrue(tray2.found("Quick"));
		assertTrue(tray2.found("QUE"));
		assertTrue(tray2.found("quE"));
		assertTrue(tray2.found("quilt"));
		assertTrue(tray2.found("quEnch"));
		assertTrue(tray2.found("MOM"));
		
	}
	
	@Test
	public void testRandomDiceTray() {
		DiceTray d = new DiceTray();
		System.out.println(d.toString());
	}

}


