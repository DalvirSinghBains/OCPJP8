package br.com.dvlima.ocpjp8.topic09._java_file_io_nio2._01_path_interface;

import java.io.IOException;
import java.nio.file.*;

public class UsingThePathInterface {

    public static void main(String[] args) {
        //gettingPathInformation();
    }

    private static void comparingTwoPaths(){
        Path path1 = Paths.get("Test");
        Path path2 = Paths.get("D:\\OCPJP\\programs\\NIO2\\Test");

        // comparing two paths using compareTo() method
        /**
         It first compares two paths using the compareTo() method,
         which compares paths character by character and returns an integer.
         In this case, because one path is a relative path and another one is an absolute path,
         you first expect to get a message that says the paths are not equal.
         */
        System.out.println("(path1.compareTo(path2) == 0) is: " + (path1.compareTo(path2) == 0));
        //false

        // comparing two paths using equals() method
        /**
         Then you compare both paths using equals(). The result is the same,
         which means even if the two Path objects are pointing to the same file/directory, it is possible for equals() to return false.
         You need to make sure both paths are absolute paths.
         */
        System.out.println("path1.equals(path2) is: " + path1.equals(path2));
        //false

        // comparing two paths using equals() method with absolute path
        /**
         In the next step, you convert the relative path to an absolute path and then compare them using equals().
         This time both paths match.
         */
        System.out.println("path2.equals(path1.toAbsolutePath()) is " + path2.equals(path1.toAbsolutePath()));
        //true

        // even if two Path objects point to the same file/directory, it is not guaranteed that the equals() method will return true.
        // You need to make sure both are absolute and normalized paths for an equality comparison to succeed for paths.
    }

    private static void gettingPathInformation() {

        // create a Path object by calling static method get() in Paths class
        Path testFilePath = Paths.get("/Users/danilolima/App/package.json");

        // retrieve basic information about path
        System.out.println("Printing file information: ");
        System.out.println("\t file name: " + testFilePath.getFileName());
        System.out.println("\t root of the path: " + testFilePath.getRoot());
        System.out.println("\t parent of the target: " + testFilePath.getParent());

        // print path elements
        System.out.println("Printing elements of the path: ");
        testFilePath.forEach(System.out::println);

        //The toUri() method returns the URI (a path that can be opened from a browser) from the path.
        System.out.println("toUri() = " + testFilePath.toUri());

        //The toAbsolutePath() method returns the absolute path from a given relative path.
        // If the input path is already an absolute path, the method returns the same object.
        System.out.println("toAbsolutePath() = " + testFilePath.toAbsolutePath());

        //The normalize() method performs normalization on the input path. In other words,
        // it removes unnecessary symbols (such as . and ..) from the Path object.
        System.out.println("normalize() = " + testFilePath.normalize());

        try {
            //toRealPath() is an interesting method.
            // It returns an absolute path from the input path object (as toAbsolutepath()).
            // It also normalizes the path (as in normalize()). Further, if linking options are chosen properly,
            // it resolves symbolic links. However, the target file/directory must exist in the file system,
            // which is not a prerequisite for other Path methods

            System.out.println("toRealPath() = " + testFilePath.toRealPath());
            System.out.println("Its normalized real path is: " + testFilePath.toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get another path object with normalized relative path
        Path testPathNormalized = Paths.get(testFilePath.normalize().toString());
        System.out.println("Its normalized absolute path is: " + testPathNormalized.toAbsolutePath());

//        the toPath() method in the java.io.File class returns the Path object; this method was added
//        in Java 7. Similarly, you can use the toFile() method in the Path interface to get a File object.
    }

}
