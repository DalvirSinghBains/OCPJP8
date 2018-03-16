package br.com.dvlima.ocpjp8.topic01._java_class_design._05_singleton_classes_immutable_classes;

/**
 *  ImmutableCircle is an immutable class - the state of its objects
 *  cannot be modified once the object is created
 */
public final class ImmutableCircle {
	
	private final String center;
	private final int radius;
	
//	• The class is declared final to prevent inheritance and overriding of its methods
//	• The class has only final data members and they are private
//	• Because center is a mutable field, the getter method getCenter() returns a copy of the Point object
	
	/*
	public Point getCenter() {
		// return a copy of the object to avoid
		// the value of center changed from code outside the class
		return new Point(center.getX(), center.getY());
	}
	 */
	
	public ImmutableCircle() {
		center = "";
		radius = 0;
	}

	public static void main(String[] args) {
		String str1 = new String("contents");
		String str2 = new String("contents");

		System.out.println("str1 == str2 is " + (str1 == str2)); //false
		System.out.println("str1.intern() == str2.intern() is " + (str1.intern() == str2.intern())); //true
	}
}
