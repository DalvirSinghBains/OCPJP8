package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class;

public class PontsToRemember {
//	• It’s possible to define or declare generic methods in an interface or a class even 
//	if the class or the interface itself is not generic.
	
//	• A generic class used without type arguments is known as a raw type. Of course, raw 
//	types are not type safe. Java supports raw types so that it is possible to use the 
//	generic type in code that is older than Java 5 (note that generics were introduced in
//	Java 5). The compiler generates a warning when you use raw types in your code. You may
//	use @SuppressWarnings({ "unchecked" }) to suppress the warning associated with raw types.
	
//	• List<?> is a supertype of any List type, which means you can pass List<Integer>, or 
//	List<String>, or even List<Object> where List<?> is expected.
	
//	• Implementation of generics is static in nature, which means that the Java compiler 
//	interprets the generics specified in the source code and replaces the generic code with 
//	concrete types. This is referred to as type erasure. After compilation, the code looks 
//	similar to what a developer would have written with concrete types. Essentially, the use 
//	of generics offers two advantages: first, it introduces an abstraction, which enables you 
//	to write generic implementation; second, it allows you to write generic implementation 
//	with type safety.

//	• There are many limitations of generic types due to type erasure. A few important ones
//	are as follows:
	
//		• You cannot instantiate a generic type using a new operator. For example, 
//		assuming mem is a field, the following statement will result in a compiler error:
//		T mem = new T();  // wrong usage - compiler error
		
//		• You cannot instantiate an array of a generic type. For example, assuming mem 
//		is a field, the following statement will result in a compiler error:
//		T[] amem = new T[100]; // wrong usage - compiler error
		
//		• You can declare instance fields of type T, but not of static fields of type T. 
//		For example,
		
//		class X<T> {
//		    T instanceMem;  			// okay
//		    static T statMem;      	// wrong usage - compiler error
//		}

//		• It is not possible to have generic exception classes; as a result, the following will 
//		not compile:
//		    class GenericException<T> extends Throwable { } // wrong usage - compiler error
		
//		• You cannot instantiate a generic type with primitive types—in other words, 
//		List<int> cannot be instantiated. However, you can use boxed primitive types.
}
