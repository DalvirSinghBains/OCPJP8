package br.com.dvlima.ocpjp8.topic09._java_file_io_nio2._02_using_files_class;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class UsingTheFilesClass {
    public static void main(String[] args) {
        //checkingFilePropertiesAndMetadata(args[0]);
        //copyingAFile(args);
        //movingAFile(args)
    }

    private static void deletingAFile(String[] args) {
        if(args.length != 1){
            System.out.println("usage: FileDelete <source-path>");
            System.exit(1);
        }
        Path pathSource = Paths.get(args[0]);
        try {
            Files.delete(pathSource);
            System.out.println("File deleted successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void movingAFile(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: FileMove <source-path> <destination-path>");
            System.exit(-1);
        }
        Path pathSource = Paths.get(args[0]);
        Path pathDestination = Paths.get(args[1]);
        try {
            Files.move(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Source file moved successfully");

        // Like the copy() method, the move() method does not overwrite the existing destination file
        // unless you specify that it should do so using REPLACE_EXISTING.

        // If you move a symbolic link, the link itself is moved, not the target file of the link.
        // It is important to note that in the case of copy(), if you specify a symbolic link,
        // the target of the link is copied, not the link itself.

        // A non-empty directory can be moved if moving the directory does not require moving the containing files/directories.
        // For instance, moving a directory from one physical drive to another may be unsuccessful (an IOException will be thrown).
        // If moving a directory is successful, then all the contained files/directories are  also moved.

        // You can specify move() as an atomic operation using the ATOMIC_MOVE copy option. When you specify an atomic move,
        // you are assured that either the move completes successfully or the source continues to be present.
        // If move() is performed as a non-atomic operation and it fails while in process, the state of both files is unknown and undefined.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyingAFile(String[] args) {
        //Path copy(Path source, Path target, CopyOption... options)

        if (args.length != 2) {
            System.out.println("usage: FileCopy <source-path> <destination-path>");
            System.exit(1);
        }
        Path pathSource = Paths.get(args[0]);
        Path pathDestination = Paths.get(args[1]);
        try {
            Files.copy(pathSource, pathDestination);
            System.out.println("Source file copied successfully");

            // D:\> java FileCopy FileCopy.java Backup.java
            // Source file copied successfully

            //Oops! What happened? When you try copying the file for the second time,
            // you get a FileAlreadyExistsException because the destination file already exists.
            //Files.copy(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);

            //try to copy a file to a new directory

            // D:\OCPJP\programs\NIO2\src>java FileCopy FileCopy.java bak\Backup.java
            // java.nio.file.NoSuchFileException: FileCopy.java -> bak\Backup.java

// all the directories (except the last one, if you are copying a directory) on the specified path must exist to avoid NoSuchFileException.

// if you copy a directory using the copy() method, it does not copy the files/directories contained in the source directory;
// you need to explicitly copy them to the destination folder.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkingFilePropertiesAndMetadata(String file) {
        Path path1 = Paths.get("Test");
        Path path2 = Paths.get("D:\\OCPJP\\programs\\NIO2\\Test");

        try {
            System.out.println("Files.isSameFile(path1, path2) is: " + Files.isSameFile(path1, path2));
            //true
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path path = Paths.get(file);
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("The file/directory " + path.getFileName() + " exists");
            // check whether it is a file or a directory
            if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                System.out.println(path.getFileName() + " is a directory");
            } else {
                System.out.println(path.getFileName() + " is a file");
            }
        } else {
            System.out.println("The file/directory " + path.getFileName()
                    + " does not exist");
        }

        /*
            D:\OCPJP\programs\NIO2\src>java PathExists PathExists.java
            The file/directory PathExists.java exists
            PathExists.java is a file

            D:\OCPJP\programs\NIO2\src>java PathExists D:\OCPJP\
            The file/directory OCPJP exists
            OCPJP is a directory

            D:\OCPJP\programs\NIO2\src>java PathExists D:\
            The file/directory null exists
            null is a directory
         */

        //FilePermissions
        System.out.printf("Readable: %b, Writable: %b, Executable: %b ",
                Files.isReadable(path), Files.isWritable(path), Files.isExecutable(path));

        /*
            D:\OCPJP\programs\NIO2\src>java FilePermissions readonly.txt
            Readable: true, Writable: false, Executable: true

            D:\OCPJP\programs\NIO2\src>java FilePermissions FilePermissions.java
            Readable: true, Writable: true, Executable: true
         */

        //FileAttributes
        try {
            Object object = Files.getAttribute(path, "creationTime", LinkOption.NOFOLLOW_LINKS);
            System.out.println("Creation time: " + object);

            object = Files.getAttribute(path, "lastModifiedTime", LinkOption.NOFOLLOW_LINKS);
            System.out.println("Last modified time: " + object);

            object = Files.getAttribute(path, "size", LinkOption.NOFOLLOW_LINKS);
            System.out.println("Size: " + object);

            object = Files.getAttribute(path, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
            System.out.println("isHidden: " + object);

            object = Files.getAttribute(path, "isDirectory", LinkOption.NOFOLLOW_LINKS);
            System.out.println("isDirectory: " + object);
            /*
            D:\> java FileAttributes FileAttributes.java
            Creation time: 2012-10-06T10:20:10.34375Z
            Last modified time: 2012-10-06T10:21:54.859375Z
            Size: 914
            isHidden: false
            isDirectory: false
             */
        } catch (IOException e) {
            e.printStackTrace();
        }


        //FileAttributes2
        try {
            BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("File size:                  " + fileAttributes.size());
            System.out.println("isDirectory:                " + fileAttributes.isDirectory());
            System.out.println("isRegularFile:              " + fileAttributes.isRegularFile());
            System.out.println("isSymbolicLink:             " + fileAttributes.isSymbolicLink());
            System.out.println("File last accessed time:    " + fileAttributes.lastAccessTime());
            System.out.println("File last modified time:    " + fileAttributes.lastModifiedTime());
            System.out.println("File creation time:         " + fileAttributes.creationTime());
            /*
            D:\>java FileAttributes2 FileAttributes2.java
            File size: 904
            isDirectory: false
            isRegularFile: true
            isSymbolicLink: false
            File last accessed time: 2012-10-06T10:28:29.0625Z
            File last modified time: 2012-10-06T10:28:22.4375Z
            File creation time: 2012-10-06T10:26:39.1875Z
             */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
