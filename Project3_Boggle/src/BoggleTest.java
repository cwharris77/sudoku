
/**
 * Cooper Harris
 */

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

class BoggleTest {
	private Hashtable<String, String> table;
	private char[][] longWords = { { 'M', 'A', 'R', 'K' }, { 'I', 'L', 'Z', 'E' }, { 'M', 'Z', 'Z', 'T' },
			{ 'B', 'Z', 'Z', 'S' } };

	@Test
	void testGetDictionary() throws FileNotFoundException {
		Hashtable<String, String> t = Boggle.getDictionary("BoggleWords.txt");

		assertEquals("abjurers", t.get("abjurers"));
	}

	@Test
	void testGetScore() {
		table = new Hashtable<>();
		table.put("mail", "mail");
		table.put("limb", "limb");
		table.put("lima", "lima");
		table.put("market", "market");
		table.put("markets", "markets");
		table.put("marke", "marke");
		table.put("markez", "markez");
		table.put("marketszz", "marketszz");

		ArrayList words = new ArrayList<>();
		ArrayList found = new ArrayList<>();
		ArrayList wrong = new ArrayList<>();
		ArrayList missed = new ArrayList<>();

		words.add("mail");
		words.add("mark");
		words.add("markets");
		words.add("market");
		words.add("marke");
		words.add("marke");
		words.add("marketszz");

		found.add("mail");
		found.add("markets");
		found.add("market");
		found.add("marke");
		found.add("marketszz");

		wrong.add("mark");

		missed.add("limb");
		missed.add("lima");
		missed.add("markez");
		DiceTray tray = new DiceTray(longWords);
		Hashtable<Integer, ArrayList<String>> scores = Boggle.getScore(words, table, tray);

		System.out.println(scores.get(1));

		assertEquals("22", scores.get(0).get(0));
		assertEquals(found, scores.get(1));
		assertEquals(wrong, scores.get(2));
		assertEquals(missed, scores.get(3));

	}

}
