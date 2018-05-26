package br.com.dvlima.ocpjp8.apress.topic06._exceptions_and_assertions._03_autoclose_resources_with_trywithresources;

import java.util.Scanner;

/**
 * try-with-resources statement without any explicit catch or finally
 */
public class TryWithResources2 {

    public static void main(String[] args) {
        System.out.println("Type an integer in the console: ");

        try (Scanner consoleScanner = new Scanner(System.in)) {
            System.out.println("You typed the integer value: " + consoleScanner.nextInt());
        }
    }

}
