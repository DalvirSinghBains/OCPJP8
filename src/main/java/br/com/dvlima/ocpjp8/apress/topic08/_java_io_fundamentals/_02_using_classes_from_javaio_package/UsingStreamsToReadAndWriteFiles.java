package br.com.dvlima.ocpjp8.apress.topic08._java_io_fundamentals._02_using_classes_from_javaio_package;

import java.io.*;
import java.util.*;

public class UsingStreamsToReadAndWriteFiles {

    public static void main(String[] args) {
        //readingTextFiles(args);
        //readingAndWritingTextFiles(args);
        //tokenizingText(args);
        //readingAByteStream(args);
        dataStream();
    }

    private static void writingToAndReadingFromObjectStreams() {
        // A simple class to illustrate object streams: fill a data structure, write it to a
        // temporary file and read it back and print the read data structure

        Map<String, String> presidentsOfUS = new HashMap<>();
        presidentsOfUS.put("Barack Obama", "2009 to --, Democratic Party, 56th term");
        presidentsOfUS.put("George W. Bush", "2001 to 2009, Republican Party, 54th and 55th terms");
        presidentsOfUS.put("Bill Clinton", "1993 to 2001, Democratic Party, 52nd and 53rd terms");

        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.data"))
        ) {
            oos.writeObject(presidentsOfUS);
        } catch (FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        } catch (IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        } // the ObjectOutputStream will auto-close, so don't have to worry about it


        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.data"))
        ) {
            Object obj = ois.readObject();

            // first check if obj is of type Map
            if (obj != null && obj instanceof Map) {
                Map<?, ?> presidents = (Map<?, ?>) obj;
                System.out.println("President name \t Description");
                for (Map.Entry<?, ?> president : presidents.entrySet()) {
                    System.out.printf("%s \t %s %n", president.getKey(),
                            president.getValue());
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        } catch (IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("cannot recognize the class of the object - is the file corrupted?");

        }
    }

    private static void dataStream() {
        // A simple class to illustrate data streams; write constants 0 and 1 in different
        // data type values into a file and read the results back and print them

        // write some data into a data file with hard-coded name "temp.data"
        try (
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("temp.data"))

        ) {
            // write values 1 to 10 as byte, short, int, long, float and double
            // omitting boolean type because an int value cannot be converted to boolean
            for (int i = 0; i < 10; i++) {
                dos.writeByte(i);
                dos.writeShort(i);
                dos.writeInt(i);
                dos.writeLong(i);
                dos.writeFloat(i);
                dos.writeDouble(i);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
            System.exit(-1); // don't proceed – exit the program
        } catch (IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
            System.exit(-1); // don't proceed – exit the program
        }


        // the DataOutputStream will auto-close, so don't have to worry about it
        // now, read the written data and print it to console
        try (
                DataInputStream dis = new DataInputStream(new FileInputStream("temp.data"))
        ) {
            // the order of values to read is byte, short, int, long, float and
            // double since we've written from 0 to 10,
            // the for loop has to run 10 times
            for (int i = 0; i < 10; i++) {
                // %d is for printing byte, short, int or long
                // %f, %g, or %e is for printing float or double
                // %n is for printing newline
                System.out.printf("%d %d %d %d %g %g %n",
                        dis.readByte(),
                        dis.readShort(),
                        dis.readInt(),
                        dis.readLong(),
                        dis.readFloat(),
                        dis.readDouble());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        } catch (IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        } // the DataOutputStream will auto-close, so don't have to worry about it
    }

    private static void readingAByteStream(String[] files) {
        // Byte streams are used for processing files that do not contain human-readable text.

        // check if the passed file is a valid .class file or not.
        // note that this is an elementary version of a checker that checks if the given file
        // is a valid file that is written according to the JVM specification
        // it checks only the magic number

        if (files.length != 1) {
            System.err.println("Pass a valid file name as argument");
            System.exit(-1);
        }

        String fileName = files[0];
        // create a magicNumber byte array with values for four bytes in 0xCAFEBABE
        // you need to have an explicit down cast to byte since
        // the hex values like 0xCA are of type int
        byte[] magicNumber = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};

        try (FileInputStream fis = new FileInputStream(fileName)) {
            // magic number is of 4 bytes use a temporary buffer to read first four bytes
            byte[] u4buffer = new byte[4];

            // read a buffer full (4 bytes here) of data from the file
            if (fis.read(u4buffer) != -1) { // if read was successful
                // the overloaded method equals for two byte arrays
                // checks for equality of contents
                if (Arrays.equals(magicNumber, u4buffer)) {
                    System.out.printf("The magic number for passed file %s matches that of a.class file", fileName);
                } else {
                    System.out.printf("The magic number for passed file %s does not match that of a.class file", fileName);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("file does not exist with the given file name ");
        } catch (IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        }
    }

    private static void tokenizingText(String[] files) {
        // read the input file and convert it into "tokens" of words;
        // convert the words to same case (lower case), remove duplicates, and print the words

        if (files.length != 1) {
            System.err.println("pass the name of the file to be read as an argument");
            System.exit(-1);
        }

        String fileName = files[0];
        // use a TreeSet<String> which will automatically sort the words in alphabetical order

        Set<String> words = new TreeSet<>();

        try (Scanner tokenizingScanner = new Scanner(new FileReader(fileName))) {
            // set the delimiter for text as non-words (special characters,
            // white-spaces, etc), meaning that all words other than punctuation
            // characters, and white-spaces will be returned
            tokenizingScanner.useDelimiter("\\W");

            while (tokenizingScanner.hasNext()) {
                String word = tokenizingScanner.next();
                if (!word.equals("")) { // process only non-empty strings
                    // convert to lowercase and then add to the set
                    words.add(word.toLowerCase());
                }
            }
            // now words are in alphabetical order without duplicates,
            // print the words separating them with tabs
            words.forEach(word -> System.out.print(word + '\t'));
        } catch (FileNotFoundException fnfe) {
            System.err.println("Cannot read the input file - pass a valid file name");
        }

    }

    private static void readingAndWritingTextFiles(String[] files) {
        if (files.length != 2) {
            System.err.println("Incorrect syntax. Correct syntax: Copy SrcFile DstFile");
            System.exit(-1);
        }

        String srcFile = files[0];
        String dstFile = files[1];

        // try opening the source and destination file
        // with FileReader and FileWriter
        try (
                BufferedReader inputFile = new BufferedReader(new FileReader(srcFile));
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(dstFile))
        ) {
            int ch = 0;
            // while there are characters to fetch, read the characters from
            // source stream and write them to the destination stream
            while ((ch = inputFile.read()) != -1) {
                // ch is of type int - convert it back to char before writing it
                outputFile.write((char) ch);
            }
            // no need to call flush explicitly for outputFile - the close()
            // method will first call flush before closing the outputFile stream

            //When you’re using buffered I/O in your programs, it’s a good idea to call the flush() method explicitly in
            //places where you want to ensure that all pending characters or data is flushed.
        } catch (FileNotFoundException fnfe) {
            // the passed file is not found ...
            System.err.println("Cannot open the file " + fnfe.getMessage());
        } catch (IOException ioe) {
            // some IO error occurred when reading the file ...
            System.err.printf("Error when processing file; exiting ... ");
        }
        // try-with-resources will automatically release FileReader object
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
            try (
                    FileReader inputFile = new FileReader(file)
            ) {
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
