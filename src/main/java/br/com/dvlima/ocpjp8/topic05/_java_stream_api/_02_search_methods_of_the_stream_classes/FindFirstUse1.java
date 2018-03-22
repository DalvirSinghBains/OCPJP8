package br.com.dvlima.ocpjp8.topic05._java_stream_api._02_search_methods_of_the_stream_classes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class FindFirstUse1 {

	// Why does the java.util.function package have both findFirst() and findAny()
	// methods? In parallel streams, findAny() is faster to use than findFirst()
	public static void main(String[] args) {
		Method[] methods = Stream.class.getMethods();

		/**
		 * In this program, we get the list of methods in the Stream itself using
		 * reflection. Then, using map() method, we get the list of method names and
		 * check if the names end with the string Match, sort those methods, and
		 * return the first found method. If we are looking for any method name that
		 * ends with Match, then we could use findAny() method instead.
		 */
		Optional<String> methodName = Arrays.stream(methods)//
				// .peek(method -> System.out.println(method.getName()))//
				.map(Method::getName)//
				.filter(name -> name.endsWith("Match"))//
				.sorted()//
				.findFirst();
		System.out.println("Result: " + methodName.orElse("No suitable method found"));

	}

}
