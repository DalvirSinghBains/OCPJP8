package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque._01_arraylist;

import java.util.ArrayList;
import java.util.ListIterator;

public class ArrayListTest {

	/** List interface */
	// - Allows null
	// - duplicate values
	// - Retains the order of insertion of objects

	/** MODIFICATION METHODS */
	/*
	 * - Add elements add(E e) add(int index, E e) addAll(Collection<? extends E> c)
	 * addAll(int index, Collection<? extends E> c) - Remove elements clear()
	 * remove(int index) remove(Object) removeAll(Collection<?> c)
	 * retainAll(Collection<?> c) - Modify elements set(int index, E c)
	 */

	/** QUERY METHODS */
	/*
	 * contains(Object o) contaisAll(Collection<?> c) equals(Object o) get(int
	 * index) indexOf(Object o) isEmpty() lastIndexOf(Object o) subList(int from,
	 * int to)
	 */

	/** MISCELLANEOUS */
	/*
	 * hashCode() toArray() toArray(T[] a)
	 */

	/** ITERATOR METHODS */
	/*
	 * iterator() listIterator() listIterator()
	 */

	/* ============================================ */
	/* Set interface */
	// - Doesn't allow addition of duplicate objects

	/* ============================================ */
	/* Deque */
	// - Is a linear collection that suportes the insertion and removal of
	// elements at both its ends.

	public static void main(String[] args) {
		//Creates a ArrayList with default initial capacity of 10.
		ArrayList<String> list = new ArrayList<>();
		
		//Adds String objects, duplicate value allowed
		list.add("Harry");
		list.add("Selvan");
		list.add("Harry");
		
		//Adds String objects Paul at first position, shifting 
		//existing list element to right
		list.add(0, "Paul");
		
		//Uses equals too find and remove first ocurrence of value mathing Harry
		list.remove("Harry");
		
		//Replaces value at position 0
		//Retrieving replaced value
		String oldValue = list.set(0, "Shreya");
		
		//
		list.get(7);
		
		//
		System.out.println("list contains Harry : " + list.contains("Harry"));
		
		//
		ListIterator<String> iterator = list.listIterator();
		
		
		while (iterator.hasNext())
			System.out.println(iterator.next());
	}
}
