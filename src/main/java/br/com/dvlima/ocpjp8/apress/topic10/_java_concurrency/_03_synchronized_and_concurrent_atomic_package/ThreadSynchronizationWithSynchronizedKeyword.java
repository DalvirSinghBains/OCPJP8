package br.com.dvlima.ocpjp8.apress.topic10._java_concurrency._03_synchronized_and_concurrent_atomic_package;

// This class exposes a publicly accessible counter
// to help demonstrate race condition problem
class Counter {
    public static long count = 0;
}

// This class implements Runnable interface
// Its run method increments the counter three times
class UseCounter implements Runnable {
    public void increment(String nameThread) {
        // increments the counter and prints the value
        // of the counter shared between threads
        synchronized (this) {
            System.out.print(nameThread);
            Counter.count++;
            System.out.print(Counter.count + " ");
        }
    }

    public synchronized void increment() {
        Counter.count++;
        System.out.print(Counter.count + "  ");
    }

    public void run() {
        increment("[Method 1] ");
        increment("[Method 2] ");
        increment("[Method 3] ");
    }
}

public class ThreadSynchronizationWithSynchronizedKeyword {

    public static void main(String args[]) {
        //RaceCondition
        UseCounter c = new UseCounter();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        Thread t3 = new Thread(c);
        t1.start();
        t2.start();
        t3.start();
    }

}