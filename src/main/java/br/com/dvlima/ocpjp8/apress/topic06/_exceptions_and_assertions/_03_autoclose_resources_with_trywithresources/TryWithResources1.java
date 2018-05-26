package br.com.dvlima.ocpjp8.apress.topic06._exceptions_and_assertions._03_autoclose_resources_with_trywithresources;

import java.util.Scanner;

/**
 * So, the benefit of a try-with-resources statement is that it simplifies your life by not having to provide
 * finally blocks explicitly. However, you still need to provide necessary catch blocks.
 */
public class TryWithResources1 {
    public static void main(String[] args) {

        try (Scanner consoleScanner = new Scanner(System.in)) {
            System.out.println("You typed the integer value: " + consoleScanner.nextInt());
        } catch (Exception e) {
            // catch all other exceptions here ...
            System.out.println("Error: Encountered an exception and could not read an integer from the console...");
            System.out.println("Exiting the program - restart and try the program again!");
        }
    }
}
