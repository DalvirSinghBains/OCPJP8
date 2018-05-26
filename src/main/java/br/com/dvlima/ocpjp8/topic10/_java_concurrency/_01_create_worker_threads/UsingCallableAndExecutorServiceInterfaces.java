package br.com.dvlima.ocpjp8.topic10._java_concurrency._01_create_worker_threads;

import java.util.concurrent.*;

public class UsingCallableAndExecutorServiceInterfaces {
}

/**
 * Executor
 */

// This Task class implements Runnable, so its a Thread object
class Task implements Runnable {
    public void run() {
        System.out.println("Calling Task.run() ");
    }
}

// This class implements Executor interface and should override execute(Runnable) method.
// We provide an overloaded execute method with an additional argument 'times' to create and
// run the threads for given number of times
class RepeatedExecutor implements Executor {
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }

    public void execute(Runnable runnable, int times) {
        System.out.printf("Calling Task.run() %d times thro' Executor.execute() %n", times);
        for (int i = 0; i < times; i++) {
            execute(runnable);
        }
    }
}

// This class spawns a Task thread and explicitly calls start() method.
// It also shows how to execute a Thread using Executor
class ExecutorTest {
    public static void main(String[] args) {
        Runnable runnable = new Task();
        System.out.println("Calling Task.run() by directly creating a Thread");
        Thread thread = new Thread(runnable);
        thread.start();
        RepeatedExecutor executor = new RepeatedExecutor();
        executor.execute(runnable, 3);
    }
}


/**
 * Callable and ExecutorService
 */

// Factorial implements Callable so that it can be passed to a ExecutorService
// and get executed as a task.
class Factorial implements Callable<Long> {
    long n;

    public Factorial(long n) {
        this.n = n;
    }

    public Long call() throws Exception {
        if (n <= 0) {
            throw new Exception("for finding factorial, N should be > 0");
        }
        long fact = 1;
        for (long longVal = 1; longVal <= n; longVal++) {
            fact *= longVal;
        }
        return fact;
    }
}

// Illustrates how Callable, Executors, ExecutorService, and Future are related;
// also shows how they work together to execute a task
class CallableTest {
    public static void main(String[] args) throws Exception {

        // the value for which we want to find the factorial
        long N = 20;

        // get a callable task to be submitted to the executor service
        Callable<Long> task = new Factorial(N);

        // create an ExecutorService with a fixed thread pool having one thread
        ExecutorService es = Executors.newSingleThreadExecutor();

        // submit the task to the executor service and store the Future object
        Future<Long> future = es.submit(task);

        // wait for the get() method that blocks until the computation is complete.
        System.out.printf("factorial of %d is %d", N, future.get());
        //factorial of 20 is 2432902008176640000

        // done. shutdown the executor service since we don't need it anymore
        es.shutdown();
    }
}