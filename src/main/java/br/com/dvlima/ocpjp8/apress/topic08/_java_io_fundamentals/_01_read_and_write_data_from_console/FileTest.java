package br.com.dvlima.ocpjp8.apress.topic08._java_io_fundamentals._01_read_and_write_data_from_console;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class FileTest {
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));

		// change the current working directory for the JVM in a running Java program
		System.setProperty("user.dir", "C:\\kishori");
		System.out.println(System.getProperty("user.dir"));

		// You can also specify the current working directory for the JVM as the
		// user.dir property value as a JVM option
		//
		// java Duser.dir=C:\test <other-arguments>

		/** Checking for a Files Existence */

		// Create a File object
		File dummyFile = new File("dummy.txt");
		// Check for the file's existence
		boolean fileExists = dummyFile.exists();

		if (fileExists) {
			System.out.println("The dummy.txt file exists.");
		} else {
			System.out.println("The dummy.txt file does not exist.");
		}

		// The absolute path identifies the file uniquely on a file system.
		// A canonical path is the simplest path that uniquely identifies the file on a
		// file system.

		/**
		 * Creating, Deleting, and Renaming Files
		 */

		// Create a File object to represent the abstract pathname
		File theDummyFile = new File("dummy.txt");
		// Create the file in the file system
		try {
			boolean fileCreated = theDummyFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// TIP The method accepts a prefix (at least three characters in length) and a
			// suffix to generate the temporary file name.

			// Create a temporary file in the default temporary directory
			File tempFile1 = File.createTempFile("kkk", ".txt");

			// Create a temporary file in the existing C:\kishori\temp directory
			File tempDir = new File("C:\\kishori\\temp");
			File tempFile2 = File.createTempFile("kkk", ".txt", tempDir);
		} catch (IOException e) {
			e.printStackTrace();
		}

		File dummyFileToDelete = new File("dummy.txt");
		// To delete the dummy.txt file immediately
		dummyFileToDelete.delete();

		// To delete the dummy.txt file when the JVM terminates
		dummyFileToDelete.deleteOnExit();

		// Rename old-dummy.txt to new_dummy.txt

		// Tip The File object is immutable. Once created, it always represents the same
		// pathname, which is passed to its constructor. When you rename a file, the old
		// File object still represents the original pathname. An important point to
		// remember is that a File object represents a pathname, not an actual file in a
		// file system.
		File oldFile = new File("old_dummy.txt");
		File newFile = new File("new_dummy.txt");
		boolean fileRenamed = oldFile.renameTo(newFile);

		if (fileRenamed) {
			System.out.println(oldFile + " renamed to " + newFile);
		} else {
			System.out.println("Renaming " + oldFile + " to " + newFile + " failed.");
		}

		/** Knowing the Size of a File */
		File myFile = new File("myfile.txt");
		// If a File object represents a non-existent file, the length() returns zero.
		long fileLength = myFile.length();

		/** Listing Directories and Files */
		// Get the list of all root directories
		File[] roots = File.listRoots();
	}
}

class FilePath {
	public static void main(String[] args) {
		String workingDir = System.getProperty("user.dir");
		System.out.println("Working Directory: " + workingDir);
		System.out.println("----------------------");
		String pathname = "dummy.txt";
		printFilePath(pathname);
		System.out.println("----------------------");
		pathname = ".." + File.separator + "notes.txt";
		printFilePath(pathname);
	}

