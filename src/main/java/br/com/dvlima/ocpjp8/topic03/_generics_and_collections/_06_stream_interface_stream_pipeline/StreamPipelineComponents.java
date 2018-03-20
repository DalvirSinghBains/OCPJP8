package br.com.dvlima.ocpjp8.topic03._generics_and_collections._06_stream_interface_stream_pipeline;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamPipelineComponents {

	public static void main(String[] args) {
		Method[] objectMethods = Object.class.getMethods();
		Stream<Method> objectMethodStream = Arrays.stream(objectMethods);
		Stream<String> objectMethodNames = objectMethodStream.map(method -> method.getName());
		Stream<String> uniqueObjectMethodNames = objectMethodNames.distinct();
		uniqueObjectMethodNames.forEach(System.out::println);
	}

}
