package br.com.dvlima.ocpjp8.apress.topic03._generics_and_collections._01_generic_class._06_using_bounded_wildcards;

class Parcel<T> {
	public <X> void deliver(X x) {
		System.out.println(x.getClass());
	}
}
