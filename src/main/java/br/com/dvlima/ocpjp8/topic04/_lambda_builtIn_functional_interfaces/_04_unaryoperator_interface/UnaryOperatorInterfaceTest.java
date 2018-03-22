package br.com.dvlima.ocpjp8.topic04._lambda_builtIn_functional_interfaces._04_unaryoperator_interface;

import java.util.Arrays;
import java.util.List;

public class UnaryOperatorInterfaceTest {

	public static void main(String[] args) {
		List<Integer> ell = Arrays.asList(-11, 22, 33, -44, 55);
		System.out.println("Before: " + ell); // Before: [-11, 22, 33, -44, 55]
		ell.replaceAll(Math::abs);
		System.out.println("After: " + ell); // After: [11, 22, 33, 44, 55]

	}

}
