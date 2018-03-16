package br.com.dvlima.ocpjp8.topic02._advanced_java_class_design._06_lambda_expressions;

//• Lambda expressions can occur only in the contexts where assignment, function calls,
//or casts can occur.

//• A lambda function is treated as a nested block. Hence, just like a nested block, we
//cannot declare a variable with the same name as a local variable in the enclosing
//block.

//• Lambda functions must return values from all the branches—otherwise it will result
//in a compiler error.

//• When argument types are declared, the lambda is known as “explicitly typed”; if they
//are inferred, it is “implicitly typed.”

//• What happens if a lambda expression throws an exception? If it is a checked
//exception, then the method in the functional interface should declare that;
//otherwise it will result in a compiler error.

@FunctionalInterface
interface LambdaFunction {
	void call();
}
