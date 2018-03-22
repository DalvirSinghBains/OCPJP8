package br.com.dvlima.ocpjp8.topic04._lambda_builtIn_functional_interfaces._01_interfaces_java_util_function_package;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A Predicate<T> affirms something as true or false: it takes an argument of
 * type T, and returns a boolean value. You can call test() method on a
 * Predicate object.
 * 
 */
public class PredicateTest {
	public static void main(String[] args) {

		// the filter() method takes a Predicate as an argument
		Stream.of("hello", "world")//
				.filter(str -> str.startsWith("h"))//
				.forEach(System.out::println);

		Predicate<String> nullCheck = arg -> arg != null;
		Predicate<String> emptyCheck = arg -> arg.length() > 0;
		Predicate<String> nullAndEmptyCheck = nullCheck.and(emptyCheck);

		String helloStr = "hello";
		System.out.println(nullAndEmptyCheck.test(helloStr));

		String nullStr = null;
		System.out.println(nullAndEmptyCheck.test(nullStr));

		/***/
		List<String> greeting = new ArrayList<>();
		greeting.add("hello");
		greeting.add("world");

		// default boolean removeIf(Predicate<? super E> filter)
		greeting.removeIf(str -> !str.startsWith("h"));
		greeting.removeIf(((Predicate<String>) str -> str.startsWith("h")).negate());
		greeting.forEach(System.out::println);
	}
}
