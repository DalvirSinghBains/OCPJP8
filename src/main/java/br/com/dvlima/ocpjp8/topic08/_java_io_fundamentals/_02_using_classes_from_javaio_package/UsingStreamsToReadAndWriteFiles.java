package br.com.dvlima.ocpjp8.topic08._java_io_fundamentals._02_using_classes_from_javaio_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UsingStreamsToReadAndWriteFiles {

    public static void main(String[] args) {
        readingTextFiles(args);

    }


    private static void readingTextFiles(String[] files) {
        // implements a simplified version of "type" command provided in Windows given
        // a text file name(s) as argument, it prints the content of the text file(s) on console
        if (files.length == 0) {
            System.err.println("pass the name of the file(s) as argument");
            System.exit(-1);
        }

        // process each file passed as argument
        for (String file : files) {
            // try opening the file with FileReader
            try (FileReader inputFile = new FileReader(file)) {
                int ch = 0;
                // while there are characters to fetch, read, and print the
                // characters when EOF is reached, read() will return -1,
                // terminating the loop
                while ((ch = inputFile.read()) != -1) {
                    // ch is of type int - convert it back to char
                    // before printing
                    System.out.print((char) ch);
                }
            } catch (FileNotFoundException fnfe) {
                // the passed file is not found ...
                System.err.printf("Cannot open the given file %s ", file);
            } catch (IOException ioe) {
                // some IO error occurred when reading the file ...
                System.err.printf("Error when processing file %s... skipping it", file);
            }
            // try-with-resources will automatically release FileReader object
        }
    }
}
