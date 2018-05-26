package br.com.dvlima.ocpjp8.topic10._java_concurrency._01_create_worker_threads;

class RunnableImpl implements Runnable {
    public static void main(String args[]) throws Exception {
        Thread myThread = new Thread(new RunnableImpl());
        myThread.start();
        System.out.println("In main(); thread name is: " +
                Thread.currentThread().getName());
    }

    public void run() {
        System.out.println("In run(); thread name is: " + Thread.currentThread().getName());
    }
}