package br.com.dvlima.ocpjp8.topic06._exceptions_and_assertions;

/**
 * Exceptions and Assertions
 * <p>
 * - Use try-catch and throw statements
 * - Use catch, multi-catch, and finally clauses
 * - Use Autoclose resources with a try-with-resources statement
 * - Create custom exceptions and Auto-closeable resources
 * - Test invariants by using assertions
 */
public class ExceptionsAndAssertions {

/**
 * Use try-catch and throw statements
 * */

// When an exception is thrown from a try block, the JVM looks for a matching catch handler from the list of catch
// handlers in the method call-chain. If no matching handler is found, that unhandled exception will result in
// crashing the application.

// While providing multiple exception handlers (stacked catch handlers), specific exception handlers should be provided
// before general exception handlers.

// You can programmatically access the stack trace using the methods such as printStackTrace() and getStackTrace(),
// which can be called on any exception object.

/**
 * Use catch, multi-catch, and finally clauses
 */
// A try block can have multiple catch handlers. If the cause of two or more exceptions is similar, and the handling
// code is also similar, you can consider combining the handlers and make it into a multi-catch block.

// A catch block should either handle the exception or rethrow it. To hide or swallow an exception by catching an
// exception and doing nothing is really a bad practice.

// You can wrap one exception and throw it as another exception. These two exceptions become chained exceptions.
// From the thrown exception, you can get the cause of the exception.

// The code inside a finally block will be executed irrespective of whether a try block has successfully executed or
// resulted in an exception.


/**
 * Use autoclose resources with a try-with-resources statement
 */

// Forgetting to release resources by explicitly calling the close() method is a common mistake. You can use a
// try-with-resources statement to simplify your code and auto-close resources.

// You can auto-close multiple resources within a try-with-resources statement. These resources need to be separated
// by semicolons in the try-with-resources statement header.

// If a try block throws an exception, and a finally block also throws exception(s), then the exceptions thrown in the
// finally block will be added as suppressed exceptions to the exception that gets thrown out of the try block to the
// caller.


/**
 * Create custom exceptions and auto-closeable resources
 */
// It is recommended that you derive custom exceptions from either the Exception or RuntimeException class.

// A method’s throws clause is part of the contract that its overriding methods in derived classes should obey.

// An overriding method can provide the same throw clause as the base method’s throws clause or a more specific throws
// clause than the base method’s throws clause.

// The overriding method cannot provide a more general throws clause or declare to throw additional checked exceptions
// when compared to the base method’s throws clause.

// For a resource to be usable in a try-with-resources statement, the class of that resource must implement the
// java.lang.AutoCloseable interface and define the close() method.

/**
 * Test invariants by using assertions
 */

// Assertions are condition checks in the program and should be used for explicitly checking the assumptions you make
// while writing programs.

// The assert statement is of two forms: one that takes a Boolean argument and one that takes an additional string
// argument.

// If the Boolean condition given in the assert argument fails (i.e., evaluates to false), the program will terminate
// after throwing an AssertionError. It is not advisable to catch and recover from when an AssertionError is thrown
// by the program.

// By default, assertions are disabled at runtime. You can use the command-line arguments of –ea (for enabling asserts)
// and –da (for disabling asserts) and their variants when you invoke the JVM.
}
