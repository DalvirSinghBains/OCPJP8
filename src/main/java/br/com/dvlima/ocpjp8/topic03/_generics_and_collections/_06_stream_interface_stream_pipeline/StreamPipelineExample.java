package br.com.dvlima.ocpjp8.topic03._generics_and_collections._06_stream_interface_stream_pipeline;

import java.util.Arrays;

public class StreamPipelineExample {

	public static void main(String[] args) {
		Arrays.stream(Object.class.getMethods()) // source
				.map(method -> method.getName())// intermediate op
				.distinct() // intermediate op
				.forEach(System.out::println);// terminal operation
	}

}
