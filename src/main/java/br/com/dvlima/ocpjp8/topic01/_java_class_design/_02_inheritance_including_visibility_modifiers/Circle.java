package br.com.dvlima.ocpjp8.topic01._java_class_design._02_inheritance_including_visibility_modifiers;

class Circle extends Shape {

	// private field
	private int radius;

	// public method
	public void area() {
		// acess to private field radius inside te class
		System.out.println("area: " + 3.14 * radius * radius);
	}

	// The fillColor method has default access
	void fillColor() {
		// access to protected field, in subclass
		System.out.println("color: " + color);
	}
}
