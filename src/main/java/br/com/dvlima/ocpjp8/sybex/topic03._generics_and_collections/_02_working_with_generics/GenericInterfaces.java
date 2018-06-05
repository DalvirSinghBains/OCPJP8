package br.com.dvlima.ocpjp8.sybex.topic03._generics_and_collections._02_working_with_generics;

//There are three ways a class can approach implementing this interface.
interface Shippable<T> {
    void ship(T t);
}

//1 - The following concrete class says that it deals only with objects
class ShippableRobotCrate implements Shippable<Object> {
    public void ship(Object t) {
    }
}

// 2 - Create a generic class
class ShippableAbstractCrate<U> implements Shippable<U> {
    public void ship(U t) {
    }
}

// 3 - The final way is to not use generics at all. This is the old way of writing code.
class ShippableCrate implements Shippable {
    public void ship(Object t) {
    }
}

//what You Can’t do with Generic Types
//Call the constructor. new T() is not allowed because at runtime it would be new Object().

//Create an array of that static type. This one is the most annoying, but it makes sense because you’d be creating an array of Objects.

//Call instanceof. This is not allowed because at runtime List<Integer> and List<String> look the same to Java thanks to type erasure.

//Use a primitive type as a generic type parameter. This isn’t a big deal because you can use the wrapper class instead. If you want a type of int, just use Integer.

//Create a static variable as a generic type parameter. This is not allowed because the type is linked to the instance of the class.