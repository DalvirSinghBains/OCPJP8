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
	 * remove elements in the stream that dont match the given condition (i.e.,
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

	/**
	 * Use the built-in interfaces included in the java.util.function package such
	 * as Predicate, Consumer, Function, and Supplier
	 */
	//  Built-in functional interfaces Predicate, Consumer, Function, and Supplier
	// differ mainly based on the signature of the abstract method they declare.

	//  A Predicate tests the given condition and returns true or false; hence it
	// has an abstract method named test that takes a parameter of generic type T
	// and returns boolean type.

	//  A Consumer consumes an object and returns nothing; hence it has an
	// abstract method named accept that takes an argument of generic type T and
	// has return type void.

	//  A Function operates on the argument and returns a result; hence it has an
	// abstract method named apply that takes an argument of generic type T and
	// has generic return type R.

	//  A Supplier supplies takes nothing but returns something; hence it has an
	// abstract method named get that takes no arguments and returns a generic
	// type T.

	//  The forEach() method defined in Iterable (implemented by collectionclasses)
	// method accepts a Consumer<T>.

	/** Develop code that uses primitive versions of functional interfaces */

	//  The built-in interfaces Predicate, Consumer, Function, and Supplier operate
	// on reference type objects. For primitive types, there are specializations
	// available for int, long, and double types for these functional interfaces.

	//  When the Stream interface is used with primitive types, it results in
	// unnecessary boxing and unboxing of the primitive types to their wrapper
	// types. This results in slower code as well as wastes memory because the
	// unnecessary wrapper objects get created. Hence, whenever possible, prefer
	// using the primitive type specializations of the functional interfaces
	// Predicate, Consumer, Function, and Supplier.

	//  The primitive versions of the functional interfaces Predicate, Consumer,
	// Function, and Supplier are available only for int, long and double types (and
	// boolean type in addition to these three types for Supplier). You have to use
	// implicit conversions to relevant int version when you need to use char, byte,
	// or short types; similarly, you can use the version for double type when you
	// need to use float.

	/** Develop code that uses binary versions of functional interfaces */
	//  The functional interfaces BiPredicate, BiConsumer, and BiFunction are
	// binary versions of Predicate, Consumer, and Function respectively. There is
	// no binary equivalent for Supplier since it does not take any arguments. The
	// prefix Bi indicates the version that takes two arguments.

	/** Develop code that uses the UnaryOperator interface */
	//  UnaryOperator is a functional interface and it extends Function interface.

	//  The primitive type specializations of UnaryOperator are IntUnaryOperator,
	// LongUnaryOperator, and DoubleUnaryOperator for int, long, and double types
	// respectively.
}
