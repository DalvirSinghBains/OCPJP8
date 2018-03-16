package br.com.dvlima.ocpjp8.topic01._java_class_design._05_singleton_classes_immutable_classes;

public class Logger2 {

	private Logger2() {
		// private constructor
	}

	public static class LoggerHolder {
		public static Logger2 logger = new Logger2();
	}

	public static Logger2 getInstance() {
		return LoggerHolder.logger;
	}

	public void log(String s) {
		// log implementation
		System.err.println(s);
	}
}
