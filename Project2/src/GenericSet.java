/* Cooper Harris
 * This class creates a generic set
 * that supports several basic operations of a
 * set.
 */

import java.util.HashSet;

public class GenericSet<T> {
	private int size;
	private HashSet<T> set;

	public GenericSet() {
		this.size = 0;
		this.set = new HashSet<>();
	}

	/**
	 * Adds a value to the set if that value is not already contained in the set.
	 * 
	 * @param value item to be added
	 * @return true if the value was added
	 */
	public boolean add(T value) {
		if (!set.contains(value)) {
			set.add(value);
			size++;

			return true;
		}
		return false;
	}

	/**
	 * @return the number of items in the set
	 */
	public int size() {
		return size;
	}

	/**
	 * @param value item to search for
	 * @return true if the set contains the item
	 */
	public boolean contains(T value) {
		return set.contains(value);
	}

	/**
	 * Finds the union of two generic sets
	 * 
	 * @param set2 the second set to union with
	 * @return the union of the two sets
	 */
	public GenericSet<T> union(GenericSet<T> set2) {
		GenericSet<T> union = new GenericSet<>();

		for (T item : set) {
			if (set2.contains(item)) {
				union.add(item);
			}
		}

		return union;
	}

	/**
	 * Finds the intersection of two generic sets
	 * 
	 * @param set2 the set to intersect
	 * @return the intersection of the two sets
	 */
	public GenericSet<T> intersection(GenericSet<T> set2) {
		GenericSet<T> intersection = new GenericSet<>();

		for (T item : set) {
			if (!set2.contains(item)) {
				intersection.add(item);
			}
		}

		for (T item : set2.getHashSet()) {
			if (!set.contains(item)) {
				intersection.add(item);
			}
		}

		return intersection;
	}

	/**
	 * Returns true if two sets contain the same values
	 * 
	 * @param set2 the set to check
	 * @return true if the sets are equal
	 */
	public boolean equals(GenericSet<T> set2) {
		return set.equals(set2.getHashSet());
	}

	/**
	 * @return the underlying hash set of the set object
	 */
	public HashSet<T> getHashSet() {
		return this.set;
	}

	/**
	 * To string in the form [value, value]
	 */
	public String toString() {
		return set.toString();
	}

}
