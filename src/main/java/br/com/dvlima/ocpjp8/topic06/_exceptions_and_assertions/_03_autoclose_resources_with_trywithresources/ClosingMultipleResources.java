package br.com.dvlima.ocpjp8.topic06._exceptions_and_assertions._03_autoclose_resources_with_trywithresources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by danilo.fernandes on 26/03/2018.
 */
public class ClosingMultipleResources {

    public static final int CHUNK = 1024; // to help copy chunks of 1KB

    public static void main(String[] args) {
        /*if (args.length == 0) {
            System.out.println("Pass the name of the file in the current directory to be zipped as an argument");
            System.exit(-1);
        }*/

        String fileName = "";
        System.out.println("Pass the name of the file in the current directory to be zipped: ");

        try (Scanner consoleScanner = new Scanner(System.in)) {
            consoleScanner.next();
        }

        // name of the zip file is the input file name with the suffix ".zip"
        String zipFileName = fileName + ".zip";
        byte[] buffer = new byte[CHUNK];

        // these constructors can throw FileNotFoundException
        try (ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(zipFileName));
             FileInputStream fileIn = new FileInputStream(fileName)) {

            // putNextEntry can throw IOException
            zipFile.putNextEntry(new ZipEntry(fileName));
            int lenRead = 0; // variable to keep track of number of bytes

            // successfully read
            // copy the contents of the input file into the zip file
            while ((lenRead = fileIn.read(buffer)) > 0) {
                // both read and write methods can throw IOException
                zipFile.write(buffer, 0, lenRead);
            }
            // the streams will be closed automatically because they are
            // within try-with-resources statement
        }
        // this can result in multiple exceptions thrown from the try block;
        // use "suppressed exceptions" to get the exceptions that were suppressed!
        catch (Exception e) {
            System.out.println("The caught exception is: " + e);
            System.out.print("The suppressed exceptions are: ");
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println(suppressed);
            }
        }
    }


    /*public static void main(String[] args) throws Exception {
        try (ClassAutoCloseable autoCloseable = new ClassAutoCloseable()) {

        }
    }*/
}

//    Points to Remember
// You cannot assign to the resource variables declared in the try-with-resources within the body of the
// try-with-resources statement. This is to make sure that the same resources acquired in the try-with-resources header
// are released in the finally block.

// It is a common mistake to close a resource explicitly inside the try-with-resources statement. Remember that
// try-with-resources expands to calling the close() method in the finally block, so the expanded code will have
// a double call to the close() method if you explicitly provide a close() method. Consider the following code:
/*
    try(Scanner consoleScanner = new Scanner(System.in)) {
          System.out.println("You typed the integer value: " +
          consoleScanner.nextInt());
          consoleScanner.close();
          // explicit call to close() method - remember that try-with-resources
          // statement will also expand to calling close() in finally method;
          // hence this will result in call to close() method in Scanner twice!
    }
 */

// The documentation of the close() method in the Scanner class says that if the scanner object is already closed,
// then invoking the method again will have no effect. So, you are safe in this case. However, in general, you cannot
// expect all the resources to have implemented a close() method that is safe to call twice. So, it is a bad practice
// to explicitly call the close() method inside a try-with-resource statement.