package org.designPatterns.pubSub;

import java.util.concurrent.locks.ReentrantLock;

public class CounterReEntrantLock {


    private int count = 0;

    private final ReentrantLock lock =
            new ReentrantLock();

    public void increment() {

        lock.lock(); // acquire lock

        try {
            count++;

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> " + count
            );

        } finally {
            lock.unlock(); // always unlock
        }
    }

    public static void main(String[] args)
            throws Exception {

        CounterReEntrantLock counter = new CounterReEntrantLock();

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}