package br.com.dvlima.ocpjp8.topic05._java_stream_api._05_sort_collection_using_stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortCollectionUsingStreamTest {

    public static void main(String[] args) {
        sortingCollection();

        sortByLength();

        sortByLengthThenNatural();

        sortByLengthThenNaturalReversed();
    }


    private static void sortingCollection() {
        List words = Arrays.asList("follow your heart but take your brain with you".split(" "));
        words.stream().distinct().sorted().forEach(System.out::println);
    }

    /**
     * The sorted() method sorts the elements in their “natural order”; the sorted() method requires that
     * the elements in the stream implement the Comparable interface. How to sort the elements in some other
     * order? For that you can invoke the overloaded sorted method that takes a Comparator as the argument:
     * Stream<T> sorted(Comparator<? super T> comparator)
     */
    private static void sortByLength() {
        List words = Arrays.asList("follow your heart but take your brain with you".split(" "));
        Comparator<String> lengthCompare = (str1, str2) -> str1.length() - str2.length();
        words.stream().distinct().sorted(lengthCompare).forEach(System.out::println);
        /*
        but
        you
        your
        take
        with
        heart
        brain
        follow
         */
    }

    /**
     * The word “heart” appears before “brain” though they are of same length. So, what if we want to first sort the
     * words by length and then sort the words of same length by natural order? For that you can use thenComparing()
     * default method provided in the Comparator interface
     */
    private static void sortByLengthThenNatural() {
        System.out.printf("%20s%s\n", "--------", "--------");
        List words = Arrays.asList("follow your heart but take your brain with you".split(" "));
        Comparator<String> lengthCompare = (str1, str2) -> str1.length() - str2.length();
        words.stream().distinct()
                .sorted(lengthCompare.thenComparing(String::compareTo))
                .forEach(System.out::println);
        /*
        but
        you
        take
        with
        your
        brain
        heart
        follow
         */
    }

    /**
     * Fortunately, the Comparator interface has been enhanced with many useful default and static methods in Java 8.
     * One such method added is reversed() and you can make use of that
     */
    private static void sortByLengthThenNaturalReversed() {
        List words = Arrays.asList("follow your heart but take your brain with you".split(" "));
        Comparator<String> lengthCompare = (str1, str2) -> str1.length() - str2.length();
        words.stream().distinct()
                .sorted(lengthCompare.thenComparing(String::compareTo).reversed())
                .forEach(System.out::println);
        /*
        follow
        heart
        brain
        your
        with
        take
        you
        but
         */
    }
}
