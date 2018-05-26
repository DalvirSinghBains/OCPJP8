package br.com.dvlima.ocpjp8.apress.topic02._advanced_java_class_design._06_lambda_expressions;

public class AnonymousInnerClass {
	interface Function {
		void call();
	}

	public static void main(String[] args) {
		Function function = new Function() {
			public void call() {
				System.out.println("Hello world");
			}
		};
		function.call();
	}
}
