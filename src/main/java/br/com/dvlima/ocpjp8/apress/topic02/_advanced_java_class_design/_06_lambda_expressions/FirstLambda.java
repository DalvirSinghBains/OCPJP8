package br.com.dvlima.ocpjp8.apress.topic02._advanced_java_class_design._06_lambda_expressions;

public class FirstLambda {
	public static void main(String[] args) {
		LambdaFunction lambdaFunction = () -> System.out.println("Hello world!");
		lambdaFunction.call();
	}
}
