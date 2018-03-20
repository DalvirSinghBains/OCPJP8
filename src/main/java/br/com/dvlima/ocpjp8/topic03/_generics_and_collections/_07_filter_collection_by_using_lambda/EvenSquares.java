package br.com.dvlima.ocpjp8.topic03._generics_and_collections._07_filter_collection_by_using_lambda;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class EvenSquares {
	public static void main(String[] args) {
		IntStream.rangeClosed(0, 10) //
				.map(i -> i * i)//
				.filter(i -> (i % 2) == 0)//
				.forEach(System.out::println);// 0 4 16 36 64 100

		/**
		 * This output is the same. This simple example shows how you can sometimes
		 * change the order of intermediate operations without changing the behavior.
		 */
		IntStream.rangeClosed(0, 10)//
				.filter(i -> (i % 2) == 0)//
				.map(i -> i * i) // call map AFTER calling filter
				.forEach(System.out::println);

		/** Important Terminal Operations in the Stream Interface */
		Object[] words = Pattern.compile(" ").splitAsStream("1 2 3 4 5").toArray();
		System.out.println(Arrays.stream(words).mapToInt(str -> Integer.valueOf((String) str)).sum());
	}
}
