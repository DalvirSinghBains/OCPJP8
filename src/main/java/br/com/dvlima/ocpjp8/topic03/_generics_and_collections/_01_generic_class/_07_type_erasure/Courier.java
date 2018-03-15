package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._07_type_erasure;

abstract class Courier {

	public <E> void deliver(E[] array) {
		for(E item : array) {
			System.out.println(item);
		}
	}
	//The java compiler would replace all occurences of E with Object
	/*public void deliver(Object[] array) {
		for(Object item : array) {
			System.out.println(item);
		}
	}*/
}
