package br.com.dvlima.ocpjp8.apress.topic01._java_class_design._03_polymorphism;

public class Overloaded {
	public static void aMethod(int val) {
		System.out.println("int");
	}

	public static void aMethod(short val) {
		System.out.println("short");
	}

	public static void aMethod(Object val) {
		System.out.println("object");
	}

	public static void aMethod(String val) {
		System.out.println("String");
	}

	public static void main(String[] args) {
		byte b = 9;

		/**
		 * In the first method call, the statement is aMethod(b) where the variable b is
		 * of type byte. There is no aMethod definition that takes byte as an argument.
		 * The closest type (in size) is short type and not int, so the compiler
		 * resolves the call aMethod(b) to aMethod(short val) definition.
		 */
		aMethod(b); // short

		/**
		 * In the second method call, the statement is aMethod(9). The constant value 9
		 * is of type int. The closest match is aMethod(int), so the compiler resolves
		 * the call aMethod(9) to aMethod(int val) definition.
		 */
		aMethod(9); // int

		Integer i = 9;
		/**
		 * The third method call is aMethod(i), where the variable i is of type Integer.
		 * There is no aMethod definition that takes Integer as an argument. The closest
		 * match is aMethod(Object val), so it is called. Why not aMethod(int val)? For
		 * finding the closest match, the compiler allows implicit upcasts, not
		 * downcasts, so aMethod(int val) is not considered.
		 */
		aMethod(i); // object

		/**
		 * The last method call is aMethod("9"). The argument is a String type. Since
		 * there is an exact match, aMethod(String val) is called.
		 */
		aMethod("9"); // String
	}
}

class OverloadingError {
	public static void aMethod(byte val) {
		System.out.println("byte");
	}

	public static void aMethod(short val) {
		System.out.println("short");
	}

	public static void aMethod(long val1, int val2) {
		System.out.println("long, int");
	}

	public static void aMethod(int val1, long val2) {
		System.out.println("int, long");
	}

	public static void main(String[] args) {
		// Error
		// aMethod(9);
		/**
		 * OverloadingError.java:61: error: no suitable method found for aMethod(int)
		 * aMethod(9);
		 */

		// Ambiguous Overload
		// aMethod(9, 10);
		/**
		 * error: reference to aMethod is ambiguous aMethod(9, 10); ^ both method
		 * aMethod(long,int) in OverloadingError and method aMethod(int,long) in
		 * OverloadingError match
		 */
	}
}
