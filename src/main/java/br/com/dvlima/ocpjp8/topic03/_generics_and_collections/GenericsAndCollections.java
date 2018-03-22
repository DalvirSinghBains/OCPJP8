package br.com.dvlima.ocpjp8.topic03._generics_and_collections;

/**
 * Generics and Collections
 * 
 * Create and use a generic class - Create and use ArrayList, TreeSet,
 * TreeMap, and ArrayDeque objects - Use java.util.Comparator and
 * java.lang.Comparable interfaces - Collections Streams and Filters - Iterate
 * using forEach methods of Streams and List - Describe Stream interface and
 * Stream pipeline - Filter a collection by using lambda expressions - Use
 * method references with Streams
 */
public class GenericsAndCollections {
	/** Create and use a generic class */
	/*
	 *  Generics will ensure that any attempts to add elements of types other than
	 * the specified type(s) will be caught at compile time itself. Hence, generics
	 * offer generic implementation with type safety.
	 */
	/*
	 *  Java 7 introduced diamond syntax where the type parameters (after new
	 * operator and class name) can be omitted. The compiler will infer the types
	 * from the type declaration.
	 */
	/*
	 *  Generics are not covariant. That is, subtyping doesnt work with generics
	 * you cannot assign a derived generic type parameter to a base type parameter.
	 */
	/*
	 *  Avoid mixing raw types with generic types. In other cases, make sure of the
	 * type safety manually.
	 */
	/*
	 *  The <?> specifies an unknown type in generics and is known as a wildcard.
	 * For example, List<?> refers to a list of unknowns.
	 */

	/** Create and use ArrayList, TreeSet, TreeMap, and ArrayDeque objects */
	/*
	 *  The terms Collection, Collections, and collection are different. Collection
	 * java.util.Collection<E> is the root interface in the collection hierarchy.
	 * Collections java.util.Collections is a utility class that contains only
	 * static methods. The general term collection(s) refers to containers like map,
	 * stack, and queue.
	 */
	/*
	 *  Remember that you cannot add or remove elements to the List returned by the
	 * Arrays.asList() method. But, you can make changes to the elements in the
	 * returned List, and the changes made to that List are reflected back in the
	 * array.
	 */
	/*
	 *  A HashSet is for quickly inserting and retrieving elements; it does not
	 * maintain any sorting order for the elements it holds. A TreeSet stores the
	 * elements in a sorted order (and it implements the SortedSet interface).
	 */
	/*
	 *  A HashMap uses a hash table data structure internally. In HashMap,
	 * searching (or looking up elements) is a fast operation. However, HashMap
	 * neither remembers the order in which you inserted elements nor keeps elements
	 * in any sorted order. Unlike HashMap, TreeMap keeps the elements in sorted
	 * order (i.e., sorted by its keys). So, searching or inserting is somewhat
	 * slower than the HashMap.
	 */
	/*
	 *  Deque (Doubly ended queue) is a data structure that allows you to insert
	 * and remove elements from both ends. There are three concrete implementations
	 * of the Deque interface: LinkedList, ArrayDeque, and LinkedBlockingDeque.
	 */
	/*
	 *  The difference between an ArrayList and ArrayDeque is that you can add an
	 * element anywhere in an array list using an index; however, you can add an
	 * element only either at the front or end of the array deque.
	 */

	/** Use java.util.Comparator and java.lang.Comparable interfaces */
	/*
	 *  Implement the Comparable interface for your classes where a natural order
	 * is possible. If you want to compare the objects other than the natural order
	 * or if there is no natural ordering present for your class type, then create
	 * separate classes implementing the Comparator interface. Also, if you have
	 * multiple alternative ways to decide the order, then go for the Comparator
	 * interface.
	 */

	/** Collections Streams and Filters */
	/*
	 *  The new stream API is provided in the java.util.stream package introduced
	 * in Java 8. The main type in this package is Stream<T> interface, which is the
	 * stream of object references. IntStream, LongStream, and DoubleStream are
	 * streams for primitive types int, long, and double types respectively.
	 */
	/*
	 *  A stream is a sequence of elements. In Java 8, the Collection interface has
	 * been added with the methods stream() and parallelStream() from which you can
	 * respectively get sequential and parallel streams.
	 */

	/** Iterate using forEach methods of Streams and List */
	/*
	 *  In Java 8, we are moving from external iteration to internal iteration. It
	 * is a major change with Java 8 approach of functional programming.
	 */
	/*
	 *  The interfaces Stream and Iterable define forEach() method. The forEach()
	 * method supports internal iteration.
	 */

	/** Describe Stream interface and Stream pipeline */
	/*
	 *  Stream operations can be chained together to form a pipeline known as
	 * stream pipeline.
	 */
	/*
	 *  A stream pipeline has a beginning, middle, and an end: source (that creates
	 * a stream), intermediate operations (that consist of optional operations that
	 * can be chained together), and terminal operations (that produce a result).
	 */
	/*
	 *  The terminal operation can produce a result, accumulate the stream
	 * elements, or just perform an action.
	 */
	/*
	 *  You can use a stream only once. Any attempt at reusing the stream (for
	 * example, by calling intermediate or terminal operations) will result in
	 * throwing an IllegalStateException.
	 */

	/** Filter a collection by using lambda expressions */
	/*
	 *  The filter() method in the Stream interface is used for removing the
	 * elements that do not match the given condition.
	 */

	/** Use method references with Streams */
	/*
	 *  When lambda expressions just route the given parameters, you can use method
	 * references instead.
	 */
	/*
	 *  Since method references serve as a way to route the parameters, it is often
	 * convenient (as it results in more concise code) to use them than their
	 * equivalent lambda expressions.
	 */

}
