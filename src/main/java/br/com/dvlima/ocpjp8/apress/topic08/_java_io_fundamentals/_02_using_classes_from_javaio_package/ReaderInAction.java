package br.com.dvlima.ocpjp8.apress.topic08._java_io_fundamentals._02_using_classes_from_javaio_package;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class ReaderInAction {

    public static void main(String[] args) throws IOException {
//        usingBufferedReader();
//        usingNewBufferedReader();
        usingBufferWriter();
    }

    private static void usingBufferWriter() throws IOException {
        Path path = Paths.get("files/some-text.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println("Hello world!");
        }
    }

    private static void usingNewBufferedReader() throws IOException {
        // 1
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("files/luci2.txt"));) {
            reader.lines().forEach(System.out::println);
        }

        //2 Stream extends StreamBase where is AutoCloseable
        try (Stream<String> lines = Files.newBufferedReader(Paths.get("files/luci2.txt")).lines()) {
            lines.forEach(System.out::println);
        }
    }

    private static void usingBufferedReader() throws IOException {
        File file = new File("files/luci2.txt");

        try (Reader reader = new FileReader(file)) {

            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
    }

}
