package br.com.dvlima.ocpjp8.apress.topic01._java_class_design._04_hashcode_equals_and_tostring_methods;

import java.util.*;

/**
 * The methods hashCode() and equals() need to be consistent for a class. For
 * practical purposes, ensure that you follow this one rule: the hashCode()
 * method should return the same hash value for two objects if the equals()
 * method returns true for them.
 */
class CircleTest {
	public static void main(String[] args) {
		Set<Circle> circleList = new HashSet<Circle>();
		circleList.add(new Circle(10, 20, 5));

		// Prints false. The class overrides the equals method, but it doesn't
		// override the hashCode() method
		System.out.println(circleList.contains(new Circle(10, 20, 5)));// false

		Circle circle = new Circle(10, 20, 5);
		System.out.println(circle.hashCode());
	}
}
