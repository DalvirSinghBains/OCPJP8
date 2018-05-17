package br.com.dvlima.ocpjp8.topic08._java_io_fundamentals;

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

}
