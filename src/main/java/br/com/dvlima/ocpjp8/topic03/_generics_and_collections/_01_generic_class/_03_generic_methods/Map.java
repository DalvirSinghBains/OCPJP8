package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._03_generic_methods;

public interface Map<X, Y> {
	<T> void mapMaterial(T t); //Generic method that defines its own type parameter
	<Y> void get(X key);
}
