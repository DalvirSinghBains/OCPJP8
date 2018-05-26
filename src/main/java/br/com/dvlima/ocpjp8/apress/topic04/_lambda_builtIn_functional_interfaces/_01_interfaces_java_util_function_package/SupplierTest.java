package br.com.dvlima.ocpjp8.apress.topic04._lambda_builtIn_functional_interfaces._01_interfaces_java_util_function_package;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A Supplier<T> supplies takes nothing but returns something: it has no
 * arguments and returns an object (of generic type T). You can call get()
 * method on a Supplier object.
 */
public class SupplierTest {

	public static void main(String[] args) {
		Random random = new Random();
		// static <T> Stream<T> generate(Supplier<T> s)
		Stream.generate(random::nextBoolean)//
				.limit(2)//
				.forEach(System.out::println);

		Supplier<String> currentDateTime = () -> LocalDateTime.now().toString();
		System.out.println(currentDateTime.get());
		// 2018-03-20T15:51:59.978

		// prints an empty string (nothing) to the console and then a newline character
		Supplier<String> newString1 = String::new;
		System.out.println(newString1.get());

		// This code makes use of constructor references. This code is equivalent to:
		Supplier<String> newString = () -> new String();
		System.out.println(newString.get());
	}

}
