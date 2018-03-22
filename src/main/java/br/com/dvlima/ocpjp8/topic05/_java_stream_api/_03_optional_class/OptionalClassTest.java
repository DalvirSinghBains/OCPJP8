package br.com.dvlima.ocpjp8.topic05._java_stream_api._03_optional_class;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class OptionalClassTest {
    public static void main(String[] args) {
        getTheValueFromOptional();

        creatingOptionalObjects();

        optionalStream();

        primitiveVersionsOfOptional();
    }

    private static void getTheValueFromOptional() {
        selectHighestTemperature(Stream.of(24.5, 23.6, 27.9, 21.1, 23.5, 25.5, 28.3));//Optional[28.3]
        selectHighestTemperature(Stream.of());//Optional.empty

        //To get the value from Optional, you can use isPresent() and get() methods
        selectHighestTemperatureIfPresent(Stream.of(24.5, 23.6, 27.9, 21.1, 23.5, 25.5, 28.3));//28.3
        selectHighestTemperatureIfPresent(Stream.of());//
    }

    private static void selectHighestTemperature(Stream<Double> temperatures) {
        System.out.println(temperatures.max(Double::compareTo));
    }

    private static void selectHighestTemperatureIfPresent(Stream<Double> temperatures) {
        Optional<Double> max = temperatures.max(Double::compareTo);
        max.ifPresent(System.out::println);
        /*if(max.isPresent()) {
            System.out.println(max.get());
        }*/
    }

    /**
     * Creating Optional Objects
     */
    private static void creatingOptionalObjects() {

        Optional<String> empty = Optional.empty();

        Optional<String> nonEmptyOptional = Optional.of("abracadabra");

        // crashes with a NullPointerException
        //Optional<String> nullStr = Optional.of(null);
        //System.out.println(nullStr);

        //If you want to create an Optional object that has null value, then you can instead use ofNullable() method:
        Optional<String> nullableStr = Optional.ofNullable(null);
        // prints: Optional.empty
        System.out.println(nullableStr);
    }

    /**
     * Optional Stream
     */
    private static void optionalStream() {
        Optional<String> string = Optional.of(" abracadabra ");
        string.map(String::trim).ifPresent(System.out::println);

        //orElse() or orElseThrow() methods
        Optional<String> stringOfNullable = Optional.ofNullable(null);
        System.out.println(stringOfNullable.map(String::length).orElse(-1));

        Optional<String> stringThrows = Optional.ofNullable(null);
        System.out.println(stringThrows.map(String::length).orElseThrow(IllegalArgumentException::new));
    }

    /**
     * Primitive Versions of Optional<T>
     */
    private static void primitiveVersionsOfOptional() {
        //It is better to use DoubleStream and OptionalDouble, which are primitive type versions for double for
        // Stream<T> and Optional<T> respectively.
        selectHighestTemperature(DoubleStream.of(24.5, 23.6, 27.9, 21.1, 23.5, 25.5, 28.3));
    }

    public static void selectHighestTemperature(DoubleStream temperatures) {
        OptionalDouble max = temperatures.max();
        max.ifPresent(System.out::println);
    }
}
