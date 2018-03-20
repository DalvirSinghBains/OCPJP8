package br.com.dvlima.ocpjp8.topic03._generics_and_collections._02_arraylist_treeset_treemap_arraydeque;

import java.util.Arrays;
import java.util.List;

public class ArrayAsList {
	public static void main(String[] args) {
		Double[] temperatureArray = { 31.1, 30.0, 32.5, 34.9, 33.7, 27.8 };

		System.out.println("The original array is: " + Arrays.toString(temperatureArray));
		System.out.println(temperatureArray);

		List<Double> temperatureList = Arrays.asList(temperatureArray);
		temperatureList.set(0, 35.2);

		System.out.println("The modified array is: " + Arrays.toString(temperatureArray));

		// The original array is: [31.1, 30.0, 32.5, 34.9, 33.7, 27.8]
		// [Ljava.lang.Double;@33909752
		// The modified array is: [35.2, 30.0, 32.5, 34.9, 33.7, 27.8]
	}
}
