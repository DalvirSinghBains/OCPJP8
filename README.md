# OCPJP8
OCP Java 8 study notes

# Implement encapsulation

Encapsulation means keeping the internal workings of your code safe from other programs that use it, allowing only what you choose to be accessed. This makes the code that uses the encapsulated code much simpler and easier to maintain since much of the complexity of the latter is hidden.

The main benefit of encapsulation is that it protects a class from being used in a way that it wasn't meant to be. By controlling the way the code is used, it can only ever do what you want it to do. 

For example, if we set variables like this:
````java
car.model = 2015;
````
We can't prevent something invalid like this:
````java
car.model = 2343242;
````
To implement encapsulation, we set up the class so only its methods can refer to its instance variables. External code will access these private instance variables only through public get/set methods. This is a convention used in reusable components called JavaBeans. The rules are:
* Instance variables are *private*
* Getter methods begin with *get* if the property is not a *boolean*, otherwise, they begin with *is*
* Setter methods begin with *set*
* The method name starts with get/is/set followed by the name of the instance variable with its first letter in uppercase

So if we have a class like the following:
````java
public class Car {
  int model;
  String name;
  String color;
}
````
The encapsulated version would look like this:
````java
public class Car {
  private int model;
  private String name;
  private String color;
  
  public int getModel() {
    return model;
  }
  public String getName() {
    return name;
  }
  public String getColor() {
    return color;
  }
  public void setModel(int model) {
    this.model = model;
  }
  public void setName(int name) {
    this.name = name;
  }
  public void setColor(int color) {
    this.color = color;
  }
}
````
Notice the use of `this` in the setter methods. The parameter name can be anything, but if it's the same as the instance variable's name, `this` (that references the instance) must be used to differentiate between them.

Of course, just like that, in the surface this code does the same as the non-encapsulated version, but by using a method instead of getting/setting the instance variable directly, we can add something like a validation without breaking the external code:
````java
public void setModel(int model) {
  if(model >= 2000 && model <= 2015) {
    this.model = model;
  } else {
    this.model = 2000;
  }
}
````
Now, if we set an invalid value:
````java
car.setModel(2343242);
````
We'll get a default and valid value that will protect the class from being used in a way it wasn't meant to be.

Encapsulation can also be used with constructors and methods of a class. The key thing is to restrict the access to any member of the class that can break things when something changes or that doesn't want to be exposed.

#Implement inheritance including visibility modifiers and composition

###Inheritance
With inheritance, you created classes based on the other classes so they can get the attributes and methods from the base class to reuse them and even redefine them.

The keyword `extends` is used to make a new class that derives from an existing class. The base class is called the superclass and the new class is called the subclass.

Let's begin for example with this class:
````java
public class Animal {
  public void eat() { /* Do something */ }
}
````
A subclass can be:
````java
public class Dog extends Animal {
}
````
By inheriting from *Animal*, the *Dog* class automatically gets the method `eat()`. We can add a method to the class:
````java
public class Dog extends Animal {
  public void bark() { /* Do something */ }
}
````
So now *Dog* has two methods, `eat()` that is inherited from *Animal* and `bark()` that is specific to the *Dog* class. *Animal* still has one method, inheritance only works from the superclasses to the subclasses.

Not everything can be inherited, and this depends directly from the used visibility modifiers.

When a subclass extends a superclass in Java, all protected and public attributes and methods of the superclass are inherited by the subclass. Attributes and methods with default (package) access modifiers can be accessed by subclasses only if the subclass is located in the same package as the superclass. Private attributes and methods of the superclass are not inherited.

| Modifier | Inherited |
| :----------------: |:------------:|
| public | Yes |
| protected | Yes |
| default | Only for subclasses in the same package |
| private | No |

When you create a subclass, the methods in the subclass cannot have less accessible access modifiers assigned to them than they had in the superclass. For instance, if a method in the superclass is public then it must be public in the subclass too.

In Java, a class is only allowed to inherit from a single superclass (singular inheritance). In some programming languages, like C++, it is possible for a subclass to inherit from multiple superclasses (multiple inheritance).

###Composition
You do composition by having an instance of another class as a field of your class instead of extending.

When to use which?
* If there is an IS-A relation, inheritance is likely to be preferred.
* If there is a HAS-A relationship, composition is preferred.

For example:
````java
class Toy {} 

class Animal{} 

// Cat is an Animal, so Cat class extends Animal class.
class Cat extends Animal { 
 // Cat has a Toy, so Cat has an instance of Toy as its member.
 private Toy toy; 
}
````

Favoring composition over inheritance is a popular object-oriented design principle that helps to create flexible and maintainable code. For example, composition facilitates testing. If one class is composed of another class, you can easily create a mock object representing the composed class.

#Implement polymorphism

With inheritance, you can take advantage of polymorphism. Polymorphism is based on the fact that you can use a variable of a superclass type to hold a reference to an object whose class is any of its subclasses, for example:
````java
Animal animal = new Dog();
````

This way, polymorphism allows the program to act differently based on the object performing the action. For example:
````java
class Animal {
  public void eat() {
    System.out.println("Animal Food");
  }
}
class Dog extends Animal {
  public void eat() {
    System.out.println("Dog Food");
  }
}
class Cat extends Animal {
  public void eat() {
    System.out.println("Cat Food");
  }
}
public class Test {
  public static void main(String args[]) {
    Animal a = new Dog();
    System.out.println(a.eat());
    a = new Cat();
    System.out.println(a.eat());
  }
}
````
The first `System.out.println(a.eat());` will print *Dog Food* since in that moment, the *a* reference is holding a *Dog* object. In the second call, it will print *Cat Food*, since now the reference holds a *Cat* object.

So polymorphism can help make code easier to change. If you have a fragment of code that uses a variable of a superclass type, such as *Animal*, you could later create a brand new subclass with another behavior, such as *Spider*, polymorphism will ensure that *Spider*'s implementation of *Animal*'s method gets executed and the fragment will work without change. 

#Override hashCode, equals, and toString methods from Object class

###equals
From [javadoc](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-):

`public boolean equals(Object obj)`
Indicates whether some other object is "equal to" this one.  
The equals method implements an equivalence relation on non-null object references:
* It is reflexive: for any non-null reference value x, x.equals(x) should return true.
* It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
* It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
* It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
* For any non-null reference value x, x.equals(null) should return false.
* The equals method for class Object implements the most discriminating possible equivalence relation on objects; that is, for any non-null reference values x and y, this method returns true if and only if x and y refer to the same object (x == y has the value true).

It is generally necessary to override the `hashCode` method whenever this method is overridden, so as to maintain the general contract for the `hashCode` method, which states that equal objects must have equal hash codes.

###hashCode
From [javadoc](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--):

`public int hashCode()`
Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by `HashMap`.
The general contract of hashCode is:
* Whenever it is invoked on the same object more than once during an execution of a Java application, the `hashCode` method must consistently return the same integer, provided no information used in equals comparisons on the object is modified. This integer need not remain consistent from one execution of an application to another execution of the same application.
* If two objects are equal according to the equals(Object) method, then calling the `hashCode` method on each of the two objects must produce the same integer result.
* It is not required that if two objects are unequal according to the equals(java.lang.Object) method, then calling the `hashCode` method on each of the two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.
* As much as is reasonably practical, the hashCode method defined by class `Object` does return distinct integers for distinct objects. (This is typically implemented by converting the internal address of the object into an integer, but this implementation technique is not required by Java.)

The relation between the two methods is:

Whenever `a.equals(b`), then `a.hashCode()` must be same as `b.hashCode()`.

###toString
From [javadoc](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#toString--):

`public String toString()`
Returns a string representation of the object. In general, the `toString` method returns a string that "textually represents" this object. The result should be a concise but informative representation that is easy for a person to read. It is recommended that all subclasses override this method.
The `toString` method for class `Object` returns a string consisting of the name of the class of which the object is an instance, the at-sign character '@', and the unsigned hexadecimal representation of the hash code of the object. In other words, this method returns a string equal to the value of:

 `getClass().getName() + '@' + Integer.toHexString(hashCode())`
 
  
  
 The following is an example of an implementation of these methods:
 ````java
 public class Person {
   private final String lastName;
   private final String firstName;
   private final boolean female;
   
   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final Person other = (Person) obj;
      if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName))
      {
         return false;
      }
      if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName))
      {
         return false;
      }
      if (this.female != other.female)
      {
         return false;
      }
      return true;
   }
   
   @Override
   public int hashCode()
   {
      int hash = 3;
      hash = 19 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
      hash = 19 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
      hash = 19 * hash + (this.female ? 1 : 0);
      return hash;
   }

   @Override
   public String toString()
   {
      return  "Person{" + "lastName=" + lastName + ", firstName=" + firstName
            + ", female=" + female +  '}';
   }
 }
 ````
#Create and use singleton classes and immutable classes
###Singleton
Singleton is a design pattern that provides a way for a class to create only one object from that class. 

The key for a Singleton class is to make the constructor private, have a static instance of itself and a method to access it:
````java
public class Singleton {
  //Create an object
   private static final Singleton instance = new Singleton();

   //Make the constructor private so that this class cannot be instantiated
   private Singleton(){}

   //Get the only object available
   public static Singleton getInstance(){
      return instance;
   }
}
````
The line `private static final Singleton instance = new Singleton();` is only executed when the class Singleton is actually used. This guaranteed the instantiation to be thread safe. To use the singleton class:
````java
Singleton s = Singleton.getInstance();
````

The other ways to build a Singleton class are:

**Using an Enum**
````java
public enum Singleton{
    INSTANCE;
}
````
**Locking /Lazy loading with Double checked Locking**
````java
public class Singleton{
     private static volatile Singleton instance;

     private Singleton(){}

     public static Singleton getInstance(){
         if(instance == null){
            synchronized(Singleton.class){
                //double checking Singleton instance
                if(instance == null){
                    instance = new Singleton();
                }
            }
         }
         return instance;
     }
}
````

###Immutable objects
Immutable objects are simply objects whose state (data) cannot change after construction, for examples the String class. They are useful in concurrent applications, since they cannot change state, they cannot be corrupted by threads.

There are several ways for creating immutable objects:
* Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
* Make all fields final and private.
* Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final.
* Make the class a Singleton
* If the instance fields include references to mutable objects, don't allow those objects to be changed:
    * Don't provide methods that modify the mutable objects.
    * Don't share references to the mutable objects. Never store references to external, mutable objects passed to the constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.

#Develop code that uses static keyword on initialize blocks, variables, methods, and classes

A static member belongs to the class rather than to an instance of the class.

###Static Initialize Blocks
A static block is used to initialize a static variable or execute some initialize code since the block is executed at the time of the class loading, before any constructors or methods.
````java
class Block {  
  static {  
    System.out.println("static block executed"); 
  }
  
  Block() {
    System.out.println("constructor executed"); 
  }
  
  public static void main(String args[]) {
    new Block();
    System.out.println("main method executed"); 
  }
} 
````
The output is:
````
static block executed
constructor executed
main method executed
````
Static blocks are executed in the order they are defined.

###Statics Variables
A static variable is used to refer a common property of all objects or instances (something that is not unique for each object)  of a class. It's initialized at the time of class loading.
````java
public class Man {
  String name;
  static String gender = "M";
  
  Man(String name) {
    this.name = name;
  }
  
  void display() {
    System.out.println(name+" "+name+" "+gender+" "+gender);
  }  
  
  public static void main(String args[]) {
    Man m1 = new Man("Bob");
    Man m2 = new Man("Richard");
    
    m1.display();
    m2.display();
  }
}
````
The output is:
````
Bob M
Richard M
````

###Static Method
A static method also belongs to the class rather than object of a class and can be invoked without the need for creating an instance of a class. The only restrictions are:
1. A static method can only access another static  member.
2. `this` and `super` cannot be used in a static method.
````java
public class Square {  
    static int calculate(int x){  
    return x*x;  
  }  
  
  public static void main(String args[]){  
    int result = Square.calculate(9);  
    System.out.println(result);  
  }  
} 
````

###Static Classes
We can define a class within another class. Such a class is called a nested class. We can't make a top level class static. Only nested classes can be static.

````java
class OuterClass {
   // Static nested class
   public static class NestedStaticClass{
       public void print() { 
         System.out.println("Message from nested static class"); 
       }
    }
} 

public class Test {
    public static void main(String args[]) {
       // create instance of nested Static class
       OuterClass.NestedStaticClass sc = new OuterClass.NestedStaticClass();

       // call non static method of nested static class
       sc.print();
    }
}
````

The characteristics of a static nested class are:
* Nested static classes don't need a reference to their outer class (the enclosing class)
* Only static members of the outer class are accessible in a nested static class


#Develop code that uses abstract classes and methods

An abstract class is a class that is declared `abstract`. Abstract classes cannot be instantiated, only subclassed.
````java
abstract class Animal {
  public void eat() {}
}
````

An abstract method is a method that is declared without an implementation, like this:
````java
public abstract void method(int arg);
````
If a class includes an abstract method, it has to be declared abstract. When an abstract class is subclassed, if the subclass doesn't provide an implementation for the inherited abstract methods, it has to be declared abstract as well.
````java
abstract class Animal {
  public void eat() {
   /** Do something */
  }
  public abstract makeSound();
}
class Dog extends Animal {
  public makeSound() {
    System.out.println("Bark");
  }
}
````

If an abstract class has static members, you can use them with a class reference (`AbstractClass.staticMethod()`) as you would with any other class.

Abstract classes are similar to interfaces. However, with abstract classes, you can declare fields that are not static and final, and define public, protected, and private concrete methods. With interfaces, all fields are automatically public, static, and final, and all methods that you declare or define are public. In addition, you can extend only one class, whether or not it is abstract, whereas you can implement any number of interfaces.

Use abstract classes when:
* You want to share code among several closely related classes.
* You expect that classes that extend your abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).
* You want to declare non-static or non-final fields. This enables you to define methods that can access and modify the state of the object to which they belong.

Use interfaces when:
* You expect that unrelated classes would implement your interface. For example, the interfaces *Comparable* and *Cloneable* are implemented by many unrelated classes.
* You want to specify the behavior of a particular data type, but not concerned about who implements its behavior.
* You want to take advantage of multiple inheritance of type.

#Develop code that uses final keyword

The final keyword can be used in variables, methods, and classes.

###Final Variable
If you make a variable final, you cannot change its value after is initialized. It can only be initialized in the constructor or when it's declared. If it's static, it will be initialized in a static block or when it's declared.

If the variable holds an object, it cannot be assigned to other objects, but the attributes of that object can be changed.
````java
class Car {  
 final int speed = 70; 
 void speedUp(){  
  speed = 90;  //Compile-time error
 }  
````

###Final Method
If you make a method of a class final, a subclass cannot override that method when inherited.
````java
class Animal {  
  final public void eat() {
    System.out.println("eating");
  }  
}  
class Dog extends Animal {  
  void eat() { //Compile-time error
    System.out.println("eating");
  }  
}
````
###Final Class
If you make any class final, you cannot extend it.
````java
final class Animal {  
  public void eat() {
    System.out.println("eating");
  }  
}  
class Dog extends Animal { //Compile-time error  
}


#Create inner classes including static inner class, local class, nested class, and anonymous inner class
An inner class is a class declared inside another class.
 
###Static Inner Class
An inner class declared with the static keyword becomes a static inner class, for example:
 ````java
class OuterClass {
   // Static nested class
   public static class NestedStaticClass {
       public void print() { 
         System.out.println("Message from nested static class"); 
       }
    }
} 

public class Test {
    public static void main(String args[]){
       // create instance of nested Static class
       OuterClass.NestedStaticClass sc = new OuterClass.NestedStaticClass();

       // call nonstatic method of nested static class
       sc.print();
    }
}
````

The rules of a static nested class are:
* Nested static classes don't need a reference of their outer class (the enclosing class)
* Only static members of the outer class are accessible in a nested static class

###Local Inner Class
A class created inside a method or a block is called local inner class. If you want to use a local inner class, you must instantiate it inside the method or block.
````java
public class LocalClass{  
 private int n = 5;
 
 void result(){  
   // Local inner class
   class Cube {  
     int calc() {
        return n*n*n;
     }  
   }
  
   Cube c = new Cube();  
   System.out.println(c.calc());  
 }  
 
 public static void main(String args[]) {  
    LocalClass lc = new LocalClass();  
    lc.result();  
 }  
}  
````
The rules of a local inner class are:
* Local inner class cannot be invoked from outside the method.
* Since java 8, it's possible to access the non-final local variables in a local inner class.

###Nested class
A nested class or member inner class is a non-static class that is created inside a class but outside a method.
 ````java
class OuterClass {
   // Nested class
   public class NestedClass {
       public void print() { 
         System.out.println("Message from nested class"); 
       }
    }
} 

public class Test {
    public static void main(String args[]){
       // create instance of outer class
       OuterClass oc = new OuterClass();
       // create instance of nested Static class
       OuterClass.NestedClass nc = oc.new NestedClass();

       // call nonstatic method of nested static class
       sc.print();
    }
}
````

The rules of a nested class are:
* Nested  classes need a reference of their outer class
* Static and non-static members of the outer class are accessible in a nested  class
* If the nested class is used inside the class that defines it, the keyword this can be used to create an instance of the nested class (for example `OuterClass.NestedClass nc = this.new NestedClass();`)

There's also a concept named shadowing. If a declaration of a type (such as a member variable or a parameter name) in a particular scope (such as an inner class or a method definition) has the same name as another declaration in the enclosing scope, then the declaration shadows the declaration of the enclosing scope, for example:
````java
public class Test {
    public int x = 10;

