package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque;

public class CollectionsClasses {

	/** Iterable */
	/*
	 * A class implementing this interface can be used for iterating with a foreach
	 * statement.
	 */

	/** Collection */
	/*
	 * Common base interface for classes in the collection hierarchy. When you want
	 * to write methods that are very general, you can pass the Collection
	 * interface. For example, max() method in java.util.Collections takes a
	 * Collection and returns an object.
	 */

	/** List */
	/*
	 * Base interface for containers that store a sequence of elements. You can
	 * access the elements using an index, and retrieve the same element later (so
	 * that it maintains the insertion order). You can store duplicate elements in a
	 * List.
	 */

	/** Set, SortedSet, NavigableSet */
	/*
	 * Interfaces for containers that don’t allow duplicate elements. SortedSet
	 * maintains the set elements in a sorted order. NavigableSet allows searching
	 * the set for the closest matches.
	 */

	/** Queue, Deque */
	/*
	 * Queue is a base interface for containers that holds a sequence of elements
	 * for processing. For example, the classes implementing Queue can be LIFO (last
	 * in, first out—as in stack data structure) or FIFO (first in, first out—as in
	 * queue data structure). In a Deque you can insert or remove elements from both
	 * the ends.
	 */

	/** Map, SortedMap, NavigableMap */
	/*
	 * Interfaces for containers that map keys to values. In SortedMap, the keys are
	 * in a sorted order. A NavigableMap allows you to search and return the closest
	 * match for given search criteria. Note that Map hierarchy does not extend the
	 * Collection interface.
	 */

	/** Iterator, ListIterator */
	/*
	 * You can traverse over the container in the forward direction if a class
	 * implements the Iterator interface. You can traverse in both forward and
	 * reverse directions if a class implements the ListIterator interface.
	 */
}
