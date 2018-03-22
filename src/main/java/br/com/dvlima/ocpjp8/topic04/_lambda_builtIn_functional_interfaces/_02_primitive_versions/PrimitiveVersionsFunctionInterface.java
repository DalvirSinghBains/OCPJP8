package br.com.dvlima.ocpjp8.topic04._lambda_builtIn_functional_interfaces._02_primitive_versions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class PrimitiveVersionsFunctionInterface {

	public static void main(String[] args) {
		AtomicInteger ints = new AtomicInteger(0);
		IntStream.generate(ints::incrementAndGet)//
				.limit(10)//
				.forEach(System.out::println);
		// prints integers from 1 to 10 on the console

		/**
		 * R apply(int value)
		 */
		Function<Integer, Integer> absInt = Math::abs;
		IntFunction absInt2 = Math::abs;
	}

}
