package org.designPatterns.pubSub;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
interface CircleInterface {
List<String> allowedColors = Arrays.asList("RED", "GREEN", "BLUE");

String getColor();

public default boolean isValid() {
    if (allowedColors.contains(getColor())) {
        return true;
    } else {
        return false;
    }
}
}
public class PCWithBlockingQueue {

    // Size of list is 2.
    LinkedBlockingQueue<Integer> list = new LinkedBlockingQueue<Integer>();
    int capacity = 100;


    int value = 0;
    // Function called by producer thread
    public void produce() throws InterruptedException
    {
        while (value<=10) {
            list.put(value++);

            System.out.println("Produced "+value);
        }
    }


    // Function called by consumer thread
    public void consume() throws InterruptedException
    { Integer value;
        do{

            value = list.take();
            System.out.println("Consumed "+value);
        }
        while (value<=10);

    }

    public static void main(String[] args) throws  Exception {
        PCWithBlockingQueue pcWithBlockingQueue = new PCWithBlockingQueue();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    pcWithBlockingQueue.produce();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    pcWithBlockingQueue.consume();
                }
                catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }
}
