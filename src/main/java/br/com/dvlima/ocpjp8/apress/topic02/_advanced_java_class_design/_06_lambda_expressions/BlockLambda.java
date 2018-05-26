package br.com.dvlima.ocpjp8.apress.topic02._advanced_java_class_design._06_lambda_expressions;

public class BlockLambda {
	interface LambdaFunction {
		String intKind(int a);
	}

	public static void main(String[] args) {
		LambdaFunction lambdaFunction = (int i) -> {
			if ((i % 2) == 0)
				return "even";
			else
				return "odd";
		};
		System.out.println(lambdaFunction.intKind(10));
	}
}
