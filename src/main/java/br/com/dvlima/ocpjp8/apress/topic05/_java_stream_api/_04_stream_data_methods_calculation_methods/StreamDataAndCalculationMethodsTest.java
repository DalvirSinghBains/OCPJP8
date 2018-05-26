package br.com.dvlima.ocpjp8.apress.topic05._java_stream_api._04_stream_data_methods_calculation_methods;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDataAndCalculationMethodsTest {

    public static void main(String[] args) {
        wordsCalculation();

        wordStatistics();
    }

    private static void wordsCalculation() {
        String[] string = "you never know what you have until you clean your room".split(" ");
        System.out.println(Arrays.stream(string).min(String::compareTo).get());//clean

        Comparator<String> lengthComparator = (s1, s2) -> s1.length() - s2.length();
        Stream.of(string).min(lengthComparator).ifPresent(System.out::println);//clean
    }

    private static void wordStatistics() {

        String limerick = "There was a young lady named Bright " +
                "who traveled much faster than light " +
                "She set out one day " +
                "in a relative way " +
                "and came back the previous night ";

        IntSummaryStatistics wordStatistics =
                Pattern.compile(" ")
                        .splitAsStream(limerick)
                        .mapToInt(String::length)
                        .summaryStatistics();

        System.out.printf(" Number of words = %d \n Sum of the length of the words = %d \n" +
                        " Minimum word size = %d \n Maximum word size %d \n" +
                        " Average word size = %f \n", wordStatistics.getCount(),
                wordStatistics.getSum(), wordStatistics.getMin(),
                wordStatistics.getMax(), wordStatistics.getAverage());

        IntStream.of(10, 20, 30, 40).sum();

        IntStream.of(10, 20, 30, 40).reduce(0, ((sum, val) -> sum + val));

        // factorial of 5
        System.out.println(IntStream.rangeClosed(1, 5).reduce((x, y) -> (x * y)).getAsInt());
        // prints: 120
    }
}
