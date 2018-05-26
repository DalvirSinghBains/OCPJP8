package br.com.dvlima.ocpjp8.apress.topic04._lambda_builtIn_functional_interfaces._03_binary_versions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BiConsumerInterfaceTest {

	public static void main(String[] args) {
		/**
		 * This code segment shows how to use BiConsumer. Similar to using
		 * List::contains method reference in the previous example for BiPredicate, this
		 * example shows how to use BiConsumer to call add() method in List using this
		 * interface.
		 */
		BiConsumer<List<Integer>, Integer> listAddElement = List::add;
		List aList = new ArrayList();
		listAddElement.accept(aList, 10);
		System.out.println(aList);
		// prints: [10]

	}

}
