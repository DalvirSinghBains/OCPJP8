package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._07_filter_collection_by_using_lambda;

import java.util.stream.IntStream;

public class EvenNumbers {

	/**
	 * The filter() method in the Stream interface is used for removing the elements
	 * that do not match the given condition.
	 */
	public static void main(String[] args) {
		// IntStream.rangeClosed(startValue, endValueInclusiveOfEnd)
		IntStream.rangeClosed(0, 10)// inclusive 10!
				.filter(i -> (i % 2) == 0)//
				.forEach(System.out::println);// 0 2 4 6 8 10

		IntStream.range(0, 10)//
				.filter(EvenNumbers::isEven)//
				.forEach(System.out::println);// 0 2 4 6 8
	}

	public static boolean isEven(int i) {
		return (i % 2) == 0;
	}
}
