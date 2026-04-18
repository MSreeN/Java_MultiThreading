package org.example.Java.Executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
       ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();

       Future<Integer> future = es.submit(new CallablePractice());
        while(!future.isDone()){
            System.out.println("waiting for future to complete");
        }
        es.shutdown();
        System.out.println(future.get());
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
