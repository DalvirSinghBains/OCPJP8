package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._01_creating_generic_class;

/**
 * Created by danilolima on 13/03/18.
 */
//TIP: Variable names used for type parameters
// You can't use java keywords;
// As per Oracle's naming conventions, 
public class Parcel<T> {

    private T t;

    public void set(T t){
        this.t = t;
    }

    public T get() {
        return t;
    }
}
