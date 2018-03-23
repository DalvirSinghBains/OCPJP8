package br.com.dvlima.ocpjp8.topic05._java_stream_api._07_use_flatmap_methods;

import java.util.Arrays;
import java.util.List;

/**
 * Use flatMap() methods in the Stream API
 */
public class UsingFlatMapMethodInStreamTest {

    public static void main(String[] args) {
//difference between map() and flatMap()
    }

    private static void uniqueCharacters() {
        String[] string = "you never know what you have until you clean your room".split(" ");
        Arrays.stream(string)
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .forEach(System.out::print);
    }


    //UsingMap
    private static void usingMap() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream()   //[1, 2, 3, 4, 5]
                .map(i -> i * i)//[1, 4, 9, 16, 25]
                .forEach(System.out::println);
    }

    /**
     * UsingFlatMap
     * The flatMap() method operates on elements just like map() method. However, flatMap() flattens the
     * streams that result from mapping each of its elements into one flat stream.
     */
    private static void usingFlatMap() {
        List<List<Integer>> intsOfInts = Arrays.asList(Arrays.asList(1, 3, 5), Arrays.asList(2, 4));
        intsOfInts.stream()// [1 3 5] [2 4]
                .flatMap(ints -> ints.stream()) //[1 3 5 2 4]
                .sorted()//[1 2 3 4 5]
                .map(i -> i * i) //[1 4 9 16 25]
                .forEach(System.out::println);
    }
}
