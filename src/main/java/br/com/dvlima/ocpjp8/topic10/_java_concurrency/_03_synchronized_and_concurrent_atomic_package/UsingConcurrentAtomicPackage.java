package br.com.dvlima.ocpjp8.topic10._java_concurrency._03_synchronized_and_concurrent_atomic_package;

import java.util.concurrent.atomic.AtomicInteger;

public class UsingConcurrentAtomicPackage {
    public static void main(String[] args) throws InterruptedException {
        Thread incremeterThread[] = new Incrementer[1000];
        Thread decrementerThread[] = new Decrementer[1000];

        for (int i = 0; i < 1000; i++) {
            incremeterThread[i] = new Incrementer();
            decrementerThread[i] = new Decrementer();
            incremeterThread[i].start();
            decrementerThread[i].start();
        }

        for (int i = 0; i < 1000; i++) {
            incremeterThread[i].join();
            decrementerThread[i].join();
        }

        System.out.printf("Integer value = %d AtomicInteger value = %d ",
                CounterAtomic.integer,
                CounterAtomic.atomicInteger.get());
    }

    static class Incrementer extends Thread {
        public void run() {
            CounterAtomic.integer++;
            CounterAtomic.atomicInteger.incrementAndGet();
        }
    }

    static class Decrementer extends Thread {
        public void run() {
            CounterAtomic.integer--;
            CounterAtomic.atomicInteger.decrementAndGet();
        }
    }
}

// Class to demonstrate how mutating "normal" (i.e., thread unsafe) integers
// and mutating "atomic" (i.e., thread safe) integers are different:
// Mutating a shared Integer object without locks can result in a race condition;
// however, mutating a shared AtomicInteger will not result in a race conditiond.
class CounterAtomic {
    public static Integer integer = new Integer(0);
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
}