	public static void printFilePath(String pathname) {
		File f = new File(pathname);
		System.out.println("File Name: " + f.getName());
		System.out.println("File exists: " + f.exists());
		System.out.println("Absolute Path: " + f.getAbsolutePath());

		// getCanonicalPath() may throw an IOException.
		try {
			System.out.println("Canonical Path: " + f.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class FileCreateDeleteRename {
	public static void main(String[] args) {
		try {
			File newFile = new File("my_new_file.txt");
			System.out.println("Before creating the new file:");
			printFileDetails(newFile);
			// Create a new file
			boolean fileCreated = newFile.createNewFile();
			if (!fileCreated) {
				System.out.println(newFile + " could not be created.");
			}
			System.out.println("After creating the new file:");
			printFileDetails(newFile);

			// Delete the new file
			newFile.delete();
			System.out.println("After deleting the new file:");
			printFileDetails(newFile);
			// Let's recreate the file
			newFile.createNewFile();
			System.out.println("After recreating the new file:");
			printFileDetails(newFile);
			// Let's tell the JVM to delete this file on exit
			newFile.deleteOnExit();

			System.out.println("After using deleteOnExit() method:");
			printFileDetails(newFile);

			// Create a new file and rename it
			File firstFile = new File("my_first_file.txt");
			File secondFile = new File("my_second_file.txt");
			fileCreated = firstFile.createNewFile();
			if (fileCreated || firstFile.exists()) {
				System.out.println("Before renaming file:");
				printFileDetails(firstFile);
				printFileDetails(secondFile);
				boolean renamedFlag = firstFile.renameTo(secondFile);
				if (!renamedFlag) {
					System.out.println("Could not rename " + firstFile);
				}
				System.out.println("After renaming file:");
				printFileDetails(firstFile);
				printFileDetails(secondFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printFileDetails(File f) {
		System.out.println("Absolute Path: " + f.getAbsoluteFile());
		System.out.println("File exists: " + f.exists());
		System.out.println("------------------------------");
	}
}

class RootList {
	public static void main(String[] args) {
		File[] roots = File.listRoots();
		System.out.println("List of root directories:");
		Stream.of(roots).forEach(f -> System.out.println(f.getPath()));
		/*
		 * for (File f : roots) { System.out.println(f.getPath()); }
		 */
	}
}

class FileLists {
	public static void main(String[] args) {
		// Change the dirPath value to list files from your directory
		String dirPath = "./";

		File dir = new File(dirPath);
		File[] list = dir.listFiles();

		for (File f : list) {
			if (f.isFile()) {
				System.out.println(f.getPath() + " (File)");
			} else if (f.isDirectory()) {
				System.out.println(f.getPath() + " (Directory)");
			}
		}

		// Create a file filter to exclude any .SYS file
		FileFilter filter = file -> {
			if (file.isFile()) {
				String fileName = file.getName().toLowerCase();
				if (fileName.endsWith(".sys")) {
					return false;
				}
			}
			return true;
		};

		// Filters only files
		FileFilter fileOnlyFilter = File::isFile;

		// Filters only directories
		FileFilter dirOnlyFilter = File::isDirectory;
	}
}

class FilteredFileList {
	public static void main(String[] args) {
		// Change the dirPath value to list files from your directory
		String dirPath = "C:\\";
		File dir = new File(dirPath);

		// Create a file filter to exclude any .SYS file
		FileFilter filter = file -> {
			if (file.isFile()) {
				String fileName = file.getName().toLowerCase();
				if (fileName.endsWith(".sys")) {
					return false;
				}
			}
			return true;
		};

		// Pass the filter object to listFiles() method to exclude the .sys files
		File[] list = dir.listFiles(filter);

		for (File f : list) {
			if (f.isFile()) {
				System.out.println(f.getPath() + " (File)");
			} else if (f.isDirectory()) {
				System.out.println(f.getPath() + " (Directory)");
			}
		}
	}
}

class FileUtil {
	// Prints the location details of a file
	public static void printFileNotFoundMsg(String fileName) {
		String workingDir = System.getProperty("user.dir");
		System.out.println("Could not find the file '" + fileName + "' in '" + workingDir + "' directory ");
	}

	// Closes a Closeable resource such as an input/output stream
	public static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class SimpleFileReading {
	public static void main(String[] args) {
		String dataSourceFile = "./files/luci1.txt";
		try (FileInputStream fin = new FileInputStream(dataSourceFile)) {
			byte byteData;
			while ((byteData = (byte) fin.read()) != -1) {
				System.out.print((char) byteData);
			}
		} catch (FileNotFoundException e) {
			FileUtil.printFileNotFoundMsg(dataSourceFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SimpleFileWriting {
	public static void main(String[] args) {
		String destFile = "./files/luci2.txt";
		// Get the line separator for the current platform
		String lineSeparator = System.getProperty("line.separator");

		String line1 = "When she I loved look'd every day";
		String line2 = "Fresh as a rose in June,";
		String line3 = "I to her cottage bent my way,";
		String line4 = "Beneath an evening moon.";

		try (FileOutputStream fos = new FileOutputStream(destFile)) {
			// Write all four lines to the output stream as bytes
			fos.write(line1.getBytes());
			fos.write(lineSeparator.getBytes());
			fos.write(line2.getBytes());
			fos.write(lineSeparator.getBytes());
			fos.write(line3.getBytes());
			fos.write(lineSeparator.getBytes());
			fos.write(line4.getBytes());
			// Flush the written bytes to the file
			fos.flush();
			// Display the output file path
			System.out.println("Text has been written to " + (new File(destFile)).getAbsolutePath());
		} catch (FileNotFoundException e1) {
			FileUtil.printFileNotFoundMsg(destFile);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}