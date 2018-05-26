package br.com.dvlima.ocpjp8.apress.topic04._lambda_builtIn_functional_interfaces._03_binary_versions;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class BiPredicateInterfaceTest {

	public static void main(String[] args) {
		/**
		 * This code shows how to use BiPredicate. The contains() method in List takes
		 * an element as an argument and checks if the underlying list contains the
		 * element. Because it takes an argument and returns an Integer, we can use a
		 * BiPredicate. Why not use BiFunction<T, U, Boolean>? Yes, the code will work,
		 * but a better choice is the equivalent BiPredicate<T, U> because the
		 * BiPredicate returns a boolean value.
		 */
		BiPredicate<List<Integer>, Integer> listContains = List::contains;
		List aList = Arrays.asList(10, 20, 30);
		System.out.println(listContains.test(aList, 20));
		// prints: true
	}

}
