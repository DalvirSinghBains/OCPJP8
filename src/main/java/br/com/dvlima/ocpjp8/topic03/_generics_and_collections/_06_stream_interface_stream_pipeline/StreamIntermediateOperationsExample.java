package br.com.dvlima.ocpjp8.topic03._generics_and_collections._06_stream_interface_stream_pipeline;

import java.util.stream.Stream;

public class StreamIntermediateOperationsExample {

	public static void main(String[] args) {

		System.out.println(Stream.of("d", "d", "s").count());// 3

		System.out.println(Stream.of(1, 2, 3, 4, 5).map(i -> i * i).count());// 5

		Stream.of(1, 2, 3, 4, 5).map(i -> i * i).forEach(System.out::print);// 1491625

		Stream.of(1, 2, 3, 4, 5).map(i -> i * i).peek(i -> System.out.printf("%d ", i)).count();// 1 4 9 16 25

		Stream.of(1, 2, 3, 4, 5) //
				.peek(i -> System.out.printf("[%d]", i))//
				.map(i -> i * i)//
				.peek(i -> System.out.printf("(%d)", i))//
				.count();
		// [1](1)[2](4)[3](9)[4](16)[5](25)
	}
}
