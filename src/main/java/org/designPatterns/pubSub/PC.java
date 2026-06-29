package org.designPatterns.pubSub;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PC {

    // Size of list is 2.
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 100;

    // Function called by producer thread
    public void produce() throws InterruptedException
    {
        int value = 0;
        while (true) {
        synchronized (this){


                // producer thread waits while list is full
                if (list.size() == capacity) {
                    System.out.println("List is full, producer is waiting...");
                    // Signal any waiting consumer before waiting
                    notify();
                    wait();
                }

                // to insert the jobs in the list
                list.add(value);
                System.out.println("Producer produced-" + value);
                value++;

                // notifies the consumer thread that now it can start consuming
                notify();

                // makes the working of program easier to understand
                Thread.sleep(1000);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException
    {
        while (true) {
            synchronized (this) {
                // consumer thread waits while list is empty
                if (list.size() == 0) {
                    System.out.println("List is empty, consumer is waiting...");
                    // Signal any waiting producer before waiting
                    notify();
                    wait();
                }

                // to retrieve the first job in the list
                int val = list.removeFirst();
                System.out.println("Consumer consumed-" + val);

                // Wake up producer thread
                notify();

                // and sleep
                Thread.sleep(1000);
            }
        }

    }
}
