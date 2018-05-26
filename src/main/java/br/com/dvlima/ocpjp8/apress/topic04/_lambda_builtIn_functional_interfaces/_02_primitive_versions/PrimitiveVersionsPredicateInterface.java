package br.com.dvlima.ocpjp8.apress.topic04._lambda_builtIn_functional_interfaces._02_primitive_versions;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.stream.IntStream;

public class PrimitiveVersionsPredicateInterface {

	public static void main(String[] args) {
		IntStream.range(1, 10)//
				.filter(i -> (i % 2) == 0) //
				.forEach(System.out::println);// 2 4 6 8

		/**
		 * Evaluates the condition passed as int and returns a boolean value as result
		 * boolean test(int value)
		 */
		IntPredicate evenNums = i -> (i % 2) == 0;
		IntStream.range(1, 10).filter(evenNums).forEach(System.out::println);// 2 4 6 8

		/**
		 * Evaluates the condition passed as long and returns a boolean value as result
		 * boolean test(int value);
		 */
		LongPredicate evenNumsLong = i -> (i % 2) == 0;

		/**
		 * Evaluates the condition passed as double and returns a boolean value as
		 * result boolean test(int value)
		 */
		DoublePredicate evenNumsDouble = i -> (i % 2) == 0;

	}

}
