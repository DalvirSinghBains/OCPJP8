package br.com.dvlima.ocpjp8.apress.topic06._exceptions_and_assertions._01_try_catch_throw_statements;

import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        if (args.length == 0) {
            // no arguments passed - throw an exception
            throw new IllegalArgumentException("No input passed to echo command");
        }

        for (String str : args) {
            // command-line arguments are separated and passed as an array
            // print them by adding a space between the array elements
            System.out.print(str + " ");

        }
    }
}


//Unhandled Exceptions
class ScanInt1 {
    /*
    Type an integer in the console:
    ten
    Exception in thread "main" java.util.InputMismatchException
            at java.util.Scanner.throwFor(Scanner.java:909)
            at java.util.Scanner.next(Scanner.java:1530)
            at java.util.Scanner.nextInt(Scanner.java:2160)
            at java.util.Scanner.nextInt(Scanner.java:2119)
            at ScanInt.main(ScanInt1.java:7)
     */
    public static void main(String[] args) {
        System.out.println("Type an integer in the console: ");
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("You typed the integer value: " + consoleScanner.nextInt());
    }
}