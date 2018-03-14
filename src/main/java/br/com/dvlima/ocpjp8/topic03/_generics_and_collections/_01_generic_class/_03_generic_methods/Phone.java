package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._03_generic_methods;

//Generic class declaration with type parameter X
public class Phone<X> {

	//Generic constructor declaration with type parameter T
	<T> Phone(T t){
		
	}
	
	public static void main(String[] args) {
		Phone<Double> phone = new Phone<Double>("Iphone");
	}
}
