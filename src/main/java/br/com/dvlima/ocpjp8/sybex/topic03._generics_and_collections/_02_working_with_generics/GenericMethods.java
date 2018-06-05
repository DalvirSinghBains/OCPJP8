package br.com.dvlima.ocpjp8.sybex.topic03._generics_and_collections._02_working_with_generics;

public class GenericMethods {

    public static <T> void sink(T t) {
    }

    public static <T> T identity(T t) {
        return t;
    }

    // DOES NOT COMPILE
    //public static T noGood(T t) { return t; }

}