    class Inner {
        public int x = 1;
        void m(int x) {
            System.out.println("x = " + x);
            System.out.println("this.x = " + this.x);
            System.out.println("Test.this.x = " + Test.this.x);
        }
    }

    public static void main(String... args) {
        Test t = new Test();
        Test.Inner i = t.new Ineer();
        i.m(5);
    }
}
````
This example defines three variables named x, the member variable of the class Test, the member variable of the inner class Inner, and the parameter in the method m. The variable x defined as a parameter of the method shadows the variable of the inner class. So, when you use the variable x in the method m, it refers to the method parameter. To refer to the member variable of the inner class Inner, use the keyword this to represent the enclosing scope. To refer to member variables that enclose larger scopes, use the class name to which they belong. The output of the example is:
````
x = 5
this.x = 1
Test.this.x = 10
````

###Anonymous Inner Class
Anonymous Inner classes are classes without a name (but not without type) used to override a method of class or interface. They can't have constructors.
````java
interface Animal {  
  void eat();  
}  
public class Test {  
  public static void main(String args[]){  
    Animal a = new Animal(){  
      public void eat() { System.out.println("eat"); }  
    };  
    a.eat();  
  }  
} 
````

The code:
````java
    Animal a = new Animal() { //This is the only case where you can use the keyword 'new' with an interface
      public void eat() { System.out.println("eat"); }  
    }; 
````
Represents:
* A class that is created but have its name decided by the compiler
* The class implements the *Animal* interface and provides the implementation of the *eat* method.
* An object is created and referred by the *a* variable of *Animal* type.

The rules to access variables in anonymous classes are:
* An anonymous class has access to the members of its enclosing class.
* An anonymous class cannot access local variables in its enclosing scope that are not declared as final or effectively final.
* Like a nested class, a declaration of a variable in an anonymous class shadows any other declarations in the enclosing scope that have the same name. 


#Use enumerated types including methods, and constructors in an enum type
Enumerated types (or enums) are classes that can be used to define a set of constants. They are type-safe, meaning that you cannot assign anything else other than the predefined constants to an enum variable.

Here's an example:
````java
public enum Colors {
    RED,
    BLUE,
    BLACK
}
````
You can refer to the constants in the enum like this:
````java
Colors color = Colors.BLUE;
````
Enums extend from `java.lang.Enum` implicitly, so they cannot extend another class. But they can implement an interface and override any method like a normal class.

You can specify values of enum constants at the creation time, but you need to define a constructor for this and, optionally, a method to get these values, for example:
````java
public enum Colors {
    RED("#ff0000"),
    BLUE("#3366cc"),
    BLACK("#000000");
    
    private String hexValue;

    private Colors(String hexValue) {
      this.hexValue = hexValue;
    }
    
    public String getHexValue() {
      return value;
    }
}
````
And the method is used like this:
````java
String value = Colors.BLUE.getHexValue();
````
If an enum contains attributes and methods, their definition  must always come after the list of constants in the enum and the list of constants must be terminated by a semicolon.

The constructor of an enum must be private, any other access modifier will result in compilation error. For this reason, you cannot create an instance of an enum by using the *new* operator.

Enums can be used in if statements in this way:
````java
Colors color = Colors.BLUE;

if( color == Colors.RED) {
  /** Do something */
} else if( color == Colors.BLUE) {
  /** Do something */
} else if( color == Colors.BLACK) {
  /** Do something */
}
````
And in switch statements like this:
````java
Colors color = Colors.BLUE;

switch (color) {
    case RED: /** Do something */; break;
    case BLUE: /** Do something */; break;
    case BLACK: /** Do something */; break;
}
````
You can also get all the possible values of an enum type by calling the static `values()` method:
````java
for (Colors c : Colors.values()) {
    System.out.println(c);
}
````
The order of the values is exactly the same in which they were defined.


#Develop code that declares, implements and/or extends interfaces and use the atOverride annotation
###Interfaces
An interface is like an abstract class, except that it cannot contain an implementation of the methods, only their signature (return type, name, parameters, and exceptions).

An interface is declared using the interface keyword. Just like classes, an interface can be declared public or with package scope (no access modifier).
````java
public interface Vehicle {
    public String serie = "XXX";
    public void start();
}
````
The variables declared in an interface are public, static and final by default. The methods are public and abstract by default.

Before you can use an interface, it must be implemented by some class:
````java
public class Truck implements Vehicle {
    public void start() {
        System.out.println("Starting truck...");
    }
}
````
A class that implements an interface must implement all the methods declared in the interface. The only exception is default methods. Java 8 introduces this feature, which provides the flexibility to allow an interface to define an implementation which will be used as default in the situation where a class fails to provide an implementation for that method.

This is made by adding the keyword `default` before the method's access modifier and adding its implementation inside the interface itself:
````java
interface Vehicle {
    default public void start() {
        System.out.println("Default start");
    }
}

class Car implements Vehicle {
    // valid in Java 8
}
````

Once a class implements an interface, an instance of that class can be assigned to a reference of the interface type:
````java
Vehicle truck = new Truck();
````

A class can implement multiple interfaces. In that case, the class must implement all the methods declared in all the interfaces implemented:
````java
interface Run {
    public void run();
}
interface Sleep {
    public void sleep();
}
public class Man
    implements Run, Sleep {

    public void run() {
        System.out.println("run");
    }

    public void sleep() {
        System.out.println("sleep");
    }
}
````
An interface cannot extend from another class or implement another interface. It can only extend another interface(s):
````java
interface Run {
    public void run();
}
interface Sleep {
    public void sleep();
}
interface Behavior extends Run, Sleep {
}

public class Man
    implements Behavior {

    public void run() {
        System.out.println("run");
    }

    public void sleep() {
        System.out.println("sleep");
    }
}
````
If a class implements *Behavior*, it has to implement all methods defined in both *Run* and *Sleep* interfaces.

###atOverride Annotation
When you use the `@Override` annotation, the compiler checks to make sure you're actually overriding a method. For example, if you misspell the method name or not match the parameters correctly, you will be warned that you're not actually overriding a method of the superclass.  

The most common use case for `@Override` is with *Object* methods:
````java
public class Test {
  @Override
  public String toString() {
    /** Do something */
  }
  @Override
  public boolean equals(Object) {
    /** Do something */
  }
  @Override
  public int hashCode() {
    /** Do something */
  }
}
````


#Create and use Lambda expressions
Think about an interface with one method, like the one used to create threads:
````java
public interface Runnable() {
  public void run();
}
````
You can use an anonymous class to implement that interface:
````java
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("run");
    }
}).start();
````
Lambda expressions can be used in cases like this, where you have what is called a functional interface, an interface with just one method declared in it.

A lambda expression has the following syntax:
````
parameter -> expression body
````
* The type declaration is optional. The compiler can inference the type from the value of the parameter.
* Parentheses around the parameter are optional. The parentheses are required only for multiple parameters.
* Curly braces in the expression body are optional. - The braces are required only if the expression body contains more than one statement.
* A return keyword in the expression body is optional. The compiler automatically returns a value if the expression body has a single expression to return the value. 

Here are some examples of Lambda expressions.
````java
(int a, int b) -> {  return a * b; }
() -> System.out.println("Hi");
String s -> { System.out.println(s); }
(a) -> a
() -> return 100;
````

Using a lambda expression, a thread can be started in this way:
````java
new Thread(
    () -> System.out.println("run");
).start();
````
In this case, the lambda expression replaces the method `run()`. Since this method has no parameters, the parentheses have no content in between. That is to signal that the lambda takes no parameters. Also, with lambda expressions, the type can be inferred from the surrounding code, so there is no need to reference the *Runnable* interface.

Lambda expressions are objects. You can assign a lambda expression to a variable and pass it around like this:
````java
Runnable r = () -> System.out.println("run");
new Thread(r).start();
````

So lambda expressions only work with one-method interfaces (called functional interfaces). There's even an annotation to make the compiler check if an interface has more than one method:
````java
@FunctionalInterface
public interface FuncInterface { //Generates a compile-time error
    public void doSomething();
    public void doMoreSomething();
}
````

Here's another example of the use of a lambda expression:
````java
@FunctionalInterface
interface MathFunction {
    public int operation(int a, int b);
}

public class Test {
   public static void main(String args[]) {
      MathFunction multiply = (a, b) -> a * b;
      MathFunction divide = (a, b) -> a / b;

      System.out.println("4 * 2 = " + multiply.operation(4, 2));	   
      System.out.println("4 / 2 = " + divide.operation(4, 2));  
   }
}
````
In summary, lambda expressions are basically used to define an implementation of a functional interface instead of using an anonymous class.

#Create and use a generic class
Generics introduce the concept of a type variable. Type variables are delimited by angle brackets and follow the class (or the interface) name:
````java
class Test<T> {
  void m(T arg) { /** Do something with the argument of type T */ }
}
````
Type variables are parameters that provide information to the compiler so it can check types. In the example above, class *Test* works with an object of type T, the same type that takes method *m*. Here's how we will use it:
````java
Test<String> t1 = new Test<>();
t1.m("a");

Test<Integer> t2 = new Test<>();
t2.m(1);
````
Notice that the generic parameter on the right is optional, it can be inferred by the compiler using the reference declaration.

So generics are a mechanism for type checking, for example, when we create an instance of *List<String>*:
````java
List<String> l = new ArrayList<String>();
l.add("a");
l.add("b");
````
If we tried to put some other type of object different than *String*, the compiler would raise an error:
````java
l.add(1); //Compile-time error
````
To get a value from the collection:
````java
String s = l.get(0);
````
To iterate the collection:
````java
for(String s : l) {
    System.out.println(s);
}
````
Now consider this object hierarchy:
````java
public class Animal { }
public class Dog extends Animal { }
public class Cat extends Animal { }
````

There are three generic wildcard operators:
````java
List<?>           l1 = new ArrayList<>();
List<? extends Animal> l2 = new ArrayList<>();
List<? super   Animal> l3 = new ArrayList<>();
````
The unknown wildcard (`List<?>`) means the list is typed to an unknown type, so the list could hold any type. Since we don't know the type of the *List*, you can't add elements to the collection, you can only read from the collection and treat the objects as *Object* instances:
````java
List<?> l = new ArrayList<>();
for(Object o : l){
      System.out.println(o);
}
````
The extends wildcard (`List<? extends Animal>`) means the list can hold objects that are instances of *Animal*, or subclasses of *Animal* (e.g. *Dog* and *Cat*). Again, you cannot insert elements into the list, because you don't know the exact type of the list. But you can get the elements from the collection because you can safely say that its elements are of instances or subclasses of *Animal*:
````java
List<? extends Animal> l = new ArrayList<>();
for(Animal a : l){
      System.out.println(a);
}
````
The super wildcard  (`List<? super Animal>`) means the list is typed to either the *Animal* class, or a superclass of *Animal*. This time, you can insert elements into the list because you know that the list is typed to either *Animal* or a superclass of *Animal*, so it is safe to insert instances of *Animal* or subclasses of *Animal* (like *Dog or *Cat*) because they are an *Animal*:
````java
List<? super Animal> l = new ArrayList<>();
l.add(new Animal());
l.add(new Dog());
l.add(new Cat());
````
However, to get elements from the list, you must cast the result to *Object*, since the elements could be of any type that is either an *Animal* or a superclass, but it is not possible to know exactly which. The only thing you know for sure is that any class is a subclass of *Object*:
````java
List<? super Animal> l = new ArrayList<>();
for(Object o : l){
      System.out.println(o);
}
````
#Create and use ArrayList, TreeSet, TreeMap, and ArrayDeque objects
###ArrayList
ArrayList is an implementation of the List interface that internally uses an Array to store the elements. This implementation is not synchronized.

Each ArrayList instance has a capacity. The capacity is the size of the array used to store the elements in the list. It is always at least as large as the list size. As elements are added to an ArrayList, its capacity grows automatically.

Here's the [javadoc](http://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) and an example:
````java
public class Test {
  public static void main(String[] args) {
  	List<Integer> l = new ArrayList<>();
  
  	// Add elements
  	l.add(1);
  	l.add(2);
  	l.add(3);
  
  	// Get size.
  	int count = l.size();
  
  	// Get an element with the zero-based index.
  	Integer i = l.get(0);
  }
}
````

###TreeSet
TreeSet is an implementation of the Set interface that uses a tree for storage, which makes access time very fast. The elements are ordered using their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used. This implementation is not synchronized.

Here's the [javadoc](http://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html) and an example:
````java
public class Test {
  public static void main(String[] args) {
  	Set<Integer> ts = new TreeSet<>();
  
  	// Add elements
  	ts.add(2);
  	ts.add(1);
  	ts.add(3);
  
  	// Get size.
  	System.out.println(ts); // Prints [1,2,3]
  }
}
````

###TreeMap
TreeMap is an implementation of the Map interface that uses a tree for storage key/value pairs, which makes access time very fast. The elements are ordered using the natural ordering or their keys, or by a Comparator provided at map creation time, depending on which constructor is used. This implementation is not synchronized.

Here's the [javadoc](http://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html) and an example:
````java
public class Test {
  public static void main(String[] args) {
  	  Map<String, Integer> tm = new TreeMap<>();
      // Put elements to the map
      tm.put("A", 10);
      tm.put("C", 40);
      tm.put("B", 20);
      
	    // Get a set of the entries
      Set<Entry<String, Integer>> set = tm.entrySet();
      // Get an iterator
      Iterator<Entry<String, Integer>> i = set.iterator();
      // Display elements
      while(i.hasNext()) {
         Entry<String, Integer> me = i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
      }
      
      // Get an element
      Integer i = tm.get("C"));
  }
}
````

###ArrayDeque
ArrayDeque is an implementation of the Deque interface.  Array deques have no capacity restrictions; they grow as necessary to support usage. They are not thread-safe. Null elements are prohibited. This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces. Elements are stored in the order (first or last) in which they are inserted.

Here's the [javadoc](http://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html) and an example:
````java
public class Test {
  public static void main(String[] args) {
  	Deque<Integer> d = new ArrayDeque();
  
  	//Add elements
  	d.add(1); //add element at tail
    d.addFirst(2); //add element at head
    d.addLast (3); //add element at tail
    
    //Get elements
    Integer firstElement1 = d.element(); //peek at the element at the head without taking the element out of the queue (throws exception is the queue is empty)
    Integer firstElement2 = d.peek(); //peek at the element at the head without taking the element out of the queue (returns null is the queue is empty)
    Integer firstElement3 = d.getFirst();//get first element (throws exception is the queue is empty)
    Integer firstElement4 = d.peekFirst();//get first element (returns null is the queue is empty)
    Integer lastElement1  = d.getLast();//get last element (throws exception is the queue is empty)
    Integer lastElement2  = d.peekLast();//get last element (returns null is the queue is empty)
    
    //Remove elements
    Integer element1 = d.remove(); //retrieves and removes the head of the queue
    Integer element2 = d.removeFirst(); //retrieves and removes the first element of the queue
    Integer element3  = d.removeLast(); //retrieves and removes the last element of the queue
  }
}
````

