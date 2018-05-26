package br.com.dvlima.ocpjp8.apress.topic05._java_stream_api._01_extract_data_from_an_object;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class ExtractDataFromStream {

	public static void main(String[] args) {
		long count = Stream.of(1, 2, 3, 4, 5).map(i -> i * i).count();
		System.out.println(count);

		/**
		 * The peek() method is meant primarily for debugging purposes. It helps us
		 * understand how the elements are transformed in the pipeline. Do not use it in
		 * production code
		 */
		long count2 = Stream.of(1, 2, 3, 4, 5)//
				.peek(i -> System.out.printf("[%d] ", i))//
				.map(i -> i * i)//
				.peek(i -> System.out.printf("(%d) ", i))//
				.count();
		// 5
		// [1] (1) [2] (4) [3] (9) [4] (16) [5] (25)

		/**
		 * You can use map() and peek() methods in primitive versions of Stream<T>; then
		 * following code snippet uses a DoubleStream:
		 */
		DoubleStream.of(1.0, 4.0, 9.0)//
				.map(Math::sqrt)//
				.peek(System.out::println)//
				.sum();
	}

}
