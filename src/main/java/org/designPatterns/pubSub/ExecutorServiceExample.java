package org.designPatterns.pubSub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.List.of;

public class ExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.submit(()->{
//           return "value";
//        });
//        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
//        scheduledExecutorService.scheduleAtFixedRate(()->{
//            System.out.println("hello");
//        },0,1,TimeUnit.SECONDS);
//        Future<String> response = executorService.submit(()->"value");
//        response.get();

        List<Integer> arrayList = List.of(1,2,3,4,5);
        int ans = arrayList.parallelStream().reduce(5, ExecutorServiceExample::sum);
        List<Integer> copyArrayList = arrayList.stream().toList();
        copyArrayList.add(5);
        System.out.println(copyArrayList.get(copyArrayList.size()-1));

//        scheduledExecutorService.awaitTermination(20,TimeUnit.SECONDS);
    }
    static int sum(int a ,int b){
        return a+b;
    }
}
