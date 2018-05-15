package br.com.dvlima.ocpjp8.topic06._exceptions_and_assertions._02_catch_multicatch_and_finally;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TryAndCatchStatements {
    //Java provides the try and catch keywords to handle any exceptions that can get thrown in the code you write.
}

// A simple progam to accept an integer from user in normal case,
// otherwise prints an error message

class ScanInt2 {
    /*
    Type an integer in the console:
    ten
    Error: You typed some text that is not an integer value...
     */
    public static void main(String[] args) {
        System.out.println("Type an integer in the console: ");
        Scanner consoleScanner = new Scanner(System.in);
        try {
            System.out.println("You typed the integer value: " + consoleScanner.nextInt());
        } catch (InputMismatchException ime) {
            // nextInt() throws InputMismatchException in case anything
            // other than an integer is typed in the console; so handle it
            System.out.println("Error: You typed some text that is not an integer value...");
        }
    }
}

/**
 * Multiple Catch Blocks
 */
// A program that scans an integer from a given string
class ScanInt3 {
//    The string to scan integer from it is: 100
//    The integer value scanned from string is: 100

//    What happens if you modify the program so that the string contains a non-integer value, as in
//    String integerStr = "hundred";
//    The string to scan integer from it is: hundred
//    Error: Cannot scan an integer from the given string

//  Now, what if you modify the program in Listing 7-4 so that the string contains an empty string, as in
//  String integerStr = "";
//  Exception in thread "main" java.util.NoSuchElementException
//    at java.util.Scanner.throwFor(Scanner.java:907)
//    at java.util.Scanner.next(Scanner.java:1530)
//    at java.util.Scanner.nextInt(Scanner.java:2160)
//    at java.util.Scanner.nextInt(Scanner.java:2119)
//    at ScanInt3.main(ScanInt.java:11)

    public static void main(String[] args) {
        String integerStr = "100";
        System.out.println("The string to scan integer from it is: " + integerStr);
        Scanner consoleScanner = new Scanner(integerStr);
        try {
            System.out.println("The integer value scanned from string is: " +
                    consoleScanner.nextInt());
        } catch (InputMismatchException ime) {
            // nextInt() throws InputMismatchException in case
            // anything other than an integeris provided in the string
            System.out.println("Error: Cannot scan an integer from the given string");
        }
    }
}

// A program that scans an integer from a given string
class ScanInt4 {
    //    The string to scan integer from it is:
//    Error: Cannot scan an integer from the given string
    public static void main(String[] args) {
        String integerStr = "";
        System.out.println("The string to scan integer from it is: " + integerStr);
        Scanner consoleScanner = new Scanner(integerStr);
        try {
            System.out.println("The integer value scanned from string is: " + consoleScanner.nextInt());
        } catch (InputMismatchException ime) {
            System.out.println("Error: Cannot scan an integer from the given string");
        } catch (NoSuchElementException nsee) {
            System.out.println("Error: Cannot scan an integer from the given string");
        } catch (IllegalStateException ise) {
            System.out.println("Error: nextInt() called on a closed Scanner object");
        }
    }
}

// Java provides a feature named multi-catch blocks in which you can combine multiple catch handlers.
// Let’s use this feature to combine the catch clauses of NoSuchElementException and IllegalStateException

// A program that illustrates multi-catch blocks
class ScanInt5 {
// in a multi-catch block, you cannot combine catch handlers for two exceptions that share a base and
// derived-class relationship. You can only combine catch handlers for exceptions that do not share the
// parent-child relationship between them.

    // the Garbage Collector (GC) is responsible for releasing only memory resources. if you are using any class that
// acquires system resources, it is your responsibility to release them by calling the close() method on that object.
    public static void main(String[] args) {
        String integerStr = "";
        System.out.println("The string to scan integer from it is: " + integerStr);
        Scanner consoleScanner = new Scanner(integerStr);
        try {
            System.out.println("The integer value scanned from string is: " + consoleScanner.nextInt());
        } catch (NoSuchElementException | IllegalStateException multie) {
            System.out.println("Error: An error occured while attempting to scan the integer");
        }
    }
}

//Releasing Resources

class ScanInt6 {
    /*
    Type an integer in the console:
    10
    You typed the integer value: 10
    Done reading the text... closing the Scanner
     */
    /*
    Type an integer in the console:
    ten
    Error: Encountered an exception and could not read an integer from the console...
    Exiting the program - restart and try the program again!
     */
    public static void main(String[] args) {
        System.out.println("Type an integer in the console: ");
        Scanner consoleScanner = new Scanner(System.in);
        try {
            System.out.println("You typed the integer value: " + consoleScanner.nextInt());
            System.out.println("Done reading the text... closing the Scanner");
            consoleScanner.close();
        } catch (Exception e) {
            // call all other exceptions here ...
            System.out.println("Error: Encountered an exception and could not read an integer from the console...");
            System.out.println("Exiting the program - restart and try the program again!");
        }
    }
}

class ScanInt7 {
    // if you call System.exit() inside a method, it will abnormally terminate the program. so, if the calling method
    // has a finally block, it will not be called and resources may leak. For this reason, it is a bad programming
    // practice to call System.exit() to terminate a program

    /*
    Type an integer in the console:
    10
    You typed the integer value: 10
    Done reading the integer... closing the Scanner
     */
    /*
    Type an integer in the console:
    ten
    Error: Encountered an exception and could not read an integer from the console...
    Exiting the program - restart and try the program again!
    Done reading the integer... closing the Scanner
     */
    public static void main(String[] args) {
        System.out.println("Type an integer in the console: ");
        Scanner consoleScanner = new Scanner(System.in);
        try {
            System.out.println("You typed the integer value: " + consoleScanner.nextInt());
        } catch (Exception e) {
            // call all other exceptions here ...
            System.out.println("Error: Encountered an exception and could not read an integer from the console...");
            System.out.println("Exiting the program - restart and try the program again!");
        } finally {
            System.out.println("Done reading the integer... closing the Scanner");
            consoleScanner.close();
        }
    }

    //This method will always return false because finally is always invoked though it is unintuitive.
    boolean returnTest() {
        try {
            return true;
        } finally {
            return false;
        }
    }
}

// The Throws Clause

