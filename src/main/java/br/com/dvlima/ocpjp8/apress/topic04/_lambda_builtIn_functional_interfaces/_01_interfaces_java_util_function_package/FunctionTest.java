package br.com.dvlima.ocpjp8.apress.topic04._lambda_builtIn_functional_interfaces._01_interfaces_java_util_function_package;

import java.util.Arrays;
import java.util.function.Function;

/**
 * A Function<T, R> operates on something and returns something: it takes one
 * argument (of generic type T) and returns an object (of generic type R). You
 * can call apply() method on a Function object.
 */
public class FunctionTest {

	public static void main(String[] args) {
		Arrays.stream("4, -9, 16".split(", "))//
				.map(Integer::parseInt)//
				.map(i -> (i < 0) ? -i : i)//
				.forEach(System.out::println);

		Function<String, Integer> strLength = str -> str.length();
		System.out.println(strLength.apply("supercalifragilisticexpialidocious"));
		// prints: 34

		Function<String, Integer> parseInt = Integer::parseInt;
		Function<Integer, Integer> absInt = Math::abs;
		Function<String, Integer> parseAndAbsInt = parseInt.andThen(absInt);

		Arrays.stream("4, -9, 16".split(", "))//
				.map(parseAndAbsInt)//
				.forEach(System.out::println);

		/**
		 * The identity() function in Function just returns the passed argument without
		 * doing anything! Then what is its use? It is sometimes used for testing  when
		 * you write a piece of code that takes a Function and want to check if it
		 * works, you can call identity() because it doesnt do anything. Here is an
		 * example:
		 */
		Arrays.stream("4, -9, 16".split(", "))//
				.map(Function.identity())//
				.forEach(System.out::println);//
	}

}
