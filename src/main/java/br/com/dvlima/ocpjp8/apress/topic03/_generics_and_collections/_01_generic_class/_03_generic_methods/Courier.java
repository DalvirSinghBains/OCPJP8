package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class._03_generic_methods;

public abstract class Courier {				//Nongeneric class

	//void
	public <E> void deliver(E[] array) {	//Generic Method
		for (E item : array) {
			System.out.println("Delivering - " + item);
		}
	}

}