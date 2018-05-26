package br.com.dvlima.ocpjp8.apress.topic08._java_io_fundamentals;

/**
 Java I/O Fundamentals

 - Read and write data from the console
 - Use BufferedReader, BufferedWriter, File, FileReader, FileWriter, FileInputStream,
    FileOutputStream, ObjectOutputStream, ObjectInputStream, and PrintWriter in the 
    java.io package.
 */
public class JavaIOFundamentals {


/*  Here are some points that might prove useful on your OCPJP 8 exam:
    • If you do not specify any string formatting specifier, the printf() method will not print anything from the given arguments!

    • Flags such as "-" and “0" make sense only when you specify width with the format specifier string.

    • You can also print the % character in a format string; however, you need to use an escape sequence for it. In
    format specifier strings, % is an escape character, which means you need to use %% to print a single %.

    • You can use the argument index feature (an integer value followed by $ symbol) to explicitly refer to the arguments
    by their index position. For example, the following prints “world hello” because the order of arguments are reversed:

    console.printf("%2$s %1$s %n", "hello", "world");

    // $2 refers to the second argument ("world") and
    // $1 refers to the first argument ("hello")

    • The < symbol in a format string supports relative index with which you can reuse the argument matched by the previous format specifier. For example, assuming console is a valid Console object, the following code segment prints “10 a 12”: console.printf("%d %<x %<o", 10);
    // 10 – the decimal value, a – the hexadecimal value of 10, and
    // 12 – the octal value of 10
    • If you do not provide the intended input data type as expected by the format string, then you can get an IllegalFormatConversionException. For instance, if you provide a string instead of an expected integer in your printRow() method implementation, you will get following exception:
    Exception in thread "main" java.util.IllegalFormatConversionException:
    d != java.lang.String
    */

//Differences Between Character Streams and Byte Streams

/* Character streams
   - Meant for reading or writing to character- or text-based I/O such as text files, text documents, XML, and HTML files.
   - Data dealt with is 16-bit Unicode characters.
   - Input and output character streams are called readers and writers, respectively.
   - The abstract classes of Reader and Writer and their derived classes in the java.io package provide support for character streams.
 */

/* Byte streams
    - Meant for reading or writing to binary data I/O such as executable files, image files, and files in low-level file formats such as .zip, .class, .obj, and .exe.
    - Data dealt with is bytes (i.e., units of 8-bit data).
    - Input and output byte streams are simply called input streams and output streams, respectively.
    - The abstract classes of InputStream and OutputStream and their derived classes in the java.io package provide support for byte streams.
 */

//    Read and write data from the console

// The public static fields in, out, and err in java.lang.System class respectively represent the standard input, output and error streams.
// System.in is of type java.io.InputStream and System.out and System.err are of type java.io.PrintStream.

// You can redirect standard streams by calling the methods System.setIn, System.setOut and System.setError.

// You can obtain a reference to the console using the System.console() method; if the JVM is not associated with any console,
// this method will fail and return null.

// Many methods are provided in Console-support formatted I/O. You can use the printf() and format() methods available
// in the Console class to print formatted text; the overloaded readLine() and readPassword() methods take format strings as arguments.

// The template of format specifiers is: %[flags][width][.precision]datatype_specifier Each format specifier starts with
// the % sign, followed by flags, width, and precision information, and ending with a data type specifier.
// In the format string, the flags, width, and precision information are optional but the % sign and data type specifier are mandatory.

// Use the readPassword() method for reading secure strings such as passwords. It is recommended to use Array’s fill()
// method to “empty” the password read into the character array (to avoid malicious access to the typed passwords).

/** Use in the java.io package
 * BufferedReader,
 * BufferedWriter,
 * File,
 * FileReader,
 * FileWriter,
 * FileInputStream,
 * FileOutputStream,
 * ObjectOutputStream,
 * ObjectInputStream,
 * PrintWriter
 * */

// The java.io package has classes supporting both character streams and byte streams.

// You can use character streams for text-based I/O. Byte streams are used for data based I/O.

// Character streams for reading and writing are called readers and writers respectively
// (represented by the abstract classes of Reader and Writer).

// Byte streams for reading and writing are called input streams and output streams respectively
// (represented by the abstract classes of InputStream and OutputStream).

// You should only use character streams for processing text files (or human-readable files), and byte streams for data files.
// If you try using one type of stream instead of another, your program won’t work as you would expect;
// even if it works by chance, you’ll get nasty bugs. So don’t mix up streams, and use the right stream for a given task at hand.

// For both byte and character streams, you can use buffering. The buffer classes are provided as wrapper classes for
// the underlying streams. Using buffering will speed up the I/O when performing bulk I/O operations.

// For processing data with primitive data types and strings, you can use data streams.

// You can use object streams (classes ObjectInputStream and ObjectOutputStream) for reading and writing objects in memory to files and vice versa.

}
