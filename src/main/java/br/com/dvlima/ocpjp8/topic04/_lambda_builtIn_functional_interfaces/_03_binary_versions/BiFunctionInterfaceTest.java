package br.com.dvlima.ocpjp8.topic04._lambda_builtIn_functional_interfaces._03_binary_versions;

import java.util.function.BiFunction;

public class BiFunctionInterfaceTest {

	public static void main(String[] args) {
		BiFunction<String, String, String> concatStr = (x, y) -> x + y;
		System.out.println(concatStr.apply("hello ", "world"));
		// prints: hello world

		BiFunction<Double, Double, Integer> compareDoubles = Double::compare;
		System.out.println(compareDoubles.apply(10.0, 10.0));
		// prints: 0
	}

}
