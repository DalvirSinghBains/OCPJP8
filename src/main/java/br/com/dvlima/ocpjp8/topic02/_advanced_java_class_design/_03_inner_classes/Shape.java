package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._03_inner_classes;

abstract class Shape {
	
//	 The name of the static nested class is expressed with
//	OuterClassName.NestedClassName syntax.
	
//	 When you define an inner nested class (or interface) inside an interface, the nested
//	class is declared implicitly public and static. This point is easy to remember: any
//	field in an interface is implicitly declared public and static, and static nested
//	classes have this same behavior.
	
//	 Static nested classes can be declared abstract or final.
	
//	 Static nested classes can extend another class or they can be used as base classes.
	
//	 Static nested classes can have static members. 
	
//	 Static nested classes can access the members of the outer class (only static members,
//	obviously).
	
//	 The outer class can also access the members (even private members) of the nested
//	class through an object of a nested class. If you dont declare an instance of the
//	nested class, the outer class cannot access nested class elements directly.

	public static class Color {
		int m_red, m_green, m_blue;

		public Color() {
			// call the other overloaded Color constructor by passing default values
			this(0, 0, 0);
		}

		public Color(int red, int green, int blue) {
			m_red = red;
			m_green = green;
			m_blue = blue;
		}

		public String toString() {
			return " red = " + m_red + " green = " + m_green + " blue = " + m_blue;
		}
	}
}
