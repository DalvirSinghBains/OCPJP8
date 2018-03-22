package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._02_final_keyword;

// The final modifier can be applied to a class, method, or variable. All methods of a
//final class are implicitly final (hence non-overridable).

// A final variable can be assigned only once. If a variable declaration defines a
//variable as final but did not initialize it, then it is referred to as blank final. You need
//to initialize a blank final in all the constructors you have defined in the class or in an
//initialization block.

// The keyword final can be applied to parameters. The value of a final parameter
//cannot be changed once assigned.

public abstract class Shape {

	//if you try to override the final method, you will get following error: 
	//"Cannot override the final method from Shape".
	final public void setParentShape(final Shape shape) {
		
	}
	
	public Shape getParentShape() {
		return null;
	}
}
