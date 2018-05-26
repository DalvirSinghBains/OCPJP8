package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._08_method_references;

import java.util.Arrays;
import java.util.List;

public class MethodReference {

	static void printUpperCaseString(String str) {
		System.out.println(str.toUpperCase());
	}

	public static void main(String[] args) {
		List<String> strings = Arrays.asList("eenvy", "meeny", "miny", "mo");
		strings.forEach(MethodReference::printUpperCaseString);
	}

}
