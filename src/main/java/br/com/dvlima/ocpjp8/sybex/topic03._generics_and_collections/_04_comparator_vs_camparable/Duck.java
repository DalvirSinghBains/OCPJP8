package br.com.dvlima.ocpjp8.sybex.topic03._generics_and_collections._04_comparator_vs_camparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Duck implements Comparable<Duck> {
    public String name;
    public int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Duck(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        List<Duck> ducks1 = new ArrayList<>();
        ducks1.add(new Duck("Quack"));
        ducks1.add(new Duck("Puddles"));
        Collections.sort(ducks1); // sort by name
        System.out.println(ducks1); // [Puddles, Quack]


        Comparator<Duck> byWeight = new Comparator<Duck>() {
            public int compare(Duck d1, Duck d2) {
                return d1.weight - d2.weight;
            }
        };

        Comparator<Duck> byWeightAsc = (d1, d2) -> d2.weight - d1.weight;

        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Quack", 7));
        ducks.add(new Duck("Puddles", 10));

        Collections.sort(ducks);
        System.out.println(ducks);  // [Puddles, Quack]

        Collections.sort(ducks, byWeight);
        System.out.println(ducks);  // [Quack, Puddles]

        Collections.sort(ducks, byWeightAsc);
        System.out.println(ducks);  // [Puddles, Quack]
    }

    public String toString() {
        return name;
    }

    public int compareTo(Duck d) {
        return name.compareTo(d.name);
// use readable output
// call String's compareTo
    }
}