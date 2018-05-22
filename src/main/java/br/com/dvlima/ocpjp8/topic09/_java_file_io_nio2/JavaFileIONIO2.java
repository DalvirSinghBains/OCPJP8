package br.com.dvlima.ocpjp8.topic09._java_file_io_nio2;

/**
 Java File I/O (NIO.2)

 - Use Path interface to operate on file and directory paths
 - Use Files class to check, read, delete, copy, move, manage metadata of a file or directory
 - Use Stream API with NIO.2
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

}
