package br.com.dvlima.ocpjp8.apress.topic02._advanced_java_class_design._06_lambda_expressions;

interface SuffixFunction {
	void call();
}

public class PigLatin {
	public static void main(String[] args) {
		String word = "hello";
		SuffixFunction suffixFunction = () -> System.out.println(word + "ay");
		//The compiler issues an error for this code segment:
		//Local variable word defined in an enclosing scope must be final or effectively final	
		//word = "";
		suffixFunction.call();
	}
}
