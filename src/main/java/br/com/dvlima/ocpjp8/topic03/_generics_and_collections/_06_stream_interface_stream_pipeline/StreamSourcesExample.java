package br.com.dvlima.ocpjp8.topic03._generics_and_collections._06_stream_interface_stream_pipeline;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSourcesExample {

	public static void main(String[] args) throws Exception {
		/**
		 * 1. You can use range or iterate factory methods in the IntStream interface
		 */
		IntStream.range(1, 6).forEach(System.out::println);// 1,2,3,4,5
		IntStream.iterate(1, i -> i + 1).limit(5).forEach(System.out::println);// 1,2,3,4,5

		/**
		 * 2. You can use the stream() method in java.util.Arrays class to create a
		 * stream from a given array, as in:
		 */
		Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		Arrays.stream(new Integer[] { 1, 2, 3, 4, 5 });

		/**
		 * 3. We can also create streams using factories and builders. The of() method
		 * is a factory method in the Stream interface:
		 */
		Stream.of(1, 2, 3, 4, 5);
		Stream.of(new Integer[] { 1, 2, 3, 4, 5 });

		Stream.builder().add(1).add(2).add(3).add(4).add(5).build();

		/**
		 * The java.nio.file.Files class has lines() method that returns a
		 * Stream<String>. This code prints the contents of the file FileRead.java in
		 * the current directory.
		 */
		// Files.lines(Paths.get("./StreamSourcesExample.java")).forEach(System.out::println);

		/**
		 * The java.util.Pattern class has splitAsStream() method that returns a
		 * Stream<String>. This code splits the input string java 8 streams based on
		 * whitespace and hence prints the strings java, 8, and streams on the
		 * console.
		 */
		Pattern.compile(" ").splitAsStream("java 8 streams").forEach(System.out::println);

		/**
		 * The java.util.Random class has ints() method that returns an IntStream. It
		 * generates an infinite stream of random integers; so to restrict the number of
		 * integers to 5 integers, we call limit(5) on that stream.
		 */
		new Random().ints().limit(5).forEach(System.out::println);

		/**
		 * The String class has chars() method (newly introduced in Java 8 in
		 * CharSequencean interface that String class implements). This method returns
		 * an IntStream (why IntStream? Remember that there is no equivalent char
		 * specialization for Streams). This code calls sorted() method on this stream,
		 * so the stream elements get sorted in ascending order. Because it is a stream
		 * of integers, this code uses "%c" to explicitly force the conversion from int
		 * to char. prints e h l l o
		 */
		"hello".chars().sorted().forEach(ch -> System.out.printf("%c ", ch));
	}

}
