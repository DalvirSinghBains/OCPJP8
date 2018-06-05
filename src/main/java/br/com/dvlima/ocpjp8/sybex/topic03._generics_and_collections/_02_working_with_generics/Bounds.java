package br.com.dvlima.ocpjp8.sybex.topic03._generics_and_collections._02_working_with_generics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface Flyer {
    void fly();
}

public class Bounds {

    //Unbounded Wildcards

    //List of “whatever.” That’s what List<?> is.
    private static void printList(List<?> list) {
        for (Object x : list) System.out.println(x);
    }

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("java");
        printList(keywords);
    }


    //Upper-Bounded Wildcards

    // ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT COMPILE
    // Instead, we need to use a wildcard:
    // List<? extends Number> list = new ArrayList<Integer>();
    public static long total(List<? extends Number> list) {
        long count = 0;
        for (Number number : list)
            count += number.longValue();
        return count;
    }

    private void anyFlyer(List<Flyer> flyer) {
    }

    private void groupOfFlyers(List<? extends Flyer> flyer) {
    }


    //Lower-Bounded Wildcards
    List<? super IOException> exceptions = new ArrayList<Exception>();
   // exceptions.add(new Exception()); // DOES NOT COMPILE
    //exceptions.add(new IOException());
//    exceptions.add(new FileNotFoundException());

}

class HangGlider implements Flyer {
    public void fly() {
    }
}

class Goose implements Flyer {
    public void fly() {
    }
}

//NEED TO USE AN UNKNOWN TYPE
class Gifts{}
class Book extends Gifts{}
class Phone extends Gifts{}

class TestWildcard {

    private static <X> void printClass(X obj){

    }

    public static void testWildcard() {
        TestWildcard test = new TestWildcard();
        printClass("");
        test.<String>printClass("");

        //You can assign an object of class Book or Phone to a reference variable of type Gifts:
        Gifts gift = new Book();
        gift = new Phone();

        //But the following assignment isn’t valid:
        //List<Gift> wishList = new ArrayList<Book>();//Won’t compile
        List<?> wishList = new ArrayList<Book>(); //? refers to any type

        // wishList.add(new Book()); Won’t compile

        // Because of the ? you can invoke method add() with literally any object
        // String, Integer, Book, Phone, and others.
        // But ArrayList<Book> should only have Book instances.

//        EXAM TIP When you use a wildcard to declare your variables or method parameters, you lose the functionality of adding objects to a collection. In this case, using the add method will result in compilation failure.


        //UPPER-BOUNDED WILDCARDS
        List<? extends Gifts> myList1 = new ArrayList<Gifts>();
        List<? extends Gifts> myList2 = new ArrayList<Book>();
        List<? extends Gifts> myList3 = new ArrayList<Phone>();

        //LOWER-BOUNDED WILDCARDS
        List<? super Gifts> list = new ArrayList<Gifts>();
        list.add(new Gifts());
        list.add(new Book());
        list.add(new Phone());
        //list.add(new Object());

        //Elements are read as instance Object, superclass of Gift.
        for (Object obj : list) System.out.println(obj);

//                              //Read Object of type
//        List<?>               Object
//        List<? extends Gift>  Gift
//        List<? super Gift>    Object

    }

    public static void wrapGift(List<? extends Gifts> list) {
        for (Gifts item : list) {
            System.out.println("GiftWrap - " + item);
        }
    }
}

