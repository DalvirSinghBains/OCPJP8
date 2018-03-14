package br.com.dvlima.ocpjp8.topic02._04_ocpjp7_enumerated_types;

import java.util.List;

/**
 * Created by danilolima on 08/03/18.
 */
public enum Level implements MyInterface{

    BEGINNER, INTERMEDIATE, EXPERT;

    static {
        System.out.println("static init block");
    }

    Level(){
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println(Level.BEGINNER);         //toString() {return name;}
        System.out.println(Level.BEGINNER.name());  //name(){return name;}

//Prints:
//        constructor
//        constructor
//        constructor
//        static init block
//        BEGINNER
    }

    @Override
    public List<Level> getItens() {
        return null;
    }
}

/*
 TIP: Enum is implicitly declared final.

 enum ExtendedEnumTest extends Level{ // error
    NOVICE;
}
*/

/*
 TIP: An enum implicitly extends java.lang.Enum, so it can't extend any other class.
 TIP: A class can't explicitly extend java.lang.Enum

 */
