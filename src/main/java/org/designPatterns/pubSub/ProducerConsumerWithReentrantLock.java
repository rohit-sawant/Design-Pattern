package org.designPatterns.pubSub;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithReentrantLock {

    private final Queue<Integer> queue =
            new LinkedList<>();

    private final int CAPACITY = 5;

    private final ReentrantLock lock =
            new ReentrantLock();

    // condition variables
    private final Condition notFull =
            lock.newCondition();

    private final Condition notEmpty =
            lock.newCondition();

    // PRODUCER
    public void produce(int value)
            throws InterruptedException {

        lock.lock();

        try {

            // wait while queue full
            while (queue.size() == CAPACITY) {

                System.out.println(
                        "Queue full, producer waiting..."
                );

                notFull.await();
            }

            queue.add(value);

            System.out.println(
                    "Produced : " + value
            );

            // signal consumer
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    // CONSUMER
    public void consume()
            throws InterruptedException {

        lock.lock();

        try {

            // wait while queue empty
            while (queue.isEmpty()) {

                System.out.println(
                        "Queue empty, consumer waiting..."
                );

                notEmpty.await();
            }

            int value = queue.poll();

            System.out.println(
                    "Consumed : " + value
            );

            // signal producer
            notFull.signal();

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ProducerConsumerWithReentrantLock mq = new ProducerConsumerWithReentrantLock();

        Thread producer = new Thread(() -> {

            for (int i = 1; i <= 10; i++) {

                try {

                    mq.produce(i);

                    Thread.sleep(200);

                } catch (Exception e) {}
            }
        });

        Thread consumer = new Thread(() -> {

            while (true) {

                try {

                    mq.consume();

                    Thread.sleep(500);

                } catch (Exception e) {}
            }
        });

        producer.start();
        consumer.start();
    }
}


