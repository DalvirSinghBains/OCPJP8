package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._01_abstract_classes_and_methods;

// The abstract keyword can be applied to a class or a non-static method.

// An abstract class may have methods or fields declared static. However, the abstract
//keyword cannot be applied to fields or static methods.

// An abstract class can extend another abstract class or can implement an interface.

// An abstract class can be derived from a concrete class! Although the language allows
//it, it is not a good idea to do so.

// An abstract class need not declare an abstract method, which means it is not
//necessary for an abstract class to have any methods declared as abstract. However, if
//a class has an abstract method, it should be declared as an abstract class.

// A subclass of an abstract class needs to provide implementation of all the abstract
//methods; otherwise you need to declare that subclass as an abstract class.
		
abstract class Shape {
	public abstract double area(); 
}

class Rectangle extends Shape{

	@Override
	public double area() {
		return 0;
	}
}
