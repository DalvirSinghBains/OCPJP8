package br.com.dvlima.ocpjp8.apress.topic10._java_concurrency._01_create_worker_threads;

public class CreatingThreadsByExtendingTheThreadClass extends Thread {

    public static void main(String[] args) {
        Thread myThread = new CreatingThreadsByExtendingTheThreadClass();
        myThread.start();
        System.out.println("In main(); thread name: " + Thread.currentThread().getName());
//        In main(); thread name is: main
//        In run(); thread name is: Thread-0
    }

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            // ignore the InterruptedException - this is perhaps the one of the
            // very few of the exceptions in Java which is acceptable to ignore
        }
        System.out.println("In run(); thread name is: " + getName());
    }
}
