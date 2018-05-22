package br.com.dvlima.ocpjp8.topic09._java_file_io_nio2;

/**
 * Java File I/O (NIO.2)
 * <p>
 * - Use Path interface to operate on file and directory paths
 * - Use Files class to check, read, delete, copy, move, manage metadata of a file or directory
 * - Use Stream API with NIO.2
 */
public class JavaFileIONIO2 {


// Do not confuse File with Files, and Path with Paths: they are different.
// File is an old class (Java 4) that represents file/directory path names,
// whereas Files was introduced in Java 7 as a utility class with comprehensive support for I/O APIs.
// The Path interface represents a file/directory path and defines a useful list of methods.
// However, the Paths class is a utility class that offers only two methods (both to get the Path object).

// The file or directory represented by a Path object may not exist.
// Other than methods such as toRealPath(),
// methods in Path do not require that the underlying file or directory be present for a Path object.

// You learned how to perform a copy for files/directories.
// However, it is not necessary to perform a copy on only two files/directories.
// You can take input from an InputStream and write to a file, or you can take input from a file and copy to an OutputStream.
// The methods copy(InputStream, Path, CopyOptions...) and copy(Path, OutputStream, CopyOptions...) can be used here.

    /*
     *    Use Path interface to operate on file and directory paths
     */
// A Path object is a programming abstraction to represent the path of a file/directory.

// You can get an instance of Path using the get() method of the Paths class.

// Path provides two methods to compare Path objects: equals() and compareTo().
// Even if two Path objects point to the same file/directory, the equals() method is not guaranteed to return true.

    /*
     *   Use Files class to check, read, delete, copy, move, manage metadata of a file or directory
     */
// You can check the existence of a file using the exists() method of the Files class.

// The Files class provides the methods isReadable(), isWritable(), and isExecutable() to check the ability of the program
// to read, write, and execute programmatically, respectively.

// You can retrieve the attributes of a file using the getAttributes() method.

// You can use the readAttributes() method of the Files class to read file attributes  in bulk.

// The copy() method can be used to copy a file from one location to another.
// Similarly, the move() method moves a file from one location to another.

// While copying, all the directories (except the last one, if you are copying a directory) on the specified
// path must exist to avoid a NoSuchFileException.

// Use the delete() method to delete a file; use the deleteIfExists() method to delete a file only if it exists.

    /*
     *   Use Stream API with NIO.2
     */
// The Files.list() method returns a Stream<Path>. It does not recursively traverse the directories in the given Path.

// The Files.walk() method returns a Stream<Path> by recursively traversing the entries from the given Path;
// in one of its overloaded versions, you can also pass the maximum depth for such traversal and provide
// FileVisitOption.FOLLOW_LINKS as the third option.

// The Files.find() method returns a Stream<Path> by recursively traversing the entries from the given Path;
// it also takes the maximum depth to search, a BiPredicate, and an optional FileVisitOption as arguments.

// Files.lines() is a very convenient method to read the contents of a file. It returns a Stream<String>.

}
