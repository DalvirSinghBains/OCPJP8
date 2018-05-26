package br.com.dvlima.ocpjp8.apress.topic05._java_stream_api._06_collect_method;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Collectors class has methods that support the task of collecting elements to a collection. You can
 * use methods such as toList(), toSet(), toMap(), and toCollection() to create a collection from a stream.
 */
public class SaveResultsToCollection {
    public static void main(String[] args) {
        collectorsToList();

        collectorsToSet();

        collectorsToMap();

        collectorsToTreeSet();

        groupStringsByLength();

        partitionStrings();
    }


    private static void collectorsToList() {
        String frenchCounting = "un:deux:trois:quatre";
        List gmailList = Pattern.compile(":")
                .splitAsStream(frenchCounting)
                .collect(Collectors.toList());

        gmailList.forEach(System.out::println);
    }

    private static void collectorsToSet() {
        String[] roseQuote = "a rose is a rose is a rose".split(" ");
        Set words = Arrays.stream(roseQuote).collect(Collectors.toSet());
        words.forEach(System.out::println);
    }

    private static void collectorsToMap() {
        Map<String, Integer> nameLength = Stream.of("Arnold", "Alois", "Schwarzenegger")
                .collect(Collectors.toMap(name -> name, name -> name.length()));
        nameLength.forEach((name, len) -> System.out.printf("%s - %d \n", name, len));
    }

    private static void collectorsToTreeSet() {
        String[] roseQuote = "a rose is a rose is a rose".split(" ");
        Set words = Arrays.stream(roseQuote).collect(Collectors.toCollection(TreeSet::new));
        words.forEach(System.out::println);
    }

    /**
     * The groupingBy() method in Collectors class takes a Function as an argument.
     * It uses the result of the function to return a Map.
     * The Map object consists of the values returned by the Function and the List of elements that matched.
     */
    private static void groupStringsByLength() {
        String[] string = "you never know what you have until you clean your room".split(" ");
        Stream<String> distinctWords = Arrays.stream(string).distinct();
        Map<Integer, List<String>> wordGroups = distinctWords.collect(Collectors.groupingBy(String::length));
        wordGroups.forEach((count, words) -> {
            System.out.printf("word(s) of length %d %n", count);
            words.forEach(System.out::println);
        });
    }

    /**
     * In the partitioningBy() method, we have given the condition str -> str.length() > 4. Now,
     * the result will be divided into two parts: a part with elements that evaluated to true for this condition and
     * another part that evaluated to false. In this case, we have used partitioningBy() method to divide the
     * words into small words (with words of length <= 4) and long words (with words of length > 4).
     */
    private static void partitionStrings() {
        String[] string = "you never know what you have until you clean your room".split(" ");
        Stream<String> distinctWords = Arrays.stream(string).distinct();
        Map<Boolean, List<String>> wordBlocks =
                distinctWords.collect(Collectors.partitioningBy(str -> str.length() > 4));
        System.out.println("Short words (len <= 4): " + wordBlocks.get(false));
        System.out.println("Long words (len > 4): " + wordBlocks.get(true));
    }
}
