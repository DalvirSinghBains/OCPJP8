package br.com.dvlima.ocpjp8.topic04._lambda_builtIn_functional_interfaces._01_interfaces_java_util_function_package;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A Consumer<T> “consumes” something: it takes an argument (of generic type T)
 * and returns nothing (void). You can call accept() method on a Consumer
 * object.
 */
public class ConsumerTest {
	public static void main(String[] args) {
		Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
		printUpperCase.accept("hello");
		// prints: HELLO

		Stream<String> strings = Stream.of("hello", "world");
		Consumer<String> printString = System.out::println;
		strings.forEach(printString);
	}
}
