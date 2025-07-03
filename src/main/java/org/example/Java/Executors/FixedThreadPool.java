package org.example.Java.Executors;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        executorCallable();
//        ExecutorService es = Executors.newFixedThreadPool(10);
//
//
//        Instant start = Instant.now();
//        for(int i = 0; i <= 11; i++){
//            es.execute(new ExecutorThread("Thread"+i+1));
//        }
//
//        es.shutdown();
//        if(!es.awaitTermination(5, TimeUnit.SECONDS)){
//            es.shutdownNow();
//        }
//        System.out.println(Thread.currentThread().getName());
//        Instant end = Instant.now();
//        System.out.println(Duration.between(start, end).getSeconds());
    }

    public static void executorCallable() throws InterruptedException {
        List<Future<String>> list = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(2);
        for(int i = 0; i<5; i++){
            Future<String> val = es.submit(new ExecutorCallable());
            list.add(val);
        }

        for(Future<String> val: list){
            try {
                System.out.println(val.get());
                System.out.println(Thread.currentThread().getName());
                System.out.println("Done");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        es.shutdownNow();
        if(!es.awaitTermination(10, TimeUnit.SECONDS)){
            es.shutdownNow();
        }
    }
}
