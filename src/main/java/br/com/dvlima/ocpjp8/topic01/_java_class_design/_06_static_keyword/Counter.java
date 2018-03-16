package br.com.dvlima.ocpjp8.topic01._java_class_design._06_static_keyword;

//Counter class should count the number of instances created from that class
public class Counter {

	static {//Prints Once for unique instace Counter
		System.out.println("1");
	}

	{//prints for every Counter instance created
		System.out.println("3");
	}

	static {//Prints Once for unique instace Counter
		System.out.println("2");
	}

	{//prints for every Counter instance created
		System.out.println("4");
	}

	private static int count; // variable to store the number of objects created
	// for every Counter object created, the default constructor will be called;
	// so, update the counter value inside the default constructor

	public Counter() {
		count++;
	}

	public static void printCount() { // method to print the counter value so far
		System.out.println("Number of instances created so far is: " + count);
	}

	public static void main(String[] args) {
		Counter anInstance = new Counter();
		// note we call printCount using the class name
		// instead of instance variable name
		Counter.printCount();
		Counter anotherInstance = new Counter();
		Counter.printCount();
	}

}
