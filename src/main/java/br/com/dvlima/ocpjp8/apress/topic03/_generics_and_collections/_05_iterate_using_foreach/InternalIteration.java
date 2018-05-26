package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._05_iterate_using_foreach;

import java.util.Arrays;
import java.util.List;

public class InternalIteration {

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("eeny", "meeny", "miny", "mo");
		strings.forEach(s -> System.out.println(s));
	}

}
