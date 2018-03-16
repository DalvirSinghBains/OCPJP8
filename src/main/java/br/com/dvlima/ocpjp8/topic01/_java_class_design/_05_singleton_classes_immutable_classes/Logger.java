package br.com.dvlima.ocpjp8.topic01._java_class_design._05_singleton_classes_immutable_classes;

//Logger class must be instantiated only once in the application; it is to ensure that the
//whole of the application makes use of that same logger instance
public class Logger {
	// declare the constructor private to prevent clients
	// from instantiating an object of this class directly
	private Logger() {

	}

	// by default, this field is initialized to null
	// the static method to be used by clients to get the instance of the Logger
	// class
	private static Logger instance;

	public static synchronized Logger getInstance() { /*synchronized for multi-threaded environment.*/
		if (instance == null) {
			// this is the first time this method is called,
			// and that's why instance is null
			instance = new Logger();
		}
		
		// return the same object reference any time and
		// every time getInstance is called
		return instance;
	}

	public void log(String s) {
		// a trivial implementation of log where
		// we pass the string to be logged to console
		System.err.println(s);
	}
}
