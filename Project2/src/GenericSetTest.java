/* Cooper Harris
 * This file contains a series of test cases for a generic
 * set.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GenericSetTest {

	@Test
	void testAddAndSize() {
		GenericSet<String> set = new GenericSet<>();

		assertTrue(set.add("max"));
		assertEquals(1, set.size());
	}

	@Test
	void testAddAndSize2() {
		GenericSet<Double> set = new GenericSet<>();

		assertTrue(set.add(67.8));
		assertTrue(set.add(90.99));
		assertTrue(set.add(45.0));
		assertTrue(set.add(13.2345));
		assertTrue(set.add(123.4));
		assertTrue(set.add(78.009));
		assertTrue(set.add(7878.1));

		assertEquals(7, set.size());
	}

	@Test
	void testAddDuplicate() {
		GenericSet<Integer> set = new GenericSet<>();

		assertTrue(set.add(15));
		set.add(13);
		set.add(16);
		set.add(8);
		assertFalse(set.add(15));

		assertEquals(4, set.size());
	}

	@Test
	void testAddDuplicate2() {
		GenericSet<String> set = new GenericSet<>();

		assertTrue(set.add("t"));
		set.add("67");
		set.add(")thanks");
		set.add("ma");
		assertFalse(set.add("t"));

		assertEquals(4, set.size());
	}

	@Test
	void testContains() {
		GenericSet<Integer> set = new GenericSet<>();

		set.add(1);
		set.add(12);
		set.add(9);
		set.add(100);
		set.add(7);
		set.add(4);

		assertTrue(set.contains(12));
		assertTrue(set.contains(100));
		assertTrue(set.contains(4));
		assertTrue(set.contains(9));

		assertFalse(set.contains(45));
		assertFalse(set.contains(90));
		assertFalse(set.contains(-45));
		assertFalse(set.contains(6777));
	}

	@Test
	void testUnion() {
		GenericSet<Integer> set = new GenericSet<>();
		GenericSet<Integer> set2 = new GenericSet<>();
		GenericSet<Integer> union = new GenericSet<>();

		set.add(1);
		set.add(12);
		set.add(9);
		set.add(100);
		set.add(7);
		set.add(4);
		set.add(67);

		set2.add(67);
		set2.add(12);
		set2.add(90);
		set2.add(10);
		set2.add(7);
		set2.add(4);
		set2.add(3);
		set2.add(75);

		union.add(12);
		union.add(7);
		union.add(4);
		union.add(67);

		assertTrue(set.union(set2).equals(union));

	}

	@Test
	void testUnion2() {
		GenericSet<Double> set = new GenericSet<>();
		GenericSet<Double> set2 = new GenericSet<>();
		GenericSet<Double> union = new GenericSet<>();

		set.add(1.5);
		set.add(12.1);
		set.add(9.90);
		set.add(100.0);
		set.add(7.34);
		set.add(4.0);
		set.add(67.0);

		set2.add(67.0);
		set2.add(12.1);
		set2.add(90.1);
		set2.add(10.23);
		set2.add(7.34);
		set2.add(4.0);
		set2.add(3.9);
		set2.add(75.345);

		union.add(12.1);
		union.add(7.34);
		union.add(4.0);
		union.add(67.0);

		assertTrue(set.union(set2).equals(union));

	}

	@Test
	void testIntersection() {
		GenericSet<Integer> set = new GenericSet<>();
		GenericSet<Integer> set2 = new GenericSet<>();
		GenericSet<Integer> intersection = new GenericSet<>();

		set.add(1);
		set.add(12);
		set.add(9);
		set.add(100);
		set.add(7);
		set.add(4);
		set.add(67);

		set2.add(67);
		set2.add(12);
		set2.add(90);
		set2.add(10);
		set2.add(7);
		set2.add(4);
		set2.add(3);

		intersection.add(1);
		intersection.add(9);
		intersection.add(100);
		intersection.add(90);
		intersection.add(10);
		intersection.add(3);

		assertTrue(set.intersection(set2).equals(intersection));

	}

	@Test
	void testIntersection2() {
		GenericSet<String> set = new GenericSet<>();
		GenericSet<String> set2 = new GenericSet<>();
		GenericSet<String> intersection = new GenericSet<>();

		set.add("67");
		set.add("12");
		set.add("t");
		set.add("Y");
		set.add("7");
		set.add("mac");
		set.add("3");

		set2.add("67");
		set2.add("12");
		set2.add("cat");
		set2.add("dog");
		set2.add("tree");
		set2.add("mac");
		set2.add("3");

		intersection.add("t");
		intersection.add("Y");
		intersection.add("7");
		intersection.add("cat");
		intersection.add("dog");
		intersection.add("tree");

		assertTrue(set.intersection(set2).equals(intersection));

	}

	@Test
	void testToString() {
		GenericSet<Integer> set = new GenericSet<>();

		set.add(1);
		set.add(2);
		set.add(3);

		assertEquals("[1, 2, 3]", set.toString());
	}
}
