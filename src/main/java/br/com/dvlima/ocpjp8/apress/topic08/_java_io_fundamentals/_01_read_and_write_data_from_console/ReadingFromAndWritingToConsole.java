package br.com.dvlima.ocpjp8.apress.topic08._java_io_fundamentals._01_read_and_write_data_from_console;

import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;

public class ReadingFromAndWritingToConsole {

    public static void main(String[] args) {
        // read();
        // streamTest();
        //consoleClass();
        formattedOutputWithTheConsoleClass();
    }

    private static void formattedOutputWithTheConsoleClass(){
        FormattedTable formattedTable = new FormattedTable();
        Console console = System.console();
        if(console != null) {
            formattedTable.line(console);
            formattedTable.printHeader(console);
            formattedTable.line(console);
            formattedTable.printRow(console, "Demando", 100, 122);
            formattedTable.printRow(console, "Mushi", 80, 100);
            formattedTable.printRow(console, "Peale", 150, 180);
            formattedTable.line(console);
        }
    }

    private static void consoleClass() {
        // Important Methods in the Console Class

        // Reader reader()
        /*Returns the Reader object associated with this Console object; can perform read operations through this
        returned reference.*/

        //PrintWriter writer()
        // Returns the PrintWriter object associated with this Console object; can perform write operations through this
        // returned reference.

        //String readLine()
        // Reads a line of text String (and this returned string object does not include any line termination characters);
        // returns null if it fails (e.g., the user pressed Ctrl+Z or Ctrl+D in the console)

        //String readLine(String fmt, Object... args)
        // Same as the readLine() method, but it first prints the string fmt.

        // get the System console object
        Console console = System.console();
        if (console == null) {
            System.err.println("Cannot retrieve console object - are you running your application from an IDE ? Exiting the application ...");
            System.exit(-1); // terminate the application
        }
        // read a line and print it through printf
        console.printf(console.readLine());
    }

    private static void streamTest() {
        // When you execute this code segment, the program will create a file named “log.txt”
        // and print the string “Test output to System.out” in that file.
        try {
            PrintStream ps = new PrintStream("log.txt");
            System.setOut(ps);
            System.out.println("Test output to System.out");
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    private static void read() {
        System.out.print("Type a character: ");
        int val = 0;

        try {
            // the return type of read is int, but it returns a byte value!
            val = System.in.read();
        } catch (IOException ioe) {
            System.err.println("Cannot read input " + ioe);
            System.exit(-1);
        }
        System.out.println("You typed: " + val);
    }
}
