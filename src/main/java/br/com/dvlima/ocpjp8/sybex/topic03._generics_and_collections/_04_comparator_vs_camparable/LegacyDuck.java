package br.com.dvlima.ocpjp8.sybex.topic03._generics_and_collections._04_comparator_vs_camparable;

public class LegacyDuck
        //implements java.util.Comparable
                                        {
    private String name;

    public int compareTo(Object obj) {
        LegacyDuck d = (LegacyDuck) obj; // cast because no generics
        return name.compareTo(d.name);
    }
}