#Use java.util.Comparator and java.lang.Comparable interfaces
`compareTo(obj)` is the method of the [Comparable](http://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html) interface that is called on one object, to compare it to another object, so the object to be compared has to implement this interface.
````java
String s = "hello";
int result = s.compareTo("world");
````
`compare(obj1, obj2)` is the method of the [Comparator](http://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html) interface that is called on some object to compare two other objects, so a utility class has to implement this interface to be used somewhere else.
````java
Comparator<String> comp = new MyComparator<>();
int result = comp.compare("hello", "world");
````

Java classes that have a natural ordering implement the *Comparable* interface (like *String*, *Integer*, *BigInteger*, etc).

The *Comparator* interface is typically used for sorting data structures such as *TreeMap* and *TreeSet* or to be passed to a sort method (such as `Collections.sort` or `Arrays.sort`).

Both methods return:
0 if the objects are equal (this have to be consistent with the equals() method)
-1 if the first object (or the object making the comparison) is "less" than the other object
1 if the first object (or the object making the comparison) is "greater" than the other object

Here's and example:
````java
class Person implements Comparable<Person> {
	private int age;
	private String name;
 
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
 
	public int getAge() {
		return age;
	}
 
	public void setAge(int age) {
		this.age = age
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	@Override
	public int compareTo(Person p) {
		if (this.getAge() > p.getAge())
			return 1;
		else if (this.getAge() < p.getAge())
			return -1;
		else
			return 0;
	}
}

class AgeComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		int age1 = p1.getAge();
		int age2 = p2.getAge();
 
		if (age1 > age2) {
			return 1;
		} else if (age1 < age2) {
			return -1;
		} else {
			return 0;
		}
	}
}

public class Test {
	public static void main(String[] args) {
		Pesron p1 = new HDTV(60, "James");
		Person p2 = new HDTV(55, "Bryan");
 
		if (p1.compareTo(p2) > 0) {
			System.out.println(p1.getName() + " is older.");
		} else {
			System.out.println(p2.getName() + " is older.");
		}
		
		//Sorted by age
		List<Person> l = new ArrayList<>();
		l.add(p1);
		l.add(p2);
 
		Collections.sort(l, new AgeComparator());
		for (Person p : l)
		  System.out.println(p.getName());
	}
}
````

Output:
````
James is older
Bryan
James
````

#Collections Streams and Filters
A stream represents a sequence of elements. It supports different kind of operations to perform computations upon those elements.

In Java 8, collections have methods that return a stream, for example, *List* and *Set* support the new methods `stream()` and  `parallelStream()` to either create a sequential or a parallel stream.

But we don't have to create collections in order to work with streams:
````java
Stream.of("a1", "a2", "a3")
    .forEach(System.out::println);
````
`Stream.of()` will create a stream from object references.

Besides regular object streams, Java 8 brings special kinds of streams for working with the primitive data types, such as *IntStream*, *LongStream* and *DoubleStream*.

*IntStreams* for example, can replace the regular for loop using `IntStream.range()`:
````java
IntStream.range(1, 10)
    .forEach(System.out::println);
````
Streams cannot be reused. As soon as you call any terminal operation the stream is closed. Terminal operations return either a void or non-stream result, like the `forEach()` method. To overcome this limitation, we have to create a new stream chain for every terminal operation we want to execute.

Before Java 8, we used for loops or iterators to iterate through the collections and filter them. In Java 8, stream operations have methods like foreach, map, filter, etc. which internally iterates through the elements.
For example:
````java
List<String> names = newArrayList<>();
for(Employee e : employees) {
  if(e.getName().startsWith("C")) {
    names.add(e.getName());
  }
}
````
Now, the line below is doing exactly the same thing but using stream and a filter:
````java
List<String> names = employees.stream().filter(e -> e.getName().startsWith("A")).collect(Collectors.toList());
````

#Iterate using forEach methods of Streams and List

In Java 8, collections that implement *Iterable* (such as *List* and *Set*) now have a `forEach()` method. This method takes as a parameter a functional interface, therefore, the parameter can be a lambda expression:
````java
List<String> l = new ArrayList<>();

l.add("A");
l.add("B");
l.add("C");
l.add("D");

l.forEach(i -> System.out.println(i));
````
Using Stream's `forEach()` method is almost the same:
````java
List<String> l = new ArrayList<>();

l.add("A");
l.add("B");
l.add("C");
l.add("D");

l.stream().forEach(i -> System.out.println(i));
````

The advantage of using a stream is that we can perform operations on the elements of the collection before the iteration.

#Describe Stream interface and Stream pipeline

A stream is a sequence of elements supporting sequential and parallel aggregate operations.  A stream is not a data structure that stores elements. Instead, it just carries values from a source through a pipeline. Here's the [javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html).

A stream pipeline is just a sequence of aggregate operations. A stream pipeline consists of:
* A stream source, like a collection
* Intermediate operations that transform the stream and produce a new stream, like `filter()` and
* A terminal operation that either produces a result or calls the `forEach()` method. 

An example of a pipeline that consists of the aggregate operations filter and count:
````java
long count = words.stream()
                  .filter(w -> w.endsWith("ly"))
                  .count();
````


#Filter a collection by using lambda expressions

The way to filter collections is through the use of a [Predicate](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html), which is basically something that returns a boolean value.

*Predicate* is a functional interface, which means that wherever an implementation of this interface is expected, we can pass a lambda expression.

For example, consider a list of objects of this class:
````java
class Person {
	private int age;
	private String name;
 
	public int getAge() {
		return age;
	}
 
	public void setAge(int age) {
		this.age = age
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
}
````
To filter the list, first we need to convert it to a stream and then pass a lambda expression that returns a boolean value to its `filter()` method:
````java
List filterd = l.stream().filter(p -> p.getAge() > 20).collect(Collectors.toList());
````

But what if we want to use multiple conditions to filter the list? The *Predicate* interface has some methods to join conditions:
````java
Predicate<Person> nameNotNull = p -> p.getName() != null;
Predicate<Person> ageAbove20 = p -> p.getAge() > 20;

Predicate<Person> multipleConditions = nameNotNull.and(ageAbove20);
List filterd = l.stream().filter(multipleConditions).collect(Collectors.toList());
````

#Use method references with Streams

When the body of a lambda expression is used to execute a method, like in this example:
````java
List<String> l = new ArrayList<>();
l.stream().forEach(s -> System.out.println(s));
````
We can substitue the lambda expression with a method reference like this:
````java
List<String> l = new ArrayList<>();
l.stream().forEach(System.out::println);
````
Notice how **::** is used to call the method and that no parameters are passed, since the compiler infers them along with their type.

There are four types of method references (assuming a class named Person with a `getName()` method and a variable named p of that type):

|Type|Example|
|----|---------|
|Reference to a static method|`Math::square`|
|Reference to a constructor	|`Integer::new`|
|Reference to an instance method of an arbitrary object of a particular type	|`Person::getName`|
|Reference to an instance method of a particular object	|`p::getName`|

Here's a comparison between a lambda expression and the equivalent method expression:

|Lambda Expression|Method Reference|
|----|---------|
|`n -> Math.square(n)`|`Math::square`|
|`i -> new Integer(i)`	|`Integer::new`|
|`p -> p.getName()`	|`Person::getName`|
|`p -> p.getName()`	|`p::getName`|


#Use  the built-in interfaces included in the java.util.function package such as Predicate, Consumer, Function, and Supplier
Functional interfaces provide target types for lambda expressions and method references. Each functional interface has a single abstract method, called *functional method* for that functional interface, to which the lambda expression's parameter and return types are matched or adapted.

###Predicate
A predicate is a statement that may be true or false depending on the values of its variables. It can be thought of as a function that returns a value that is either true or false.

In Java 8, a *Predicate* is a functional interface that can be used anywhere you need to evaluate a boolean condition. Since it's a functional interface, you can pass a lambda expression wherever a *Predicate* is expected.

See the [API](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html) to know the methods of this interface.

Here's an example. First, we see how the interface with an anonymous class:
````java
Predicate<String> isALongWord = new Predicate<String>() {
    @Override
    public boolean test(String t) {
        return t.length() > 10;
    }
};
String s = "successfully"
boolean result = isALongWord.test(s);
````
And now with a lambda expression:
````java
Predicate<String> isALongWord = t -> t.length() > 10;
String s = "successfully"
boolean result = isALongWord.test(s);
````
Predicates are also used to filter collections, for example:
````java
public class Test {
  public static void main(String[] args) {
    List<String> l = new ArrayList<>();
    l.add("successfully");
    l.add("easy");
    l.add("fortune");
    List<String> filtered = l.stream().filter( s -> s.length() > 5 ).collect(Collectors.<String>toList());
    System.out.println(filtered);
  }
}
````
Here, the filter method expects a *Predicate*, so we can pass a lambda expression to simplify things, so the output of the example is:
````
["successfully", "fortune"]
````

###Consumer
This functional interface represents an operation that accepts a single input argument and returns no result. The real outcome is the side-effects it produces. Since it's a functional interface, you can pass a lambda expression wherever a *Consumer* is expected.

See the [API](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html) to know the methods of this interface.

Here's an example:
````java
class Product {
  private double price = 0.0;
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  public void printPrice() {
    System.out.println(price);
  }
}

public class Test {
  public static void main(String[] args) {
    Consumer<Product> updatePrice = p -> p.setPrice(5.9);
    Product p = new Product();
    updatePrice.accept(p);
    p.printPrice();
  }
}
````
Basically, what *Consumer* does is executing the assigned lambda expression. The side-effect here, it's the updating of the product's price, so the output is:
````
5.9
````

###Function
This functional interface represents a function that accepts one argument and produces a result. One use, for example, it's  to convert or transform from one object to another. Since it's a functional interface, you can pass a lambda expression wherever a *Function* is expected.

See the [API](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html) to know the methods of this interface.

Here's an example:
````java
public class Test {
  public static void main(String[] args) {
    int n = 5;
    modifyTheValue(n, val-> val + 10);
    modifyTheValue(n, val-> val * 100);
  }
  
  static void modifyValue(int v, Function<Integer, Integer> function){
    int result = function.apply(v);
    System.out.println(newValue);
  }
  
}
````
The input parameter type and the return type of the method can either be same or different. In this case, they are the same type and the program just execute the functions represented by the lambda expression, an addition and a multiplication, so the output is:
````
15
500
````

###Supplier
This functional interface does the opposite of the *Consumer*, it takes no arguments but it returns some value. It may return different values when it is being called more than once. Since it's a functional interface, you can pass a lambda expression wherever a *Supplier* is expected.

See the [API](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html) to know the one method of this interface.

Here's an example:
````java
public class Program {
    public static void main(String[] args) {
        int n = 3;
	    display(() -> n + 10);
	    display(() -> n + 100);
    }
    
    static void display(Supplier<Integer> arg) {
	    System.out.println(arg.get());
    }
}
````
Basically, a *Supplier* just provides values. The output of the example is:
````
13
103
````

#Develop code that uses primitive versions of functional interfaces
Due to the way generics are implemented, parameters of the functional interfaces (for example, *Predicate<T>*) can be bound only to reference types (like *String*, objects, etc).

If you want to use primitive types with these functional interfaces, Java uses a mechanism called autoboxing to automatically convert a primitive to its corresponding wrapper type (for example, int to *Integer*) and vice versa.

But since boxed values use more memory, this comes with a performance cost. For this reason, Java provides specialized versions of the functional interfaces to avoid autoboxing operations when the inputs or outputs are primitives.

For example, instead of using
````java
Predicate<Integer> p = i -> i > 10;
````
You can use
````java
IntPredicate p = i -> i > 10;
````

In general, the names of functional interfaces that have a primitive version for the input parameter are preceded by the primitive type, like *IntPredicate*.
The *Function* interface also has variants for the output parameter like *ToIntFunction&lt;T&gt;*.

Here's a summary of the primitive version of functional interfaces with a link to their javadoc:

**Predicate&lt;T&gt;**  
[IntPredicate](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntPredicate.html). Predicate of one int-valued argument.  
[LongPredicate](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongPredicate.html). Predicate of one long-valued argument.  
[DoublePredicate](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoublePredicate.html). Predicate of one double-valued argument.

**Consumer&lt;T&gt;**  
[IntConsumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntConsumer.html). Operation that accepts a single int-valued argument and returns no result.  
[LongConsumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongConsumer.html). Operation that accepts a single long-valued argument and returns no result.  
[DoubleConsumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleConsumer.html). Operation that accepts a single double-valued argument and returns no result.

**Function&lt;T, R&gt;**  
[IntFunction&lt;R&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntFunction.html). Function that accepts an int-valued argument and produces a result.  
[IntToDoubleFunction](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntToDoubleFunction.html). Function that accepts an int-valued argument and produces a double-valued result.  
[IntToLongFunction](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntToLongFunction.html). Function that accepts an int-valued argument and produces a long-valued result.  
[LongFunction&lt;R&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongFunction.html). Function that accepts a long-valued argument and produces a result.  
[LongToDoubleFunction](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongToDoubleFunction.html). Function that accepts a long-valued argument and produces a double-valued result.  
[LongToIntFunction](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongToIntFunction.html). Function that accepts a long-valued argument and produces an int-valued result.  
[DoubleFunction&lt;R&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleFunction.html). Function that accepts a double-valued argument and produces a result.  
[ToIntFunction&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToIntFunction.html). Function that produces an int-valued result.  
[ToDoubleFunction&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToDoubleFunction.html). Function that produces a double-valued result.  
[ToLongFunction&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToLongFunction.html). Function that produces a long-valued result.

**Supplier&lt;T&gt;**  
[BooleanSupplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/BooleanSupplier.html). Supplier of boolean-valued results.  
[IntSupplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntSupplier.html). Supplier of int-valued results.  
[LongSupplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongSupplier.html). Supplier of long-valued results.  
[DoubleSupplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleSupplier.html). Supplier of double-valued results.

**UnaryOperator&lt;T&gt;**  
[IntUnaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntUnaryOperator.html). Function operation on a single int-valued operand that produces an int-valued result.  
[LongUnaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongUnaryOperator.html). Function operation on a single long-valued operand that produces a long-valued result.  
[DoubleUnaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleUnaryOperator.html). Function operation on a single double-valued operand that produces a double-valued result.

#Develop code that uses binary versions of functional interfaces

The following functional interfaces:
* Predicate&lt;T&gt;
* Consumer&lt;T&gt;
* Function&lt;T,R&gt;
* UnaryOperator&lt;T&gt;

Represent an operation that takes one argument. But there are versions of these interfaces that take two arguments called. These are the binary versions. They have the same semantics, the only difference is the number of arguments. Note there is no binary version of *Supplier*. This is because a *Supplier* takes no arguments.

Here's a summary of the binary versions of the functional interfaces along with their primitive versions and a link to their javadoc:

**BiPredicate&lt;L, R&gt;**  
(No primitive versions)

**BiConsumer&lt;T, U&gt;**  
[ObjIntConsumer&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ObjIntConsumer.html). Operation that accepts an Object-valued and an int-valued argument and returns no result.  
[ObjLongConsumer&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ObjLongConsumer.html). Operation that accepts an Object-valued and a long-valued argument and returns no result.  
[ObjDoubleConsumer&lt;T&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ObjDoubleConsumer.html). Operation that accepts an Object-valued and a double-valued argument and returns no result.

**BiFunction&lt;T, U, R&gt;**  
[ToIntBiFunction&lt;T, U&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToIntBiFunction.html). Function that accepts two arguments and produces an int-valued result.  
[ToLongBiFunction&lt;T, U&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToLongBiFunction.html). Function that accepts two arguments and produces a long-valued result.  
[ToDoubleBiFunction&lt;T, U&gt;](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToDoubleBiFunction.html). Function that accepts two arguments and produces a double-valued result.

**BinaryOperator&lt;T&gt;**  
[IntBinaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntBinaryOperator.html). Function operation upon two int-valued operands and producing an int-valued result.  
[LongBinaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongBinaryOperator.html). Function operation upon two long-valued operands and producing a long-valued result.  
[DoubleBinaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleBinaryOperator.html). Function operation upon two double-valued operands and producing a double-valued result.


#Develop code that uses the UnaryOperator interface
*UnaryOperator* is a functional interface that receives a value of a certain type and returns a value of the same type. This is a specialization of the *Function* interface for the case where the operand and result are of the same type (in fact *UnaryOperator* extends from *Function*).

Here's the [javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/function/UnaryOperator.html).

And here's an example:
````java
public class Test {
  public static void main(String[] args) {

  	UnaryOperator<Integer> unary = v -> v * 10;
	  // This means the same as the UnaryOperator above.
  	Function<Integer, Integer> function = v -> v * 10;
  
  	System.out.println(unary.apply(10));
  	System.out.println(function.apply(10));
  }
}
````
The output:
````
100
100
````

The UnaryOperator can also be applied to a collection like this:
````java
public class Program {
  public static void main(String[] args) {
  	List<Integer> list = new ArrayList<>();
  	list.add(1);
  	list.add(2);
  	list.add(3);
  	list.replaceAll(i -> i * 10);
  	// ... Display the results.
  	System.out.println(list);
  }
}
````
The output:
````
[10, 20, 30]
````
#Develop code to extract data from an object using peek() and map() methods including primitive versions of the map() method
###peek()
````java
Stream<T> peek(Consumer<? super T> action)
````
Peek is an intermediate operation that returns a stream consisting of the elements of the original stream, and performing the provided action on each element.

According to the [API](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#peek-java.util.function.Consumer-), This method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline:
````java
Stream.of("the", "good", "bad", "ugly")
   .filter(e -> e.length() > 3)
   .peek(e -> System.out.println("Filtered value: " + e))
   .map(String::toUpperCase)
   .peek(e -> System.out.println("Mapped value: " + e))
   .collect(Collectors.toList());
````

###map()
````java
<R> Stream<R> map(Function<? super T,? extends R> mapper)
````
Map is an intermediate operation that returns a stream consisting of the results of applying the given function to the elements of this stream. Since we're talking about a *Function*, it's possible to convert the items in the stream to other objects. In other words, for each item you create a new object based on that item.

For example, to convert all strings in the stream to uppercase:
````java
Stream.of("a", "b", "c", "d").map(s -> s.toUpperCase()).forEach(s -> System.out.print(s));
````

Just like a *Function*, map() include primitive versions:
````java
IntStream mapToInt(ToIntFunction<? super T> mapper)
LongStream mapToLong(ToLongFunction<? super T> mapper)
DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
````
These methods can be used when you want a stream of these primitive types. For example, consider a stream of elements of this type:
````java
class Employee {
  private int id;
  private String name;
  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
}
````
We can extract the employee ids directly to a stream of `int` in this way:
````java
List<Integer> employeeIds = employees.stream().mapToInt(e -> e.getId()).collect(Collectors.toList());
````
This works because `getInt()` returns an `int`, the type expected by `mapToInt()`.


#Search for data by using search methods of the Stream classes including findFirst, findAny, anyMatch, allMatch, noneMatch
We have two types of methods in the Stream API to search for data:
* findXXX methods. Take no arguments and return an Optional object with the result, or an empty Optional if nothing is found.
* XXXMatch methods. Take a Predicate and return a boolean if an element in the stream returns true by applying the Predicate.

The methods are:

`Optional<T>	findAny()`
This is a short-circuiting terminal operation that returns an Optional representing some element of the stream. This doesn't guarantee to return the first element of a sorted stream.

`Optional<T>	findFirst()`
This is a short-circuiting terminal operation that returns the first element of the stream. If the stream has no order, then any element may be returned.

`boolean anyMatch(Predicate<? super T> predicate)`
This is a short-circuiting terminal operation that returns whether any elements of this stream match the provided predicate.  If the stream is empty, then false is returned and the predicate is not evaluated.

`boolean allMatch(Predicate<? super T> predicate)`
This is a short-circuiting terminal operation that returns whether all elements of this stream match the provided predicate.  If the stream is empty, then true is returned and the predicate is not evaluated.

`boolean noneMatch(Predicate<? super T> predicate)`
This is a short-circuiting terminal operation that returns whether no elements of this stream match the provided predicate.  If the stream is empty, then true is returned and the predicate is not evaluated.

A short-circuiting terminal operation basically means that there's no need to process the entire stream to return a result. As soon as an element that fits the predicate is found or that a result can be inferred, the result is returned.

Here's an example:
````java
Optional<Integer> first = Stream.of(1, 10, 5, 3, 13, 20).filter(i -> i % 2 == 0).findFirst(); //returns 10
Optional<Integer> any = Stream.of(1, 10, 5, 3, 13, 20).filter(i -> i % 2 == 0).findAny(); //return 10 or 20
boolean any2 = Stream.of(1, 10, 5, 3, 13, 20).anyMatch(i -> i % 3 == 0); //returns true
boolean all = Stream.of(1, 10, 5, 3, 13, 20).allMatch(i -> i % 2 == 0); //returns false
boolean none = Stream.of(1, 10, 5, 3, 13, 20).noneMatch(i -> i % 6 == 0); //returns true
````

#Develop code that uses the Optional class

The [java.util.Optional<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html) class acts as a container which may or may not contain a non-null value. 

The main methods of the class are:
* `isPresent()` that returns true if it contains a non-null value, false otherwise.
* `get()` that returns the non-null value if it contains a non-null value, and throws a NoSuchElementException otherwise. To avoid the exception, before getting the value you must check if the Optional object contains a non-null value.
* `ifPresent(Consumer<? super T> action)` takes a Consumer to perform some operation on the value contained in the Optional. If the Optional is empty or null, it does not perform anything.

OptionalInt, OptionalLong, and OptionalDouble deal with optional primitive values.
* `getAsInt()` method from OptionalInt class returns int value.
* `getAsLong()` method from OptionalLong class return long value.
* `getAsDouble()` method from OptionalDouble class return double value.

Some stream operations (like `map()`, `max()`, `min()`, etc.)  return optional values with nothing inside if the stream is empty. For example:
````java
OptionalInt min = Stream.of(10, 20, 30, 40).filter(n -> n > 50).min();
if (min.isPresent()) {
  System.out.println(min.getAsInt());
} else {
  System.out.println("No value");
}
````
Or if we don't need to print the "No value" message:
````java
OptionalInt min = Stream.of(10, 20, 30, 40).filter(n -> n > 50).min();
min.isPresent(n -> System.out.println(n));
````

Besides `get()`, we have three other methods to obtain values from an Optional:
* `T orElse(T defaultValue)` Returns the value contained in the Optional. If empty, it returns the specified defaultValue.
* `T orElseGet(Supplier<? extends T> defaultSupplier)` Returns the value contained in the Optional. If empty, it returns the value returned from the specified defaultSupplier.
* `<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X extends Throwable` Returns the value contained in the Optional. If empty, it throws the exception returned from the specified exceptionSupplier.
For example:
````java
Stream.of(10, 20, 30, 40).filter(n -> n > 50).min().orElse(0);
Stream.of(10, 20, 30, 40).filter(n -> n > 50).min().orElseGet(() -> logAndReturnDefault());
Stream.of(10, 20, 30, 40).filter(n -> n > 50).min().orElseThrow(Exception::new);
````

If we are not using streams and we need to create an Optional, there are three methods we can use:
* `<T> Optional<T> empty()` Returns an empty Optional.
* `<T> Optional<T> of(T value)` Returns an Optional containing the specified value  If the value is null, it throws a NullPointerException.
* `<T> Optional<T> ofNullable(T value)` Returns an Optional containing the specified value if the value is non-null. If the specified value is null, it returns an empty Optional.

For example:
````java
Optional<String> empty  = Optional.empty();
Optional<String> string = Optional.of("Hello");
Optional<String> empty2  = Optional.ofNullable(null);
````

#Develop code that uses Stream data methods and calculation methods
###Data methods
````Optional<T>	max(Comparator<? super T> comparator)````
Terminal operation that returns the maximum element of this stream according to the provided Comparator wrapped in an Optional object, or an empty Optional if the stream is empty.

````Optional<T>	min(Comparator<? super T> comparator)````
Terminal operation that returns the minimum element of this stream according to the provided Comparator wrapped in an Optional object, or an empty Optional if the stream is empty.

````long count()````
Terminal operation that returns the count of elements in this stream.

Comparator is a functional interface, so you can create a comparator in the form of a lambda expression. For example:
````java
Comparator<String> byLength = (s1, s2) -> Integer.compare( s1.length(), s2.length());
Optional<String> max = Stream.of("hello","good bye", "black", "white", "good", "bad")
        .max(byLength); //returns "good bye"
Optional<String> min = Stream.of("hello","good bye", "black", "white", "good", "bad")
        .min(byLength); //returns "bad"
long count = Stream.of("hello","good bye", "black", "white", "good", "bad")
        .count(); //returns 6
````

###Calculation methods
Calculation methods only work with the primitive versions of Stream: IntStream, LongStream, and DoubleStream. For example, for IntStream the methods are:

`int sum()`
Terminal operation that returns the sum of elements in the stream.

`OptionalInt min()`
Terminal operation that returns an OptionalInt describing the minimum element of the stream, or an empty optional if this stream is empty.

`OptionalInt max()`
Terminal operation that returns an OptionalInt describing the minimum element of the stream, or an empty optional if this stream is empty.

`OptionalDouble average()`
Terminal operation that returns an OptionalDouble describing the arithmetic mean of elements of the stream, or an empty optional if this stream is empty.

Except for `average()` that always returns an OptionalDouble, the other methods return a long and OptionalLong for LongStream, and a double and OptionalDouble for DoubleStream.

Here's an example:
````java
OptionalInt max = IntStream.of(1, 34, 667, 3, 32, 23).max(); // max() returns 667

OptionalInt min = IntStream.of(1, 34, 667, 3, 32, 23).min(); // min() returns 1

OptionalDouble average = IntStream.of(1, 34, 667, 3, 32, 23).average(); // returns 126.66

int sum = IntStream.of(1, 34, 667, 3, 32, 23).sum(); // returns 760
````
#Sort a collection using Stream API

To sort a collection using the method `sorted()` from the Stream API, you need a Comparator (there's a version of sorted that takes no arguments that returns a stream with its elements sorted according to natural order).

Comparator is a functional interface, so you can create a comparator in the form of a lambda expression.  For example:
````java
Comparator<String> byLength = (s1, s2) -> Integer.compare( s1.length(), s2.length());

Stream.of("hello","good bye", "black", "white", "good", "bad")
          .sorted(byLength)
          .forEach(s -> System.out.println(s)); 
````
The output:
````
bad
good
hello
black
white
good bye
````

If you want to compare using multiple criteria, in Java 8 there's a new method to make this easily,  `Comparator.thenComparing()`:
````java
Comparator<String> byLength = (s1, s2) -> Integer.compare( s1.length(), s2.length());
Comparator<String> byLetters = (s1, s2) -> s1.compareTo(s2);

Stream.of("hello","good bye", "black", "white", "good", "bad")
          .sorted(byLength.thenComparing(byLetters))
          .forEach(s -> System.out.println(s)); 
````
The output:
````
bad
good
black
hello
white
good bye
````
#Save results to a collection using the collect method and group/partition data using the Collectors class
Streams have the following method:
````java
collect(Collector<? super T,A,R> collector)
````
This method performs a mutable reduction operation on the elements of this stream using a [Collector](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html).

A mutable reduction operation accumulates (or collects) input elements into a mutable result container, such as a Collection or StringBuilder, as it processes the elements in the stream.

A collect operation requires three functions: 
* A supplier function to construct new instances of the result container
* An accumulator function to incorporate an input element into a result container
* A combining function to merge the contents of one result container into another.

In fact, streams also have the following method that takes the above functions to create our own collect operations:
````java
collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
````
For example, traditionally, to collect the elements of a list into another list, we could do:
````java
List<String> word = new ArrayList<>();;
List<String> letters = new ArrayList<>();
letters.add("H");
letters.add("e");
letters.add("l");
letters.add("l");
letters.add("o");
for (String s : letters) {
   word.add(s.toUpperCase());
}
````
Now with the collect method:
````java
List<String> letters = new ArrayList<>();
letters.add("H");
letters.add("e");
letters.add("l");
letters.add("l");
letters.add("o");
List<String> word = letters.stream().collect(() -> new ArrayList<>(),
                                    (c, s) -> c.add(s.toUpperCase()),
                                    (c1, c2) -> c1.addAll(c2));
````

However, there's a class, [Collectors](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html), which implements many useful reduction operations. So using Collectors, the code becomes simpler:
````java
List<String> letters = new ArrayList<>();
letters.add("H");
letters.add("e");
letters.add("l");
letters.add("l");
letters.add("o");
List<String> word = letters.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
````

Other implementations of Collectors are:
````java
// Accumulate into a TreeSet
Set<String> set = letters.stream()
                          .map(s -> s.toUpperCase())
                          .collect(Collectors.toCollection(TreeSet::new));

// Convert elements to strings and concatenate them, separated by commas
String joined = letters.stream()
                      .map(s -> s.toUpperCase())
                      .collect(Collectors.joining(", "));

// Compute sum of all letters
int total = letters.stream()
                      .collect(Collectors.summingInt(s.length())));

// Group by starting letter
Map<String, List<String>> grouped = letters.stream()
                                    .collect(Collectors.groupingBy(s.substring(0,1)));

// Partition letters into uppercase and lowercase
Map<Boolean, List<String>> upperLower = letters.stream()
                                    .collect(Collectors.partitioningBy(s -> Character.isUpperCase(s.codePointAt(0))));

````
The last method, partitionBy, takes a predicate that returns `Map<Boolean, List <String>>` where the key is a boolean and the results are based on the behavior of the predicate. So its output is:
````
{false=["e","l","l","o"], true=[H]}
````

#Use of merge() and flatMap() methods of the Stream API
````java
<R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)
````
This method returns a stream consisting of the results of replacing each element of the original stream with the content of the stream produced by the provided function (if the function returns null, an empty stream is used, instead).

This method is commonly used in one&dash;to&dash;many relationships to flatten all the elements into a new stream.

For example, consider the following classes:
````java
class Student {
  private int id;
  private String name;
  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
}
class Course {
  private List<Student> students;
  private String name;
  public List<Student> getStudents() {
    return students;
  }
  public String getName() {
    return name;
  }
  public void setStudents(List<Student> students) {
    this.students = students;
  }
  public void setName(String name) {
    this.name = name;
  }
}
````
Assuming there's a list of courses and each course has a list of students that take that course, if we want to aggregate the names of all students taking a course into one stream, the following code will do it:
````java
List<String> students = courses.stream()
                .flatMap(course -> course.getStudents().stream()) //Get the students of each course
                .map(student -> student.getName()) //Now that we have a stream with all the students, we extract their name
                .collect(Collectors.toList());
````

There are other examples in the [javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-) of the method.

#Use try-catch and throw statements
A try block is used to enclose code that might throw an exception and it can be followed by one or many catch block. 

A catch block is used to handle an exception. It defines the type of the exception and a reference. For example:
````java
try {  
// Code that may throw an exception  
} catch(Exception e){
 // Do something with the exception using reference e
} 
````

If exception is not handled, the Java Virtual Machine provides a default exception handler that performs the following tasks:

1. Prints out exception description.
2. Prints the stack trace (Hierarchy of methods where the exception occurred).
3. Causes the program to terminate.

But if an exception is handled by in a try-catch block, the normal flow of the application is maintained and rest of the code is executed.

If you want to manually throw an exception, use the throw keyword:
````java
public class Test {  
   public static boolean validateName(String name) {
      boolean valid = true;
      try {
        if(name != null) {
         throw new Exception("The name is not valid");
        }
      } catch(Exception e) {
        valid = false;
      }
      
      return valid;
   }  
   public static void main(String args[]) {  
      if(validateName(null)) {
        System.out.println("Valid Name");
      } else {
        System.out.println("Invalid Name");
      }
  }  
} 
````
The output is:
````
Invalid name
````

#Use catch, multi-catch, and finally clauses
###Catch and multi-catch
If you're handling multiple exceptions, the catch blocks must be ordered from most specific to most general, for example, the catch for IndexOutOfBoundsException must come before the catch for Exception, otherwise, a compile-time error is generated:
````java
try {  
  int arr[] = new int[5];  
  arr[10] = 20;  
}  
catch(ArrayIndexOutOfBoundsException e) {
  e.printStackTrace();
}  
catch(ArithmeticException e) {
  e.printStackTrace();
}  
````

The problem with this example is that it contains duplicate code in each of the catch blocks. For this situation, since Java 7, we can use a multi-catch block:
````java
try {  
  int arr[] = new int[5];  
  arr[10] = 20;  
  int r = arr[10]/10;
}  
catch(ArrayIndexOutOfBoundsException|ArithmeticException ex) {
  ex.printStackTrace();
}  
````
The multi-catch clause specifies the types of exceptions that the block can handle, and each exception type is separated with a vertical bar (|). In this case, the catch parameter is implicitly final. So in the example, the catch parameter ex is final and therefore you cannot assign any values to it within the catch block.

A restriction when using the multi-catch clause is that you can't use related exceptions (subclasses) in the same clause because it is redundant, the exception would already be caught by an alternative. For example, the following is invalid, since ArrayIndexOutOfBoundsException is a subclass of Exception:
````java
try {  
  int arr[] = new int[5];  
  arr[10] = 20;  
  int r = arr[10]/10;
}  
catch(ArrayIndexOutOfBoundsException|Exception ex) { // compile-time error
  ex.printStackTrace();
} 
````

###Finally
A finally block is always executed whether an exception is handled or not. It's an optional block, and if there's one, it goes after a try or catch block. This means the following blocks are both valid:
````java
try {
  // code that might throw an exception 
} catch(Exception e) {
  // handle exception
} finally {
  // code that it's executed no matter what
}

try {
  // code that might throw an exception 
} finally {
  // code that it's executed no matter what
}
````

So for each try block there can be zero or more catch blocks, but only one finally block.

The only situation where a finally block will not be executed, it's if the program exits abruptly (either by calling `System.exit()` or by a fatal error that causes the process to abort). Finally gets called even in the following case, before the method returns:
````java
void m() {
  try {  
      throws Exception();  
  }  
  catch (Exception e) {   
      return failure;  
  }  
  finally {  
      System.out.println("Of course this is printed");
  }
}
````

#Use Autoclose resources with a try-with-resources statement

Normally, a finally block is used to ensure that a resource is closed whether the try statement completes normally or not. For example:
````java
BufferedReader br = new BufferedReader(new FileReader(path));
try {
    return br.readLine();
} catch(IOException e) {
    e.printStackTrace();
} finally {
    if (br != null) br.close();
}
```` 

However, Java 7 introduced the try-with-resources statement, which ensures that each resource is closed at the end of the statement. Any object that implements `java.lang.AutoCloseable`, which includes all objects which implement `java.io.Closeable`, can be used as a resource. The above example can be rewritten with a try-with-resources statement like this:
````java
try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
````

But there's a subtle difference between these two try blocks. If the methods `readLine()` and `close()` both throw exceptions, then the method in the first example throws the exception thrown from the finally block (from `close()`) and the exception thrown from the try block (from `readLine()`) is suppressed. In contrast, in the try-with-resources example, if exceptions are thrown from both the try block (from `readLine()`) and the try-with-resources statement (from `close()`), then the exception from the try block is thrown and the exception thrown from the try-with-resources block is suppressed. You can retrieve these suppressed exceptions by calling the `Throwable.getSuppressed()` method from the exception thrown by the try block.

The [Closeable](https://docs.oracle.com/javase/8/docs/api/java/io/Closeable.html) interface extends the [AutoCloseable](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html) interface. The `close()` method of the Closeable interface throws exceptions of type IOException while the close method of the AutoCloseable interface throws exceptions of type Exception.

You may declare more than one resources in a try-with-resources statement, separated by a semicolon. When the try block   terminates, either normally or because of an exception, the `close()` methods of the resources are automatically called in the opposite order of their creation. For example:
````java
// At the end, br2 is closed before br1.
try (BufferedReader br1 = new BufferedReader(new FileReader(path1)); 
      BufferedReader br2 = new BufferedReader(new FileReader(path2))) {
        String l1 =  br1.readLine();
        String l2 =  br2.readLine();
    }
````

An important note, in a try-with-resources statement, any catch or finally blocks are run after the resources declared have been closed.

#Create custom exceptions and Auto-closeable resources
###Custom exceptions
We can create our own exceptions by extending the [java.lang.Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html) class. For example:
````java
class MyException extends Exception {
  public MyException(String message) {
    super(message);
  }
}
public class Test {
  public static void main(String args[]) {
    try{
            throw new MyException("My exception...");
        } catch(MyException e){
            e.printStackTrace();
        }
  }
}
````
You can also extend from [RuntimeException](https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html), to make your exception unchecked (so it doesn't have to be catched if you don't want to).

###Auto-closeable resources
You can implement the [java.lang.AutoCloseable](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html) or [java.lang.Closeable](https://docs.oracle.com/javase/8/docs/api/java/io/Closeable.html) interfaces in your own classes and use them with a try-with-resources block. However, the close method of the *Closeable* interface throws exceptions of type *IOException* while the close method of the *AutoCloseable* interface throws exceptions of type Exception. So it's better to implement *AutoCloseable*, since you can override this behavior and throw other exceptions or no exception at all.

*AutoClosable* only has one method called close():
````java
public interface AutoClosable {
    public void close() throws Exception;
}
````
So an implementation would look like this:
````java
public class MyAutoClosable implements AutoCloseable {
    public void someMethod() {
       System.out.println("Doing something");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closed!");
    }
}
````
And this is how you'd use it:
````java
try(MyAutoClosable ac = new MyAutoClosable()){
    ac.someMethod();
}
````
The output:
````
Doing something
Closed!
````

#Test invariants by using assertions

Assertions are statements that you can use to test your assumptions about the code during development. If the assertion turns out to be false, then an AssertionError is thrown. You can use assertions in two forms:
````java
private method(int i) {
  assert i > 0;
  //or
  assert i > 0 : "Parameter i must be a positive value"
  
  // Do something now that we know i is greater than 0
}
````
In the first form, the assert expression must evaluate to a boolean value. The other version adds a second
expression separated from the first boolean expression by a colon. This expression would be used when the assertion is false in addition to throwing AssertionError. This second expression must resolve to a value, otherwise, a compile-time error is generated.

To enable assertions, you have to compile the code with the assertion enabled. Then, when running the program, assertions have to be enabled again.

Assertions were added to Java since version 1.4, so before that version, you can use assert as an identifier. This is important because in Java 7, assertions are compiled by default, but you can override this behavior at the command line:
````
javac -source 1.3 Test.java
````
The command above will issue warnings when  the word assert is used as an identifier, but the code will compile and execute. But if you use any of the following commands:
````
javac -source 1.4 Test.java
javac -source 1.5 Test.java
javac -source 5 Test.java
javac -source 1.6 Test.java
javac -source 6 Test.java
javac -source 1.7 Test.java
javac -source 7 Test.java
javac Test.java
````
The compilation will fail if assert is used as an identifier.

So compilation is the first step to using assertions. But to execute the assertion checks, you have to enable them with:
````
java -ea com.example.Test
````
or
````
java -enableassertions com.example.Test
````

The default behavior is to run the code with assertions disabled, but the commands to explicitly disabling assertions are:
````
java -da com.example.Test
````
or
````
java -disableassertions com.example.Test
````
However, this command is useful in the case you want to enable assertions for some classes or packages only, for example:
````
java -ea -da:com.example.PrintUtil com.example.Test
````
To disable assertions only for the com.example.Test class. Or:
````
java -ea -da:com.example.util... com.example.Test
````
To disable assertions for the com.example.util package and all of its subpackages.

But no all uses of assertions are considered appropriate. Here are the rules:
* Don't use assertions to validate arguments to a public method
* Use assertions to validate arguments to a private method
* Don't use assertions to validate command-line arguments
* Use assertions, even in public methods, to check for cases that are never supposed to happen
* Don't use assert expressions that can cause side effects. For example:

  ````java
  private void m(List<Integer> l) {
    assert checkSize(l);
  }
  private boolean checkSize(List<Integer> l) {
    l.add(10);
    return l.size() > 3;
  }
  ````
#Create and manage date-based and time-based events including a combination of date and time into a single object using LocalDate, LocalTime, LocalDateTime, Instant, Period, and Duration

The new Date/Time API was designed with the following principles:
* Immutable classes
* Dates and times are separated 
* Support for different calendaring systems

###LocalDate, LocalTime and LocalDateTime
The first classes to learn when using the new API are [LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), [LocalTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html) and [LocalDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html), which is a pairing of the former classes:
````java
LocalTime time = LocalTime.now();
LocalDate date = LocalDate.now();
LocalDateTime dt = LocalDateTime.of( date, time ); // You can also call LocalDateTime.now();
````
They are local in the sense that they represent date and time from the context of one observer, in contrast to time zones.

All the core classes in the new API are constructed by factory methods. When constructing a value through its fields, the factory is called *of*. When converting from another type, the factory is called *from*. There are also parse methods that take strings as parameters:
````java
LocalDate ld1 = LocalDate.of(2014, Month.SEPTEMBER, 19);
LocalDate ld2 = LocalDate.ofEpochDay(1000);
LocalTime lt1 = LocalTime.of(14, 05);
LocalTime lt2 = LocalTime.parse("14:05:00");
````

You can use standard getters to obtain values from the classes:
````java
int m = LocalDate.now().getDayOfMonth();
int y = LocalDate.now().getYear();
int h = LocalTime.now().getHour();
int min = LocalTime.now().getMinute();
````

You can also modify the object values by using the *with* methods instead of using setters (because the classes are immutable, which also means that you don't actually modify a class, a new one is returned):
````java
LocalDateTime thePast = dt.withDayOfYear(90).withHour(21);
````

There are other methods to perform calculations:
````java
LocalDateTime dt = dt.plusMinutes(10).plus(2, ChronoUnit.DAYS);
LocalDateTime dt = dt.minusYears(10);
````

###Instant
An instant is a point of time counting from the first second of 1/1/1970, also known as epoch. Instants after the epoch have positive values, and earlier instants have negative values and a larger value is always later on the timeline than a smaller value.

The range of an instant requires the storage of a number larger than a long. To achieve this, the class stores a long representing epoch-seconds and an int representing nanosecond-of-second, which will always be between 0 and 999,999,999.

An Instant can be created in a similar way as a date or a time:
````java
Instant now = Instant.now();
Instant epochNow = Instant.ofEpochSecond(1000000);
````
It also has get, plus and minus methods:
````java
long s = Instant.now().getEpochSecond();
int n = Instant.now().getNano();
Instant twoSecondsAfter = now.plusSeconds(2);
Instant twoSecondsBefore = now.minusSeconds(2);
````

###Period
A period represents a value such as "1 months and 15 days", in contrast to the other classes that represent a point in time.

Here are some ways to create a period:
````java
Period period = Period.of(1, 2, 3); // 1 year, 2 months, 3 days
Period periodTwoMonths = Period.ofMonths(2);
Period period20142015 = Period.between(LocalDate.of( 2014, Month.JANUARY, 1), LocalDate.of( 2015, Month.JANUARY, 1));
````
It also has get, plus and minus methods:
````java
int y = period.getYears();
Period newPeriod = period.plusDays(2).minusMonths(1);
````

You can modify the values of dates using periods:
````java
LocalDate newDate = date.plus(period);
````

###Duration
A duration is similar to a period but its precision is based on hours, minutes, seconds, milliseconds. 

A duration can be created using an amount of seconds or minutes or hours or by specifying start and end times:
````java
Duration d2 = Duration.ofSeconds(10, 50); // 10 seconds and 50 nanoseconds
Duration d2 = Duration.between(LocalTime.NOON, LocalTime.MIDNIGHT);
````

It's also possible to perform normal plus, minus, and with operations on durations and also like periods, modify the value of a date or time using a duration.

#Work with dates and times across time zones and manage changes resulting from daylight savings including Format date and times values
###Timezones
Time zones are defined by their offset from Coordinated Universal Time (UTC) and correspond to a region in which the time is the same.

Time zones can be referred to by two identifiers: abbreviated, for example, "PLT" and longer, for example, "Europe/Paris".
[ZoneId](https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html) is an identifier for a region:
````java
ZoneId id = ZoneId.of("Europe/Paris");
````
[ZoneOffset](https://docs.oracle.com/javase/8/docs/api/java/time/ZoneOffset.html) is the period of time representing a difference between Greenwich/UTC and a time zone.
````java
ZoneOffset offset = ZoneOffset.of("-06:00");
````
[ZonedDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html) is a date and time with a fully qualified time zone. You should use this class if you want to represent a date and time without relying on the context of a specific server.  
````java
ZonedDateTime zdt1 = ZonedDateTime.parse("2014-12-03T10:15:300[Europe/Paris]");//2014-12-03T10:15:300Z[Europe/Dublin]
ZonedDateTime zdt2 = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
````
[OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html) is an immutable representation of a date-time with an offset from UTC/Greenwich, for example '2000-12-31T10:15:30+01:00'.

OffsetDateTime, ZonedDateTime, and Instant all store an instant on the timeline to a nanosecond precision. Instant is the simplest, just representing the instant. OffsetDateTime adds to the instant the offset from UTC/Greenwich, which allows the local date-time to be obtained. ZonedDateTime adds full timezone data.

[OffsetTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetTime.html) is a time with an offset from UTC/Greenwich in the ISO-8601 calendar system, such as '08:45:21+05:00'.
````java
OffsetDateTime odt = OffsetDateTime.of(LocaDateTime.now(),ZoneOffset.of("-4")); //2015-05-22T23:42:20.101-06:00
OffsetTime ot = OffsetTime.ofInstant(Intant.now(),ZoneId.of("America/Los_Angeles")); //22:42:20.101-07:00
````

[Clock](http://docs.oracle.com/javase/8/docs/api/java/time/Clock.html) gives us the ability to get the current date/time from the system clock with a specific timezone:
````java
Clock defaultZone = Clock.systemDefaultZone();
Clock clock = Clock.system(ZoneId.of("Europe/Italy"));
````

###Formatting
[java.time.format.DateTimeFormatter](http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) is the class for printing and parsing date-time objects.

This class works by using:
* Predefined constants, such as ISO_LOCAL_DATE
* Pattern letters, such as yyyy-MMM-dd
* Localized styles, such as long or medium

The date-time classes provide two methods - one for formatting, for example:
````java
LocalDateTime dt = LocalDateTime.of( 2010, Month.JULY, 03, 09, 0, 30 );
String isoDateTime = dt.format(DateTimeFormatter.ISO_DATE_TIME);
````
And one for parsing, for example:
````java
LocalDate dt = LocalDate.parse( "2014/09/19 14:05:12", DateTimeFormatter.ofPattern( "yyyy/MM/dd kk:mm:ss" ) );
````
The last example makes use of a pattern. Patterns are sequences of letters and symbols used to create a Formatter using the `ofPattern(String)` and `ofPattern(String, Locale)` methods.

#Define and create and manage date-based and time-based events using Instant, Period, Duration, and TemporalUnit

[Instant](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html) captures the current moment in time. They are useful for obtaining timestamps to a nanosecond precision.
````java
Instant i = Instants.now();
````

[Period](https://docs.oracle.com/javase/8/docs/api/java/time/Period.html) represents an amount of time in years, months, and/or days. They are useful for adding/subtracting time to/from a date:
````java
LocalDateTime today = LocalDateTime.now();
Period period = Period.ofDays(7);
LocalDateTime nextWeek = today.plus(period);
````

[Duration](https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html) represents an amount of time in hours, minutes, and/or seconds. Same concept as period but with time units:
````java
LocalTime now = LocalDateTime.now();
Duration tenMinutes = Duration.ofMinutes(10);
LocalTime tenMinutesLater = now.plus(tenMinutes);
````

Period and Duration also have a `between()`method to for determining elapsed time between dates (or time) objects:
````java
LocalDate ld1 = LocalDate.parse("2015-05-23");
LocalDate ld2 = LocalDate.parse("2014-05-23");
Period timeBetween = Period.between(ld1,ld2); //Difference in years, months, days
Duration timeSpan = Duration.between(ld1,ld2); //Difference in hours, minutes, seconds
````

[TemporalUnit](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/TemporalUnit.html) is an interface that represents a unit of date-time, like years, months, days, hours, minutes and seconds. It doesn't represent the amount of the unit. Its implementation is the class [ChronoUnit](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoUnit.html), which contains enum constants like:
* NANOS - Unit that represents the concept of a nanosecond
* SECONDS - Unit that represents the concept of a second.
* HALF_DAYS - Unit that represents the concept of half a day.
* MILLENNIA - Unit that represents the concept of a millennium. For the ISO calendar system, it is equal to 1000 years.

And almost any other time unit you can imagine. It has some helpful methods like `between()`. For example, if you need to find the number of years between two dates:
````java
ChronoUnit.YEARS.between(date1, date2):
````

#Read and write data from the console

###Console object
Access to the console depends upon the underlying platform and also upon the way in which the Java program is invoked. If it's started from the command line without redirecting the standard input and output streams, then its console will exist. If it's stated for example by a background job scheduler or an IDE, it's most likely that it will be no access to the console.

If there's a console,  it will be available with the `System.console()` method. If it's not available, an invocation of that method will return null.

Reading a line of text from the console:
````java
Console console = null;
String s = null;
try
{
   console = System.console();
   if (console != null)
   {
      s = console.readLine();
      System.out.println(s);
   }
} catch (Exception ex)
{
   ex.printStackTrace();
}
````
For reading a password or other secure data,`readPassword()` or `readPassword(String, Object...)` (that provides a formatted prompt) and manually zero the returned character array after processing to minimize the lifetime of sensitive data in memory:
````java
Console console = null;
char[] passwd = null;
try
{
   console = System.console();
   if (console != null)
   {
      if((passwd = console.readPassword("[%s]", "Password:")) != null) {
        java.util.Arrays.fill(passwd, ' ');
      }
   }
} catch (Exception ex)
{
   ex.printStackTrace();
}
````
For writing, we have two equivalent methods that accept a [format string](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax):
````java
Console console = System.console();
if (console != null)
{
  console.format("[%s]", "Hi");
  console.printf("[%s]", "Hi again");
  console.format("Or just hi");
}
````

###Standard Streams
Java supports three standard streams: Standard Input, accessed through `System.in`; Standard Output, accessed through `System.out`; and Standard Error, accessed through `System.err`. These objects are defined automatically and do not need to be opened.

To read using streams:
````java
try {
   BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
   String s = bufferRead.readLine();
    
   System.out.println(s);
}
catch(IOException ex)
{
  ex.printStackTrace();
}
 ````
 To write, standard output and standard error are both for output and they are defined as PrintStream objects:
 ````java
System.out.println("Hi");
System.out.print("Hi again\n");
 ````
 
#Use BufferedReader, BufferedWriter, File, FileReader, FileWriter, FileInputStream, FileOutputStream, ObjectOutputStream, ObjectInputStream, and PrintWriter in the java.io package.

[File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)

This class is a representation of a file or a directory pathnames. Its instances are immutable, that is, once created, the abstract pathname represented by the object will never change.

You can create an instance this way:
````java
File file = new File("c:\\file.txt");
````
Or:
````java
File file = new File("/usr/file.txt");
````
Once you have created a File object you can do a lot of things:
````java
// Check if the corresponding file or directory actually exists
boolean fileExists = file.exists();

// Create a single directory if it does not already exist
boolean directoryCreated = file.mkdir();

// Read the length of the file in bytes
long length = file.length();

// Rename or move a file or directory:
boolean renameSuccessful = file.renameTo(new File("c:\\new-file.txt"));

// Delete the file or directory
success = file.delete();

// Check if the File object points to a directory
boolean isDirectory = file.isDirectory();

// Check if the file/directory is hidden
boolean isHidden = file.isHidden();

// Get a list (an array) of all the names of the files/directories in a directory
String[] fileNames = file.list();

// Get a list of all files/directories in a directory as instances of File
File[]   files = file.listFiles();

````

[FileReader](https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html)  
and  
[FileWriter](https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html)

FileReader and FileWriter are character based, they are intended for reading and writing text. 

Here's an example code snippet for FileReader:
````java
Reader reader = new FileReader("c:\\file.txt");
int data = reader.read();
while(data != -1){
    char dataChar = (char) data;
    data = reader.read();
}
reader.close();
````
Here's an example code snippet for FileWriter:
````java
Writer writer = new FileWriter("c:\\file.txt");
writer.write("Hello World Writer");
writer.close();
````
FileReader extends from InputStreamReader, so it can work with an InputStream. At the same time, FileWriter extends from OutputStreamReader, so it can work with an OutputStream.

[FileInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html)

FileInputStream reads the contents of a file as a stream of bytes. It's a subclass of InputStream. It can be created either with a String or a File object that represent a path. Here's an example:
````java
InputStream input = new FileInputStream("c:\\text.txt");
int byteData = input.read();
while(byteData != -1) {
  byteData = input.read();
}
input.close();
````
There's another version of `read()` that reads up to the length of an array of bytes of data from the input stream into that array:
````java
InputStream fis = new FileInputStream("c:\\text.txt");
byte[] data = new byte[1024];
int bytesRead = fis.read(data);
while(bytesRead != -1) {
  bytesRead = fis.read(data);
}
fis.close();
````

[FileOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html)

FileOutputStream writes the contents of a file as a stream of bytes. It's a subclass of OutputStream. It can be created either with a String or a File object that represent a path.

When you create a FileOutputStream representing a file that already exists, you can decide if you want to overwrite or to append to the existing file. It depends on the constructor you choose:
````java
OutputStream fos1 = new FileOutputStream("c:\\text.txt"); //overwrites file
OutputStream fos2 = new FileOutputStream("c:\\text.txt", true); //appends to file
OutputStream fos3 = new FileOutputStream("c:\\text.txt", false); //overwrites file
````

When you write to a FileOutputStream, the data may get cached internally in memory and written to disk at a later time. If you want to make sure that all data is written to disk without having to close the FileOutputStream, you can call its `flush()` method.

````java
OutputStream output = null;
try {
  output = new FileOutputStream("c:\\text.txt");

  while(moreData()) {
    int data = getData();
    output.write(data);
  }
} finally {
    if(output != null) {
        output.close();
    }
}
````
The other versions of the `write()` method are:
`write(byte[] bytes)`. It writes all the bytes in the byte array to the OutputStream.
`write(byte[] bytes, int offset, int length)`. It writes to the OutputStream length number of bytes starting from an offset of the byte array.

[BufferedReader](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html)

BufferedReader provides buffering to a character-input stream. Rather than read one byte at a time, BufferedReader reads a larger block at a time. 
To add buffering to an InputStream just wrap it in a BufferedInputStream:
````java
Reader r = new BufferedReader(new FileReader("c:\\file.txt"));
````
The BufferedReader creates a byte array internally and fills it by calling the `read()` or `readLine()` methods on the underlying Reader. You can set the buffer size to use internally, for example to 1024 bytes, like this:
````java
Reader r = new BufferedReader(new FileReader("c:\\file.txt"), 1024);
````

[BufferedWriter](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html)

BufferedWriter provides buffering to a character-output stream. To add buffering to a Writer just wrap it in a BufferedWriter:
````java
BufferedWriter input = new BufferedWriter(new FileWriter("c:\\file.txt"));
````
To set the size of the buffer, provide it as a constructor parameter like this:
````java
BufferedWriter input = new BufferedWriter(new FileWriter("c:\\file.txt"), 1024);
````
Without buffering, each invocation of a `write()` or `newLine()` method would cause characters to be converted into bytes and written immediately to the file, which can be very inefficient.

[ObjectInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html)

ObjectInputStream allows you to read serialized objects (their class must implement java.io.Serializable) from an InputStream instead of only bytes. You wrap an InputStream in an ObjectInputStream like this:
````java
class Test implements java.io.Serializable {}
...
ObjectInputStream input = new ObjectInputStream(new FileInputStream("obj.data"));
Test object = (Test) input.readObject();
input.close();   
````

[ObjectOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html)

ObjectOutputStream allows you to write serialized objects (their class must implement java.io.Serializable) from an OutputStream instead of only bytes. You wrap an OutputStream in an ObjectOutputStream like this:
````java
class Test implements java.io.Serializable {}
...
ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("obj.data"));
Test object = new Test();
output.writeObject(object);
output.close();  
````

[PrintWriter](https://docs.oracle.com/javase/8/docs/api/java/io/PrinterWriter.html) 

PrintWriter class allows you to write formatted data or data from primitive types instead of bytes to an underlying Writer. Here's an example:
````java
PrintWriter writer = new PrintWriter(new FileWriter("c:\\file.txt") );
writer.print(true);
writer.print(1);
writer.print(1.23);

// printf and format methods do the same
writer.printf("%s", "Hi");
writer.format("%s", "Hi");

writer.close();
````

The constructors of PrintWriter are:

`PrintWriter(File file)`  
Creates a new PrintWriter, without automatic line flushing, with the specified file.

`PrintWriter(File file, String csn)`  
Creates a new PrintWriter, without automatic line flushing, with the specified file and charset.

`PrintWriter(OutputStream out)`  `  
Creates a new PrintWriter, without automatic line flushing, from an existing OutputStream.

`PrintWriter(OutputStream out, boolean autoFlush)`  
Creates a new PrintWriter from an existing OutputStream.

`PrintWriter(String fileName)`  
Creates a new PrintWriter, without automatic line flushing, with the specified file name.

`PrintWriter(String fileName, String csn)`  
Creates a new PrintWriter, without automatic line flushing, with the specified file name and charset.

`PrintWriter(Writer out)`  
Creates a new PrintWriter, without automatic line flushing.

`PrintWriter(Writer out, boolean autoFlush)`  
Creates a new PrintWriter.

#Use Path interface to operate on file and directory paths

The [Path](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html) interface defines an object that represents the path to a file or a directory. 

A Path object has a root component and a hierarchical sequence of names separated by backslashes (in Windows) or slashes (Unix/Linux). The root component identifies the file system being used, for example, a drive letter. The names represent the directories needed to navigate to the target file or directory. The last name in the sequence represents the name of the target file or directory.

To create a Path object you can use the the get method of the Paths class like this:
````java
Path p1 = Paths.get("c:\\file.txt"); // using an absolute path
Path p2 = Paths.get("c:\\data", "examples\\file.txt"); // using a relative path to construct the path c:\data\examples\file.txt
````

When working with relative paths you can use:
* **.** to refer to the current directory
* **..** to refer to the parent directory

For example:
````java
Path p1 = Paths.get("c:\\data\\.\\file.txt"); // refers to c:\data\file.txt
Path p2 = Paths.get("c:\\data\\examples\\..\\file.txt"); // refers to c:\data\file.txt
````

The `normalize()` method of the Path interface can normalize a path, meaning that it removes all the **.** and **..** in the path and resolves to the real path it refers to. 

You can convert a relative path to an absolute path like this:
````java
Path p1 = Paths.get("file.txt");
Path p2 = p1.toAbsolutePath(); // refers for example to c:\data\file.txt
````

You can convert a Path to a File and a File to a Path like this:
````java
File f = Paths.get("file.txt").toFile();
Path p = new File("file.txt").toPath();
````

Path stores the name elements as a sequence. The highest element is located at index 0. The following code snippet shows some methods to get information about the path:
````java
Path path = Paths.get("c:\\users\\admin\\file.txt"); 
Path path = path.getFileName(); // returns file.txt
Path path = path.getName(0); // returns users
int count = path.getNameCount(); // returns 3 (users, admin and file.txt)
Path path = path.subpath(0, 2); // returns users/admin
Path path = path.getParent(); // returns /users/admin
Path path = path.getRoot(); // returns c:\ (if we were in a unix filesystem, it would return /)
````


#Use Files class to check, read, delete, copy, move, manage metadata of a file or directory

The class [Files](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html) contains static methods that operate on files and directories.

###Check files
The `Files.exists()` method checks if a given Path exists in the file system, for example
````java
Path path = Paths.get("data/logging.properties");
boolean exists = Files.exists(path, new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});
````
The LinkOption parameter is used to indicate how symbolic links are handled if the file is a symbolic link. By default, symbolic links are followed. If the option NOFOLLOW_LINKS is present as in the example, then symbolic links are not followed.

There's also:
````java
static boolean	notExists(Path path, LinkOption... options)
````
That tests whether the file located by this path does not exist.

###Read files
The methods for reading a file are:
````java
public static byte[] readAllBytes(Path path) throws IOException
````
Reads all the bytes from a file.

````java
public static List<String> readAllLines(Path path,  Charset cs) throws IOException
````
Read all lines from a file. Bytes from the file are decoded into characters using the specified charset.

````java
public static List<String> readAllLines(Path path)  throws IOException
````
Read all lines from a file. Bytes from the file are decoded into characters using the UTF-8 charset.

All methods ensure that the file is closed when all bytes have been read or an I/O error, or other runtime exception, is thrown. Also, they are not designed for reading large files. Here's an example
````java
Path path = Paths.get("file.txt");
try {
    List<String> lines = Files.readAllLines(path);
} catch (IOException e) {
    // something failed
    e.printStackTrace();
}
````

###Delete files
The `Files.delete()` method can delete a file or directory. Here's an example:
````java
Path path = Paths.get("file.txt");
try {
    Files.delete(path);
} catch (IOException e) {
    // deleting failed
    e.printStackTrace();
}
````
This method will only delete a directory if it is empty.

###Copy files
The `Files.copy()` method copies a file from one path to another. If the destination file already exists, a `java.nio.file.FileAlreadyExistsException` is thrown:
````java
Path source  = Paths.get("file.txt");
Path target = Paths.get("file-copy.txt");

try {
    Files.copy(source, destination);
} catch(FileAlreadyExistsException fae) {
    fae.printStackTrace();
} catch (IOException e) {
    // something else went wrong
    e.printStackTrace();
}
````
If you want to overwrite an existing file use this line:
````java
Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
````

###Move files
`Files.move()` moves a file. Moving a file is the same as renaming it, but moving an both move it to a different directory and change its name in the same operation. If the destination file already exists, a `java.nio.file.FileAlreadyExistsException` is thrown:
````java
Path source  = Paths.get("file.txt");
Path target = Paths.get("file-moved.txt");

try {
    Files.move(source, destination);
} catch(FileAlreadyExistsException fae) {
    fae.printStackTrace();
} catch (IOException e) {
    // something else went wrong
    e.printStackTrace();
}
````
If you want to overwrite an existing file use this line:
````java
Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
````

###Manage metadata
The following methods can be used to extract information about the file or directory:
````java
public static boolean isDirectory(Path path, LinkOption... options)
````
Tests whether a file is a directory.

-----

````java
public static boolean isExecutable(Path path)
````
Tests whether a file is executable.

-----

````java
public static boolean isHidden(Path path)
````
Tells whether or not a file is considered hidden.

-----

````java
public static boolean isReadable(Path path)
````
Tests whether a file is readable.

-----

````java
public static boolean isWritable(Path path)
````
Tests whether a file is writable.

-----

````java
public static long size(Path path)
````
Returns the size of a file (in bytes).

-----

````java
public static A <A extends BasicFileAttributes> readAttributes(Path path, Class<A> type, LinkOption... options)
````
Reads a file's attributes as a bulk operation. The type parameter is the type of the attributes and this method returns an instance of that type if supported. The implementations are:
* [BasicFileAttribute](https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/BasicFileAttributes.html)
* [PosixFileAttributes](https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/PosixFileAttributes.html)
* [DosFileAttributes](https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/DosFileAttributes.html)

The options array may be used to indicate how symbolic links are handled. By default, symbolic links are followed.

For example:
````java
BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
````

-----

````java
public static Map<String,Object> readAttributes(Path path, String attributes, LinkOption... options)
````
Reads a set of file attributes as a bulk operation. The attributes parameter identifies the attributes to be read and takes the form:
````
[view-name:]attribute-list
````
Where `view-name` is the name of a [FileAttributeView](https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/FileAttributeView.html) that identifies a set of file attributes. If not specified, it defaults to "basic". Some possible values are:  
`"*"` - Read all basic-file-attributes.  
`"lastModifiedTime,lastAccessTime"` - Reads the last modified time  and last access time basic attributes.  
`"posix:*"` - Read all POSIX-file-attributes.  
`"posix:permissions,owner"` - Reads the POSX file permissions and owner.

The options parameter may be used to indicate how symbolic links are handled. By default, symbolic links are followed.

-----

````java
public static Object getAttribute(Path path, String attribute, LinkOption... options)throws IOException
````
Reads the value of a file attribute. The attribute parameter identifies the attribute to be read and takes the form:
````
[view-name:]attribute-name
````
The options parameter may be used to indicate how symbolic links are handled. By default, symbolic links are followed.

For example:
````java
int size = (Integer)Files.getAttribute(path, "basic:size");
````

-----

````java
public static Path setAttribute(Path path, String attribute,  Object value,  LinkOption... options)
````
Sets the value of a file attribute. The attribute parameter identifies the attribute to be set and takes the form:
````
[view-name:]attribute-name
````
The options parameter may be used to indicate how symbolic links are handled. By default, symbolic links are followed.

For example:
````java
Files.setAttribute(path, "dos:hidden", false);
````

#Use Stream API with NIO.2

In Java 8, the class [Files](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html) adds methods that allow us to use streams with files. 

###List
````java
public static Stream<Path> list(Path dir)
````
Streams all the elements of a given directory. This method is not recursive. For example:
````java
try (Stream<Path> stream = Files.list(Paths.get("c:\\data"))) {
    stream.map(String::valueOf)
        .filter(path -> path.endsWith(".txt"))
        .forEach(System.out::println);
}
````
The example maps each path to its string representation, filter the results (only files ending in .txt) and print them. Streams implement AutoCloseable, so the try-with-resources block should be used to ensure that the stream's close method is invoked after the stream operations are completed.

###Find and Walk
````java
public static Stream<Path> find(Path start, int maxDepth,  BiPredicate<Path,BasicFileAttributes> matcher, FileVisitOption... options)
````
Search for files starting at the given Path that represents the root of the file tree with the maximum number of directory levels to search given by the parameter maxDepth. For each file encountered, the given BiPredicate is invoked and the file is only included in the returned Stream if the BiPredicate returns true. For example:
````java
try (Stream<Path> stream = Files.find(Paths.get("c:\\data"), 3, 
                                        (path, attr) -> String.valueOf(path).endsWith(".txt"))) {
    stream.map(String::valueOf).forEach(System.out::println);
}
````
The example searches for all txt files and print their path and name.

````java
public static Stream<Path> walk(Path start, int maxDepth,  FileVisitOption... options)
public static Stream<Path> walk(Path start, FileVisitOption... options) // This visits all levels of the directory tree.
````
We can do the same thing but using the walk method and a stream filter:
````java
try (Stream<Path> stream = Files.walk(Paths.get("c:\\data"), 3)) {
    stream.map(String::valueOf).
          filter(path -> path.endsWith(".txt")).
          forEach(System.out::println);
}
````
However, the find method is more efficient for these cases.

###Read lines and BufferedReader
````java
public static Stream<String>	lines(Path path, Charset cs)
public static Stream<String>	lines(Path path) // Uses the UTF-8 charset.
````
This method reads all lines from a file into a stream. For example:
````java
try (Stream<String> stream = Files.lines(Paths.get("c:\\data\\file.txt"))) {
    stream.map(String::toLowerCase).forEach(System.out::println);
}
````

[BufferedReader](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html) also supports stream with its lines method:
````java
try (BufferedReader reader = Files.newBufferedReader(Paths.get("c:\\data\files.txt"))) {
    reader.lines().map(String::toLowerCase).forEach(System.out::println);
}
````

#Create worker threads using Runnable, Callable and use an ExecutorService to concurrently execute tasks

###Runnable
We can create a thread by passing an implementation of [Runnable](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) to a Thread constructor. There are three ways to do it:
````java
class Task implements Runnable {
  @Override
  public void run() {
      System.out.println("Running");
  }
}

public class Test {
  public static void main(String args[]) {
    Runnable r = new Task();
    Thread thread = new Thread(r);
    thread.start();
  }
}

````
Or with an anonymous class:
````java
public class Test {
  public static void main(String args[]) {
    Runnable r = new Runnable() {
      @Override
      public void run() {
          System.out.println("Running");
      }
    };
    Thread thread = new Thread(r);
    thread.start();
  }
}
````
Or with a lambda expression:
````java
public class Test {
  public static void main(String args[]) {
    Runnable r = () -> System.out.println("Running");
    Thread thread = new Thread(r);
    thread.start();
  }
}
````

###Callable
The [Callable](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html) interface is similar to Runnable, they're both designed to be executed by another thread, a Runnable however, does not return a result and cannot throw a checked exception. We can create a Callable in three ways:
````java
class Task implements Callable<Integer> {
  @Override
  public Integer call() {
      int n = 0;
      for(int i = 0; i < 100; i++) { n += i; }
      return n;
  }
}

public class Test {
  public static void main(String args[]) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Callable c = new Task();
    Future<Long> future = executor.submit(c);
    try {
        Long result = future.get(); //waits for the thread to complete
        System.out.println(result);
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
    executor.shutdown();
  }
}

````
Or with an anonymous class:
````java
public class Test {
  public static void main(String args[]) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Callable c = new Callable<Integer>() {
      public Integer call() {
          int n = 0;
          for(int i = 0; i < 100; i++) { n += i; }
          return n;
      }
    };
    Future<Long> future = executor.submit(c);
    try {
        Long result = future.get(); //waits for the thread to complete
        System.out.println(result);
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
    executor.shutdown();
  }
}
````
Or with a lambda expression:
````java
public class Test {
  public static void main(String args[]) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Callable c = () -> {
          int n = 0;
          for(int i = 0; i < 100; i++) { n += i; }
          return n;
      };
    Future<Long> future = executor.submit(c);
    try {
        Long result = future.get(); //waits for the thread to complete
        System.out.println(result);
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
    executor.shutdown();
  }
}
````

###ExecutorService
The [ExecutorService](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html) interface represents a mechanism that executes tasks in the background. 

You can use the [Executors](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html) factory class to create ExecutorService implementations. Some examples are:
````java
// Creates an Executor that uses a single worker thread operating off an unbounded queue.
ExecutorService es1 = Executors.newSingleThreadExecutor();
// Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.
ExecutorService es2 = Executors.newFixedThreadPool(10);
// Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.
ExecutorService es3 = Executors.newScheduledThreadPool(10);
````
There are a few methods to execute task with an ExecutorService:
####execute(Runnable)
The execute method takes a Runnable, and executes it asynchronously:
````java
executorService.execute(new Runnable() {
    public void run() {
        System.out.println("A task");
    }
});
executorService.shutdown();
````

####submit(Runnable)
This method takes a Runnable but returns a Future object. This object returns null when the Runnable has finished executing:
````java
Future future = executorService.submit(new Runnable() {
    public void run() {
        System.out.println("A task");
    }
});
future.get(); //Blocks until the Runnable has finished
executorService.shutdown();
````

####submit(Callable)
This version of the method takes a Callable, and returns a Future object with a result when it has finished executing:
````java
Future future = executorService.submit(new Callable<String>() {
    public String call() {
        return "A callable";
    }
});
System.out.println(future.get()); //Blocks until the Callable has finished
executorService.shutdown();
````

####invokeAny(Collection<? extends Callable<T>>)
This method executes the given tasks returning the result of one that has completed successfully. You have no guarantee about which of the Callable's results you'll get, just one of the ones that finish. When one of the tasks complete or throws an exception, the rest are canceled. For example:
````java
List<Callable<String>> callables = new ArrayList<Callable<String>>();
callables.add(new Callable<String>() {
    public String call() {
        return "Callable 1";
    }
});
callables.add(new Callable<String>() {
    public String call() {
        return "Callable 2";
    }
});
callables.add(new Callable<String>() {
    public String call() {
        return "Callable 3";
    }
});
String result = executorService.invokeAny(callables);
System.out.println(result);
executorService.shutdown();
````
Sometimes it will print "Callable 1", sometimes "Callable 2", and other times "Callable 3".

####invokeAll(Collection<? extends Callable<T>>)
This method executes the given tasks, returning a list of Futures holding their status and results when all complete. `Future.isDone()` is true for each element of the returned list. A completed task could have terminated either normally or by throwing an exception:
````java
List<Callable<String>> callables = new ArrayList<Callable<String>>();
callables.add(new Callable<String>() {
    public String call() {
        return "Callable 1";
    }
});
callables.add(new Callable<String>() {
    public String call() {
        return "Callable 2";
    }
});
callables.add(new Callable<String>() {
    public String call() {
        return "Callable 3";
    }
});
List<Future<String>> futures = executorService.invokeAll(callables);
for(Future<String> f : futures){
    System.out.println(f.get());
}
executorService.shutdown();
````

When you are done using the ExecutorService you should shut it down, so the threads do not keep running. To terminate the threads inside the ExecutorService you call its `shutdown()` method. The ExecutorService will not shut down immediately, but it will no longer accept new tasks, and once all threads have finished current tasks, the ExecutorService shuts down. If you want to shut down the ExecutorService immediately, you can call the `shutdownNow()` method. This will attempt to stop all executing tasks right away, and skips all non-processed tasks.

#Identify potential threading problems among deadlock, starvation, livelock, and race conditions

###Deadlock
Deadlock describes a situation where two or more threads are blocked forever, waiting for each other. Example:
````java
public class TestThread {
   public static Object lock1 = new Object();
   public static Object lock2 = new Object();
   
   public static void main(String args[]) {
   
      Thread t1 = new Thread(new Task1());
      Thread t2 = new Thread(new Task2);
      t1.start();
      t2.start();
   }
   
   private static class Task1 implements Runnable {
      public void run() {
         synchronized (lock1) {
            System.out.println("Task 1: Holding lock 1...");
            try {
              Thread.sleep(10);
            }
            catch (InterruptedException e) {}
            System.out.println("Task 1: Waiting for lock 2...");
            synchronized (lock2) {
               System.out.println("Task 1: Holding lock 1 & 2...");
            }
         }
      }
   }
   
   private static class Task2 implements Runnable {
      public void run() {
         synchronized (lock2) {
            System.out.println("Task 2: Holding lock 2...");
            try {
              Thread.sleep(10);
            }
            catch (InterruptedException e) {}
            System.out.println("Task 2: Waiting for lock 1...");
            synchronized (lock1) {
               System.out.println("Task 2: Holding lock 1 & 2...");
            }
         }
      }
   } 
}
````

###Starvation
Lock starvation occurs when a thread, having lesser priority than other ones, is constantly waiting for a lock, never able to take it because other thread(s) with higher priority are constantly acquiring the lock.

###Livelock
A LiveLock is like a deadlock in the sense that two (or more) threads are blocking each others. But with the livelock, each thread is waiting "actively", trying to resolve the problem on its own. A live lock occurs when the combination of these processes' efforts to resolve the problem makes it impossible for them to ever terminate. For example, if two threads detect a deadlock, and try to "step aside" for each other, without proper care they will end up being stuck in a loop always "stepping aside" and never managing to move forwards.

###Race conditions
A race condition is a situation where two threads compete for the same resource and they try to change it at the same time, doing it in a way that causes unexpected results. The problem happens when for example, one thread checks if the value is X, then do something that depends on that value and another thread does something to the value in between the check and the do.

#Use synchronized keyword and java.util.concurrent.atomic package to control the order of thread execution
###synchronized
You have to be careful when multiple threads access shared variables, since it can result in a race condition. For example, in a method like this:
````java
int n = 0;

void m() {
    this.n = this.n + 1;
}
````
The increment of variable n is vulnerable to concurrency. We can use the synchronized keyword to fix this. We can, for example, synchronize the method:
````java
int n = 0;

synchronized void m() {
    this.n = this.n + 1;
}
````
Or just a block of code:
````java
int n = 0;

void m() {
  synchronized (this) {
    this.n = this.n + 1;
  }
}
````
Internally, Java uses a so monitor or lock to manage synchronization. This monitor is bound to an object. For synchronized instance methods, the lock is on the instance of the corresponding object. For static methods, it's the class. For synchronized blocks, the object can be specified (the example use this to refer to the instance the method belongs to). Only the thread that acquires the lock has access to the method (or block).

###java.util.concurrent.atomic
[java.concurrent.atomic](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html) contains classes to perform atomic operations. With an atomic operation, you can safely perform the operation in parallel on multiple threads without using the synchronized keyword or locks.

We have for example:
* AtomicBoolean, AtomicInteger, AtomicLong, and AtomicReference<V> to update a value of the corresponding type (or object reference) atomically.
* AtomicIntegerArray, AtomicLongArray, and AtomicReferenceArray<E> to update the elements of the corresponding array type (or object reference) atomically.
* DoubleAdder and LongAdder, where one or more variables together maintain an initially zero sum of the corresponding type.
* DoubleAccumulator and LongAccumulator, where one or more variables together maintain a running value of the corresponding type updated using a supplied binary operator.

The synchronized example above can be changed to use an AtomicInteger in this way:
````java
AtomicInteger n = new AtomicInteger(); // creates an AtomicInteger with the initial value 0.

void m() {
    n.incrementAndGet();
}
````
Here are some common operations for the atomic classes:
````java
AtomicInteger ai = new AtomicInteger(10);
int val = ai.get(); // Get the value
ai.set(15); // Set the value

int expectedValue = 15;
int newValue      = 20;
// If the value of ai equals expectedValue, ai is set to newValue
ai.compareAndSet(expectedValue, newValue);

ai = new AtomicInteger(10);
val = ai.getAndAdd(10); // val contains 10 but ai contains 20
val = ai.addAndGet(10); // val and ai contain 30

val = ai.getAndDecrement(); // val contains 30 but ai contains 29
val = ai.decrementAndGet(); // val and ai contain 28

val = ai.getAndIncrement(); // val contains 28 but ai contains 29
val = ai.incrementAndGet(); // val and ai contain 30
````
The methods `updateAndGet()` and `getAndUpdate()` accept a lambda expression in order to perform a function (an IntUnaryOperator in the case of AtomicInteger) upon the value, for example:
````java
AtomicInteger ai = new AtomicInteger(10);
ai.updateAndGet(i -> i * 5); // ai contains 50
````
The methods `accumulateAndGet()` and `getAndAccumulate()` accept a lambda expression of type IntBinaryOperator (in the case of AtomicInteger) that updates the current value with the results of applying the given function to the current and given values. The function is applied with the current value as its first argument, and the given value as the second argument. For example:
````java
AtomicInteger ai = new AtomicInteger(10);
atomicInt.accumulateAndGet(5, (a, b) -> a + b) // ai contains 15
````
In the case of LongAdder and DoubleAdder, they can be used to consecutively add values to a number. For example:
````java
ExecutorService executor = Executors.newFixedThreadPool(3);
LongAdder la = new LongAdder();
IntStream.range(0, 1000)
    .forEach(i -> executor.submit(la::increment)); //Adds one to la, 1000 times
System.out.println(la.sum());   // la contains 1000
````
This class provides the methods `add(long)` (adds the given value) and `increment()` (add one) and is thread-safe. But instead of just summing up a single result, this class maintains a set of variables internally to reduce contention over threads. The result can be retrieved by calling `sum()` or `sumThenReset()` (gets the value and reset the sum to zero). This class is prefered over the atomic classes when updates from multiple threads are more common than reads. 

LongAccumulator and DoubleAccumulator are a more generalized version of the previous classes. Here's an example:
````java
LongAccumulator acc = new LongAccumulator((a, b) -> a + b , 1L);
ExecutorService executor = Executors.newFixedThreadPool(2);
IntStream.range(0, 5)
    .forEach(i -> executor.submit(() -> acc.accumulate(i)));
System.out.println(acc.getThenReset()); //acc contains 15
````
In the example, a LongAccumulator is created with the function `a + b` and an initial value of one. With every call to accumulate(i), both the current result and the value i are passed as parameters to the lambda expression. A LongAccumulator also maintains a set of variables internally to reduce contention over threads. The result can be retrieved by calling `get()` or `getThenReset()` (gets the value and reset the variables to zero).

#Use java.util.concurrent collections and classes including CyclicBarrier and CopyOnWriteArrayList
###BlockingQueue
The [BlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html) interface represents a thread-safe queue. Generally, a thread produces objects, while another thread consumes them.

A BlockingQueue has four forms of methods:

| | Throws Exception |	Special Value |	Blocks |	Times Out |
|----|--------|-----------|----------|-------|
|Insert |` add(e)` | `offer(e)` | `put(e)` | `offer(e, timeout, timeunit)`|
|Remove | `remove()` | `poll()` | `take()` | `poll(timeout, timeunit)`|
|Examine | `element()`| `peek()` |  |  |

**Throws Exception**: If the attempted operation is not possible immediately, an exception is thrown.  
**Special Value**: If the attempted operation is not possible immediately, a special value is returned (often true / false).  
**Blocks**: If the attempted operation is not possible immediately, the method call blocks until it is.  
**Times Out**: If the attempted operation is not possible immediately, the method call blocks until it is, but waits no longer than the given timeout.

It is not possible to insert null into a BlockingQueue. If you try to insert null, the BlockingQueue will throw a NullPointerException.

The implementations of the BlockingQueue are:
* [ArrayBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ArrayBlockingQueue.html). A bounded blocking queue backed by an array. This queue orders elements FIFO (first-in-first-out). The head of the queue is that element that has been on the queue the longest time. The tail of the queue is that element that has been on the queue the shortest time. New elements are inserted at the tail of the queue, and the queue retrieval operations obtain elements at the head of the queue.  
* [DelayQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/DelayQueue.html). An unbounded blocking queue of Delayed elements, in which an element can only be taken when its delay has expired. 
* [LinkedBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedBlockingQueue.html). An optionally-bounded blocking queue based on linked nodes. This queue orders elements FIFO (first-in-first-out).  
* [LinkedTransferQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedTransferQueue.html). An unbounded TransferQueue based on linked nodes. This queue orders elements FIFO (first-in-first-out) with respect to any given producer.  
* [PriorityBlockingQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/PriorityBlockingQueue.html). An unbounded blocking queue that uses the same ordering rules as class [PriorityQueue](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html) and supplies blocking retrieval operations.  
* [SynchronousQueue](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/SynchronousQueue.html). A blocking queue in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa. A synchronous queue does not have any internal capacity, not even a capacity of one. You cannot peek at a synchronous queue because an element is only present when you try to remove it; you cannot insert an element (using any method) unless another thread is trying to remove it; you cannot iterate as there is nothing to iterate.

Here's an example using ArrayBlockingQueue:
````java
class Producer implements Runnable {
    private BlockingQueue<String> queue = null;
    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            // The sleeps calls will cause the Consumer to block while waiting for objects in the queue.
            queue.put("1");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<String> queue = null;
    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(4000);
    }
}
````

###Blocking Deque
The [BlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingDeque.html) interface in the java.util.concurrent class represents a thread-safe deque. A deque is a "Double Ended Queue", a queue which you can insert and take elements from, in both ends.

The implementation of the BlockingDeque is:
* [LinkedBlockingDeque](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/LinkedBlockingDeque.html). An optionally-bounded blocking deque based on linked nodes.

Like a BlockingQueue, a BlockingDeque has four forms of methods:  
For the First Element:

| | Throws Exception |	Special Value |	Blocks |	Times Out |
|----|--------|-----------|----------|-------|
|Insert |` addFirst(e)` | `offerFirst(e)` | `putFirst(e)` | `offerFirst(e, timeout, timeunit)`|
|Remove | `removeFirst()` | `pollFirst()` | `takeFirst()` | `pollFirst(timeout, timeunit)`|
|Examine | `getFirst()`| `peekFirst()` |  |  |

For the Last Element:

| | Throws Exception |	Special Value |	Blocks |	Times Out |
|----|--------|-----------|----------|-------|
|Insert |` addLast(e)` | `offerLast(e)` | `putLast(e)` | `offerLast(e, timeout, timeunit)`|
|Remove | `removeLast()` | `pollLast()` | `takeLast()` | `pollLast(timeout, timeunit)`|
|Examine | `getLast()`| `peekLast()` |  |  |

For example:
````java
BlockingDeque<String> deque = new LinkedBlockingDeque<>();
deque.addFirst("a");
deque.addLast("b");
String b = deque.takeLast();
String a = deque.takeFirst();
````

###ConcurrentMap
The [ConcurrentMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentMap.html) interface represents a Map that can handle concurrent access.

The implementation of the ConcurrentMap is:
* [ConcurrentHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html). A hash table supporting full concurrency of retrievals and high expected concurrency for updates. 

Since it extends form Map, it has the same methods as a normal map and some others for concurrent access:
````java
ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("key", "value");
Object value = concurrentMap.get("key");

// Puts a new value into the map only if no value exists for the given key
String val = map.putIfAbsent("key2", "value2");

// Returns the value for the given key. If doesn't exist, the passed default value is returned
value = map.getOrDefault("hi", "or not");
````
However, Java 8 adds new methods that support functional programming.

The method `forEach()` accepts a BiConsumer lambda expression with both the key and value of the map passed as parameters. It replaces for-each loops:
````java
concurrentMap.forEach((key, value) -> System.out.println(key + "=" + value));
````

The method `replaceAll()` accepts a BiFunction lambda expression. The function is called with the key and the value of each map entry returning a new value to be assigned for the current key:
````java
concurrentMap.replaceAll((key, value) -> value.toUpperCase());
````

To transform a single entry, use `compute()`. The method accepts both the key to be computed and a bi-function. There are two variations, `computeIfAbsent()` and `computeIfPresent()`, that work only if the key is absent or present respectively:
````java
concurrentMap.compute("key", (key, value) -> value.toUpperCase());
````
The method `merge()` can be used to unify a new value with an existing value in the map. It accepts a key, the new value to be merged into the existing entry and a bi-function to specify the merging behavior of both values:
````java
concurrentMap.merge("key", "newVal", (oldVal, newVal) -> oldVal + " merged with " + newVal);
System.out.println(concurrentMap.get("key")); // It prints "value merged with newVal"
````


###ConcurrentNavigableMap
The [ConcurrentNavigableMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentNavigableMap.html) interface is a [java.util.NavigableMap](https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html) with support for concurrent access and for its submaps. The submaps are the maps returned by various methods like `headMap()`, `subMap()` and `tailMap()`.

The implementation of ConcurrentNavigableMap is:
* [ConcurrentSkipListMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentSkipListMap.html). A scalable concurrent ConcurrentNavigableMap implementation. The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map creation time, depending on which constructor is used.

The `headMap(T toKey)` method returns a view of the map containing the keys which are strictly less than the given key. Changes to the original map are reflected in the head map:
````java
ConcurrentNavigableMap<String, String> map = new ConcurrentSkipListMap<>();
map.put("1", "one");
map.put("2", "two");
map.put("3", "three");
ConcurrentNavigableMap headMap = map.headMap("2");
````
headMap contains a ConcurrentNavigableMap with the key "1", since only this key is strictly less than "2".

The `tailMap(T fromKey)` method returns a view of the map containing the keys which are greater than or equal to the given fromKey.Changes to the original map are reflected in the head map:
````java
ConcurrentNavigableMap<String, String> map = new ConcurrentSkipListMap<>();
map.put("1", "one");
map.put("2", "two");
map.put("3", "three");
ConcurrentNavigableMap tailMap = map.tailMap("2");
````
tailMap contains the keys "2" and "3" because these two keys are greater than or equal to "2".

The `subMap()` method returns a view of the original map which contains all keys from (including) to (excluding) two keys given as parameters to the method:
````java
ConcurrentNavigableMap<String, String> map = new ConcurrentSkipListMap<>();
map.put("1", "one");
map.put("2", "two");
map.put("3", "three");
ConcurrentNavigableMap subMap = map.subMap("2", "3");
````
submap contains only the key "2", because only this key is greater than or equal to "2" and smaller than "3".

###CyclicBarrier
The [CyclicBarrier](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html) class is a synchronization mechanism that allows a set of threads to all wait for each other to reach a common barrier point. The barrier is called cyclic because it can be re-used after the waiting threads are released.

The waiting threads wait at the CyclicBarrier until either:
* The last thread arrives (calls `await()`)
* The thread is interrupted by another thread (another thread calls its interrupt() method)
* Another waiting thread is interrupted
* Another waiting thread times out while waiting at the CyclicBarrier
* The `CyclicBarrier.reset()` method is called by some external thread.

When you create a CyclicBarrier you specify how many threads wait for it, before releasing them:
````java
CyclicBarrier barrier = new CyclicBarrier(2);
````
The CyclicBarrier supports a barrier action, which is a Runnable that is executed once the last thread arrives. You pass tit in its constructor:
````java
Runnable barrierAction = () -> System.out.println("Barrier Action") ;
CyclicBarrier barrier = new CyclicBarrier(2, barrierAction);
````

And here is how a thread waits at a CyclicBarrier:
````java
barrier.await();
// Specifying a 20 seconds timeout to release the threads, even if not all threads are waiting at the CyclicBarrier
barrier.await(10, TimeUnit.SECONDS);
````
You can see a complete example in the [javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html).

###CopyOnWriteArrayList
The [CopyOnWriteArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CopyOnWriteArrayList.html) class is a thread-safe variant of ArrayList in which all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array.

The iterator of CopyOnWriteArrayList is fail-safe and doesn't throw a ConcurrentModificationException even if underlying CopyOnWriteArrayList is modified once the iteration begins, because the iterator is operating on a separate copy of ArrayList. For that reason, all the updates made on CopyOnWriteArrayList are not available to the iterator. However, element-changing operations on iterators themselves (remove, set, and add) are not supported. These methods throw an UnsupportedOperationException.

With CopyOnWriteArrayList, there is no lock on read, so this operation is faster. Because of this, CopyOnWriteArrayList is most useful when you have few updates and inserts and many concurrent reads than using, for example, `Collections.synchronizedList(arrayList)`.

#Use parallel Fork/Join Framework

The Fork/Join Framework is designed for work that can be broken down into smaller tasks, with its results combined to produce the final result. One important concept is that ideally no worker thread is idle, idle workers steal the work from those workers who are busy, this is known as *work-stealing*.

It follows this algorithm:
````
if (problem is small)
	directly solve problem
else {
	split problem into independent parts
	fork new subtasks to solve each part
	join all subtasks
	compose result from subresults
}
````
The core classes of the Fork-Join framework are ForkJoinPool and ForkJoinTask.

####ForkJoinPool
[ForkJoinPool](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html) is an implementation of ExecutorService that employs the work-stealing algorithm. It can be created like this:
````java
ForkJoinPool pool = new ForkJoinPool(int); //creates a ForkJoinPool with the indicated parallelism level (number of initial threads in the pool)
ForkJoinPool pool = new ForkJoinPool(); //equivalent to new ForkJoinPool(Runtime.availableProcessors())
````
There are different ways of submitting a task to the ForkJoinPool:
````java
void execute(ForkJoinTask<?> task)
````
Arranges for (asynchronous) execution of the given task.
````java
void execute(Runnable task)
````
Executes the given command at some time in the future.
````java
<T> T invoke(ForkJoinTask<T> task)
````
Performs the given task, returning its result upon completion.
````java
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
````
Executes the given tasks, returning a list of Futures holding their status and results when all complete.
````java
<T> ForkJoinTask<T>	submit(Callable<T> task)
````
Submits a value-returning task for execution and returns a Future representing the pending results of the task.
````java
<T> ForkJoinTask<T>	submit(ForkJoinTask<T> task)
````
Submits a ForkJoinTask for execution.
````java
ForkJoinTask<?> submit(Runnable task)
````
Submits a Runnable task for execution and returns a Future representing that task.
````java
<T> ForkJoinTask<T>	submit(Runnable task, T result)
````
Submits a Runnable task for execution and returns a Future representing that task.

####ForkJoinTask
[ForkJoinTask](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinTask.html) is an abstract class for creating tasks that run within a ForkJoinPool. [RecursiveAction](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveAction.html) and [RecursiveTask](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveTask.html) are its subclasses. The only difference between these two classes is that the RecursiveAction does not return a value while RecursiveTask does.

The main methods of ForkJoinTask are:
* The `fork()` method allows a ForkJoinTask to be planned for asynchronous execution. A new task can be created with this method.
* The join() method allows a ForkJoinTask to wait for the completion of another one.

In this example,  the program finds the minimum number from a large array:
````java
public class MinimumTaskFinder extends RecursiveTask<Integer> {

  private static final int SEQUENTIAL_THRESHOLD = 5;

  private final int[] data;
  private final int start;
  private final int end;

  public MinimumTaskFinder(int[] data, int start, int end) {
    this.data = data;
    this.start = start;
    this.end = end;
  }

  public MinimumTaskFinder(int[] data) {
    this(data, 0, data.length);
  }

  @Override
  protected Integer compute() {
    final int length = end - start;
    if (length < SEQUENTIAL_THRESHOLD) {
      return computeDirectly();
    }
    final int split = length / 2;
    final MinimumTaskFinder left = new MinimumTaskFinder(data, start, start + split);
    left.fork();
    final MinimumTaskFinder right = new MinimumTaskFinder(data, start + split, end);
    return Math.min(right.compute(), left.join());
  }

  private Integer computeDirectly() {
    int min = Integer.MAX_VALUE;
    for (int i = start; i < end; i++) {
      if (data[i] < min) {
        min = data[i];
      }
    }
    return min;
  }

  public static void main(String[] args) {
    int[] data = new int[10000];
    Random random = new Random();
    for (int i = 0; i < data.length; i++) {
      data[i] = random.nextInt(1000);
    }

    ForkJoinPool pool = new ForkJoinPool();
    MinimumTaskFinder task = new MinimumTaskFinder(data);
    System.out.println(pool.invoke(task));
  }
}
````
If the size of the array is less than a threshold, then find the minimum directly by iterating over the array. Otherwise, since the problem can be broken into chunks, split the array into two halves, recurse on each half and wait for them to complete (join). Once the value is reduced to the threshold, the tasks are not further divided for parallelism. Finally, once we have the result of each half, we can find the minimum of the two and return it. 

#Use parallel Streams including reduction, decomposition, merging processes, pipelines and performance.

You can execute streams in parallel so Java partitions the stream into multiple substreams. Aggregate operations iterate over and process these substreams in parallel and then combine the results. It's important that the operations are stateless and can be executed in an arbitrary order.

A stream is not parallel by default. To make a parallel stream, invoke the method `Collection.parallelStream` (if you're working with a collection) or `BaseStream.parallel`:
````java
List l = new ArrayList();
l.parallelStream().forEach(System.out:println);
// Or
Stream.of("1", "2", "3").parallel().forEach(System.out:println);
````
Parallel streams use a common ForkJoinPool available via the `ForkJoinPool.commonPool()` method. The size of the thread-pool depends on the amount of available physical CPU cores:
````java
ForkJoinPool commonPool = ForkJoinPool.commonPool();
System.out.println(commonPool.getParallelism()); 
````
The value can be modified by setting the following JVM parameter to a non-negative integer:
````java
-Djava.util.concurrent.ForkJoinPool.common.parallelism=4
````

###Reduction
A reduction operation combines all elements into a single result, such as finding the sum or maximum of a set of numbers, or accumulating elements into a list. So in addition to `reduce()`, `collect()`, `sum()`, `max()`, or `count()` are also reduction operations.

The `reduce()` method has the following versions:
````java
Optional<T>	reduce(BinaryOperator<T> accumulator)
````
Performs a reduction on the elements of this stream, using an associative accumulation function, and returns an Optional describing the reduced value, if any.
````java
T	reduce(T identity, BinaryOperator<T> accumulator)
````
Performs a reduction on the elements of this stream, using the provided identity value and an associative accumulation function, and returns the reduced value.
````java
<U> U	reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
````
Performs a reduction on the elements of this stream, using the provided identity, accumulation and combining functions.

A reduce operation is parallelizable as long as the function(s) used to process the elements are associative and stateless. For example:
````java
int sum = numbers.parallelStream().reduce(0, (x,y) -> x+y);
````
Can be parallelized with no modification:
````java
int sum = numbers.parallelStream().reduce(0, (x,y) -> x+y);
````
Reduction can operate on subsets of the data in parallel, and then combine the intermediate results to get the final answer.

A reduce operation on elements of type <T> yielding a result of type <U> requires three parameters:
````java
 <U> U reduce(U identity,
              BiFunction<U, ? super T, U> accumulator,
              BinaryOperator<U> combiner);
````
Here, the identity element is both an initial seed value for the reduction and a default result if there are no input elements. The accumulator function takes a partial result and the next element and produces a new partial result. The combiner function combines two partial results to produce a new partial (or the final) result. Example:
````java
int sum = numbers.parallelStream()
                  .reduce(0, 
                    (sum, x) -> sum + x,
                    (x, y) -> x + y);
````

#Describe the interfaces that make up the core of the JDBC API including the Driver, Connection, Statement, and ResultSet interfaces and their relationship to provider implementations

[Driver](https://docs.oracle.com/javase/8/docs/api/java/sql/Driver.html).  Provides the API for registering and connecting drivers based on JDBC (every driver class must implement it). Generally used only by the DriverManager class

[DriverManager](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html). To makes a connection with a driver.

[Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html). Provides methods for creating statements and managing connections with a data source and their properties.

[Statement](https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html). Object used for executing a static SQL statement and returning the results it produces.

[ResultSet](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html). Object used for retrieving and updating the results of a query.

To use the JDBC API with a particular DBMS, you need a JDBC driver to establish the communication between Java and the database. JDBC drivers are divided into four types:

Type 1: JDBC-ODBC (Open Database Connectivity) Bridge  
Type 2: Native-API, partly Java driver  
Type 3: Network-protocol, all-Java driver  
Type 4: Native-protocol, all-Java driver

All JDBC drivers implement four JDBC interfaces: 
* Driver
* Connection
* Statement
* ResultSet.

The DriverManager class tracks the loaded JDBC drivers and creates the database connections. Before JDBC 4.0, the Driver class was loaded with this code:
````java
Class.forName("com.jw.client.JWDriver");
````
From JDBC 4.0 this is done automatically. This registers the driver with the DriverManager. This way, when a program creates a database connection with the `DriverManager.getConnection()` method, the DriverManager, in turn, calls the `Driver.connect()` method. Every JDBC driver must implement the java.sql.Driver interface. So, the JDBC driver's `connect()` method checks whether the driver URL is correct, and then, returns the Connection within its `connect()` method.


#Identify the components required to connect to a database using the DriverManager class including the JDBC URL
When working with JDBC, the first thing you need to do is to establish a connection with a data source (like a DBMS). One way to this is to use the class [DriverManager](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html).

DriverManager connects an application to a data source by using a database URL. When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within the classpath.

A database URL varies depending on the DBMS used. For example, for MySQL it looks like this:
````
jdbc:mysql://localhost:3306/data
````
Where localhost is the name of the server that is hosting the database, 3306 is the port number and data is the name of the database.

In previous versions of JDBC, to get a connection, you first had to initialize the JDBC driver by calling the method `Class.forName()`. Since JDBC 4.0, drivers that are found in your class path are automatically loaded. 

To connect to a data source using DriverManager, we need to get a [Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html) object with something like the following snippet:
````java
Properties props = new Properties();
props.put("user", userName);
props.put("password", password);

Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data");
````

#Submit queries and read results from the database including creating statements, returning result sets, iterating through the results, and properly closing result sets, statements, and connections

To process an SQL statement with JDBC you have to:

1. Establishing a connection.
2. Create a statement.
3. Execute the query.
4. Process the ResultSet object.
5. Close the connection.

In code, it looks like this:
````java
String sql = "SELECT id, name FROM users WHERE id = ?";
List<User> users = new ArrayList<>();
try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=admin&password=admin12345");
     PreparedStatement ps = con.prepareStatement(sql);) {
    ps.setInt(1, 1001);
    try (ResultSet rs = ps.executeQuery();) {
        while(rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("name")));
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
````

First, a connection with a MySQL database is established.

With a Connection object, you create a Statement object. There are three different kinds of statements:
* [Statement](https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html). Used to implement simple SQL statements with no parameters.
* [PreparedStatement](https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html). Used for precompiling SQL statements that might contain input parameters. It extends Statement.
* [CallableStatement](https://docs.oracle.com/javase/8/docs/api/java/sql/CallableStatement.html). Used to execute stored procedures that may contain both input and output parameters. It extends PreparedStatement.

To execute a query, call an execute method from Statement such as:
* `execute()`. Returns true if the first object that the query returns is a ResultSet object. Use this method if the query could return one or more ResultSet objects. Retrieve the ResultSet objects returned from the query by repeatedly calling Statement.getResultSet.
* `executeQuery()`. Returns one ResultSet object.
* `executeUpdate()`. Returns an integer representing the number of rows affected by the SQL statement. Use this method if you are using INSERT, DELETE, or UPDATE SQL statements.

With a ResultSet object, you can access the data. It acts as a cursor, pointing  to one row of data and positioned before the first row at the beginning. Then you call, for example, the method `next()` to move the cursor forward by one row and you can get the data with getter methods that either take the column index (the first column is 1) or the column name. There are getter methods for a lot of types, for example:
````java
int getInt(int columnIndex);
int getInt(String columnName);

long getLong(int columnIndex);
long getLong(String columnName);

String getString(int columnIndex);
String getString(String columnName);

BigDecimal getBigDecimal(int columnIndex);
BigDecimal getBigDecimal(String columnName);
````

When you are finished, call the method `Statement.close()` to immediately release the resources it's using. When you call this method, its ResultSet objects are also closed. If you're not going to need the connection anymore, you should also close the connection with `Connection.close()`. You can use a try-with-resources statement to automatically close Connection, Statement, and ResultSet objects, regardless of whether an SQLException has been thrown. Or you can close them manually in the finally block like this:
````java
try {
  Connection con = ...
  Statement stmt = ...
  // Do something with stmt
} catch(Exception e) {
  e.printStackTrace();
} finally {
    if (stmt != null) { stmt.close(); }
    if (con != null) { con.close(); }
}
````
#Describe the advantages of localizing an application
Internationalization is the process of designing an application so that it can be adapted to various languages and regions without engineering changes. 

Localization is the process of adapting the content of a product to a specific region or language. Translating words is the best-known part of this process, but it is not the only part. Localization usually includes:
* Numbers
* Dates
* Currencies
* Images and sounds
* Layouts

Even in countries with the same languages, you can find differences, for example, American English vs. British English.

So the benefits of localization are:
* With the addition of localized data, the same executable can run worldwide.
* Textual elements, such as status messages and the GUI component labels, are not hardcoded in the program. Instead, they are stored outside the source code and retrieved dynamically.
* Support for new languages does not require recompilation.
* Culturally-dependent data, such as dates and currencies, appear in formats that conform to the end user's region and language.

#Read and set the locale by using the Locale object
Here's the [javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html) for the Locale class.

The four ways to create a Locale object are:
* `Locale.Builder` Class
  
  For example:
  ````java
  Locale loc = new Locale.Builder().setLanguage("fr").setRegion("FR").build();
  ````
* Locale Constructors

  There are three constructors available in the Locale class:
  * `Locale(String language)`
  * `Locale(String language, String country)`
  * `Locale(String language, String country, String variant)`
  
  For example:
  ````java
  Locale l1 = new Locale("es", "ES");
  Locale l2 = new Locale("en");
  ````
* `Locale.forLanguageTag` Factory Method

  For example:
  ````java
  Locale loc = Locale.forLanguageTag("ja-JP");
  ````
* Locale Constants

  For example:
  ````java
  Locale loc1 = Locale.ITALIAN;
  Locale loc2 = Locale.CHINA;
  ````

To find out which types of *Locale* definitions a locale-sensitive class recognizes, you invoke the `getAvailableLocales()` method. For example, to find out which *Locale* definitions are supported by the *NumberFormat* class:
````java
public class Test {
    static public void main(String[] args) {
        Locale locales[] = NumberFormat.getAvailableLocales();
        for (Locale l : locales) {
            System.out.println(l.toString());
        }
    }
}
````
The output can be something like this:
````
ms_MY
ar_QA
is_IS
fi_FI
pl
en_MT
````
If we change from`System.out.println(l.toString())` to `System.out.println(l.getDisplayName ())` the output would be:
````
Malay (Malaysia)
Arabic (Qatar)
Icelandic (Iceland)
Finnish (Finland)
Polish
English (Malta)
````

You can assign a different *Locale* to every locale-sensitive object in your program. However, locale-sensitive objects rely on the default *Locale* set by the Java Virtual Machine. You can use the `Locale.getDefault()` method to get it and the `Locale.setDefault(Locale)` to set it.

#Create and read a Properties file

Properties are files with string key/value pairs that store configuration data or settings:
````
# A comment
user=amtg
passw=changeme
language=java
````

To use them, you create instances of `java.util.Properties`. The following example, loads the file config.properties from project classpath and read its properties:
````java
public class Test {
  public static void main(String[] args) {
    	Properties prop = new Properties();
    	InputStream input = null;
 
    	try {
    		input = Test.class.getClassLoader().getResourceAsStream("config.properties");
    		//load the properties file from class path
    		prop.load(input);
 
        //get a property value and print it out
        System.out.println(prop.getProperty("user"));
        
        //get all properties
        Enumeration<?> e = prop.propertyNames();
    		while (e.hasMoreElements()) {
    			String key = (String) e.nextElement();
    			String value = prop.getProperty(key);
    			System.out.println("Key : " + key + ", Value : " + value);
    		}
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
      } finally {
        	input.close();
			}
    }
}
````

#Build a resource bundle for each locale and load a resource bundle in an application

[java.util.ResourceBundle](http://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html) class is used to store texts in key/pair values and components that are locale sensitive.

Resource bundles can share a common base name, but names can also have additional components that identify their locales. For example, if the base name of resource bundles is "MyBundle", we can have a "MyBundle_en" for the English locale and "MyBundle_es" for a Spanish locale. We can even have different resources for different countries, for example, "MyBundle_fr_CA" for Canadian French:
````
MyBundle.properties
MyBundle_en.properties
MyBundle_es.properties
MyBundle_fr_CA.properties
````
These files should be located in the same directory. If no locale is found for a resource bundle, the default bundle ("MyBundle.properties") will be used.

To load the resource bundle MyBundle.properties for an English locale, we use the following code:
````java
Locale locale = new Locale("en", "US");
ResourceBundle rb = ResourceBundle.getBundle("MyBundle", locale);
System.out.println(rb.getString("label"));
````

Although we only use the *ResourceBundle* class, it actually has two subclasses,  *PropertyResourceBundle* and *ListResourceBundle*. 

The *PropertyResourceBundle* class stores localized texts in standard Java property files.

The *ListResourceBundle* class uses classes to contain the resources. Using classes, you can use more than just *String* values.

Here's an example implementation:
````java
public class MyBundle_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return labels;
    }

    private Object[][] labels = {
            { "value1"   , new Integer(100) },
            { "label1", "MILES" },
    };
}
````

To get an object value, we use `rb.getObject(key)` or `rb.getStringArray(key)` (which is just a shortcut to `(String[])getObject(key)`) instead of `rb.getString(key)`

You can also obtain a set of all the keys contained in the *ResourceBundle* by using:
````java
Set<String> keys = rb.keySet();
````
