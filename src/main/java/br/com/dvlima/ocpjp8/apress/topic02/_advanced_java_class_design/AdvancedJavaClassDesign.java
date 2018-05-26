package br.com.dvlima.ocpjp8.apress.topic02._advanced_java_class_design;

/**
 * Advanced Java Class Design
 * <p>
 * 1- Develop code that uses abstract classes and methods
 * 2- Develop code that uses the final keyword
 * 3- Create inner classes including static inner class, local class, nested class, and anonymous inner class
 * 4- Use enumerated types including methods, and constructors in an enum type
 * 5- Develop code that declares, implements and/or extends interfaces and use the @Override annotation.
 * 6- Create and use Lambda expressions
 */
public class AdvancedJavaClassDesign {
	
	public static void main(String ... args) {
		
	}
}


//04 - Rules to remember about enums

/*An enum can define a main method. This means that you can define an enum as an executable Java application.*/
/*The enum constant list might not be followed by a semicolon, if the enum doesn' define any methods or variables*/
/*An enum constant can define a constant specific class body and use it to override existing methods or define new
variables and methods. */
/*An enum implicitly extends java.lang.Enum, so it can’t extend any other class. But a class can’t explicitly
extend java.lang.Enum. An enum can implement interface(s). */
/*An enum can never be instantiated using the keyword new. */
/*You can define multiple constructors in your enums. */
/*An enum can’t define a constructor with public or protected access level. */
/*An enum can define an abstract method. Just ensure to override it for all your enum constants. */
/*The enum method values() returns a list of all the enum constants. */
/*An enum can be defined as a top-level enum, or as a member or another class or interface. It can’t be defined
local to a method. */