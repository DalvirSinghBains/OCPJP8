package br.com.dvlima.ocpjp8.topic05._java_stream_api._02_search_methods_of_the_stream_classes;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 * The match and find methods for searching elements are short-circuiting
 * in nature. What is short-circuiting? The evaluation stops once the result is
 * found (and the rest is not evaluated). You are already familiar with the
 * short-circuiting name of the operators && and ||. For example, in the
 * expression ((s != null) && (s.length() > 0)), if the String s is null, the
 * condition (s != null) evaluates to false; hence false is the result of the
 * expression. The remaining expression (s.length() > 0) is not evaluated in
 * this case.
 */
public class FindFirstUse2 {

	public static void main(String[] args) {
		OptionalDouble temperature = DoubleStream.of(-10.1, -5.4, 6.0, -3.4, 8.9, 2.2)//
				.filter(temp -> temp > 0)//
				.findFirst();
		System.out.println("First matching temperature > 0 is " + temperature.getAsDouble());
		// First matching temperature > 0 is 6.0
	}

}
