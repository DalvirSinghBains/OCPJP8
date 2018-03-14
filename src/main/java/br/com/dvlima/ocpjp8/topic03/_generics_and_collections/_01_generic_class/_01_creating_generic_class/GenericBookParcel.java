package br.com.dvlima.ocpjp8.topic03._generics_and_collections._01_generic_class._01_creating_generic_class;

/**
 * Created by danilolima on 14/03/18.
 */
public class GenericBookParcel<X, T> extends Parcel<T>{
/*
    GENERIC CLASS EXTENDING ANOTHER GENERIC CLASS
 */
//TIP: Won' compile; no way to pass argument to T
//public class GenericBookParcel<X> extends Parcel<T>{
//TIP: ok, defines two type parameters X an T
//public class GenericBookParcel<X, T> extends Parcel<T>{
//TIP: ok
//public class GenericBookParcel<X, T> extends Parcel<T>{
//public class GenericBookParcel<X, T> extends Parcel<Book>{//Real class Book

/*
    NONGENERIC CLASS EXTENDING A GENERIC CLASS
 */
//TIP: is a nongeneric class that passes argument Phone to its base class Parcel<T>
//class NonGenericPhoneParcel extends Parcel<Phone>

//TIP: won't compile
//class NonGenericPhoneParcel extends Parcel<T>
//NonGenericPhoneParcel<String> var = new NonGenericPhoneParcel<>();
// can't pass arguments to a nongeneric class.

/*
    MULTIPLE TYPE PARAMETERS
 */
//class ClassName <T1, T2, ..., Tn>

}
