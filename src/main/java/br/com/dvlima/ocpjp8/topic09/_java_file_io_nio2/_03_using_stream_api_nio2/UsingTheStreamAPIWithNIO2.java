package br.com.dvlima.ocpjp8.topic09._java_file_io_nio2._03_using_stream_api_nio2;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class UsingTheStreamAPIWithNIO2 {

    public static void main(String[] args) throws IOException {
        listFiles();
    }

    private static void usingLinesMethod(String[] files) {
        // implements a simplified version of "type" command provided in Windows;
        // given a text file name(s) as argument, it prints the content of the file(s)
        if (files.length == 0) {
            System.err.println("pass the name of the file(s) as argument");
            System.exit(-1);
        }
        // process each file passed as argument
        Arrays.stream(files).forEach(UsingTheStreamAPIWithNIO2::processFile);
    }

    private static void processFile(String file) {
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            lines.forEach(System.out::println);
        } catch (IOException ioe) {
            System.err.println("IOException occurred when reading the file... exiting");
            System.exit(-1);
        }
    }

    private static void listFiles() throws IOException {
        try (Stream<Path> entries = Files.list(Paths.get("."))) {
            entries.forEach(System.out::println);
        }

        Files.list(Paths.get("."))
                .map(path -> path.toAbsolutePath())
                .forEach(System.out::println);

        // Note that the list() method does not recursively traverse the entries in the given Path.
        // To recursively traverse the directories, you can use the Files.walk() method:
        /*
            The FileVisitOption has one enumeration value: FileVisitOption.FOLLOW_LINKS. You can pass that to the walk() method.
            You can also specify maxDepth: the limit on the nesting level for recursively traversing the directory entries
         */
        try (
                Stream<Path> entries = Files.walk(Paths.get("."), 4, FileVisitOption.FOLLOW_LINKS)
        ) {
            long numOfEntries = entries.count();
            System.out.printf("Found %d entries in the current path", numOfEntries);
        }

        //FindFiles
        BiPredicate<Path, BasicFileAttributes> predicate = (path, attrs) -> attrs.isRegularFile() && path.toString().endsWith("class");

        try (
                Stream<Path> entries = Files.find(Paths.get("."), 4, predicate)
        ) {
            entries.limit(100).forEach(System.out::println);
        }
    }
}
