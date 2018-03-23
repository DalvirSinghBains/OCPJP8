package br.com.dvlima.ocpjp8.topic08._java_io_fundamentals._01_read_and_write_data_from_console;

import java.io.Console;

public class ConsoleLogin {

	public static void main(String[] args) {
		Console console = System.console();

		if (console != null) {
			console.printf("Console is available.%n");
		} else {
			System.out.println("Console is not available.%n");
			return; // A console is not available
		}

		String userName = console.readLine("User Name: ");
		char[] passChars = console.readPassword("Password: ");
		String passString = new String(passChars);

		if (passString.equals("letmein")) {
			console.printf("Hello %s", userName);
		} else {
			console.printf("Invalid password");
		}
	}

}
