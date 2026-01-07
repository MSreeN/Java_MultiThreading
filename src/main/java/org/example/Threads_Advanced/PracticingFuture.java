package org.example.Threads_Advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class PracticingFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Future<?>> futureList = new ArrayList<>();
        Future<?> future1 = new FutureTask<Object>(() -> {
            try {
                Thread.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        futureList.add(future1);
        Future<?> future2 = new FutureTask<Object>(() -> {
            try {
                Thread.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        futureList.add(future2);
        Future<?> future3 = new FutureTask<Object>(() -> {
            try {
                Thread.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        futureList.add(future3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
    }
}
