package br.com.dvlima.ocpjp8.topic04._lambda_builtIn_functional_interfaces;

/**
 * Lambda Built-in Functional Interfaces
 */
// - Use the built-in interfaces included in the java.util.function package such
// as Predicate, Consumer, Function, and Supplier
// - Develop code that uses primitive versions of functional interfaces
// - Develop code that uses binary versions of functional interfaces
// - Develop code that uses the UnaryOperator interface

public class LambdaBuiltInFunctionalInterfaces {
	/** Predicate<T> */
	/** boolean test(T t) */
	/** Checks a condition and returns a boolean value as result */
	/*
	 * Common Use: In filter() method in java.util.stream.Stream which is used to
	 * remove elements in the stream that don’t match the given condition (i.e.,
	 * predicate) as argument.
	 */

	/** Consumer<T> */
	/** void accept(T t) */
	/** Operation that takes an argument but returns nothing */
	/*
	 * Common Use:In forEach() method in collections and in java.util.stream.Stream;
	 * this method is used for traversing all the elements in the collection or
	 * stream.
	 */

	/** Function<T, R> */
	/** R apply(T t) */
	/** Functions that take an argument and return a result */
	/*
	 * Common Use:In map() method in java.util.stream.Stream to transform or operate
	 * on the passed value and return a result.
	 */

	/** Supplier<T> */
	/** T get() */
	/** Operation that returns a value to the caller */
	/*
	 * Common Use:In generate() method in java.util.stream.Stream to create an
	 * infinite stream of elements.
	 */
}
