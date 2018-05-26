package br.com.dvlima.ocpjp8.apress.topic06._exceptions_and_assertions._04_custom_exceptions_auto_closeable_resources;

import java.util.NoSuchElementException;
import java.util.Scanner;

// a custom "unchecked exception" that is meant to be thrown
// when the input provided by the user is invalid
class InvalidInputException extends RuntimeException {
    // default constructor
    public InvalidInputException() {
        super();
    }

    // constructor that takes the String detailed information we pass while
    // raising an exception
    public InvalidInputException(String str) {
        super(str);
    }

    // constructor that remembers the cause of the exception and
    // throws the new exception
    public InvalidInputException(Throwable originalException) {
        super(originalException);
    }

    // first argument takes detailed information string created while
    // raising an exception
    // and the second argument is to remember the cause of the exception
    public InvalidInputException(String str, Throwable originalException) {
        super(str, originalException);
    }
}

// class for testing the custom exception InvalidInputException
class CustomExceptionTest {
    public static int readIntFromConsole() {
        Scanner consoleScanner = new Scanner(System.in);
        int typedInt = 0;
        try {
            typedInt = consoleScanner.nextInt();
        } catch (NoSuchElementException nsee) {
            System.out.println("Wrapping up the exception and throwing it...");
            throw new InvalidInputException("Invalid integer input typed in console", nsee);
        } catch (Exception e) {
            // call all other exceptions here ...
            System.out.println("Error: Encountered an exception and could not read an integer from the console...");
        }
        return typedInt;
    }


    /*
    Type an integer in the console:
    one
    Wrapping up the exception and throwing it...
    Error: Invalid input in console...
    The current caught exception is of type: InvalidInputException: Invalid integer input typed
    in console
    The originally caught exception is of type: java.util.InputMismatchException
     */
    public static void main(String[] args) {
        System.out.println("Type an integer in the console: ");
        try {
            System.out.println("You typed the integer value: " + readIntFromConsole());
        } catch (InvalidInputException iie) {
            System.out.println("Error: Invalid input in console... ");
            System.out.println("The current caught exception is of type: " + iie);
            System.out.println("The originally caught exception is of type: " +iie.getCause());
        }
    }
}
