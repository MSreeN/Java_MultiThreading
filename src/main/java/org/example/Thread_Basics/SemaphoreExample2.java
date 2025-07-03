package org.example.Thread_Basics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreExample2 implements Runnable{
    Barrier barrier;
    SemaphoreExample2(Barrier barrier) {
        this.barrier = barrier;
    }

    public static void main(String[] args) {
        Barrier barrier = new Barrier(5 );
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 5;i++){
            threads.add(new Thread(new SemaphoreExample2(barrier)));
        }

        for(Thread thread : threads){
            thread.start();
        }
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + " part 1 completed");
        try {
            barrier.waitForOtherThreads();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " part 2 completed");

    }

    static class Barrier{
        volatile  int count = 0;
        int noOfThreads;
         Semaphore semaphore = new Semaphore(0);
         Lock lock = new ReentrantLock(true);

        public Barrier(int numberOfWorkers) {
            this.noOfThreads = numberOfWorkers;
        }

        public void waitForOtherThreads() throws InterruptedException {
            boolean isLast = Boolean.FALSE;
            try{
                lock.lock();
                count++;
                if(count == noOfThreads) isLast = Boolean.TRUE;
            }finally {
                lock.unlock();
            }
            if(isLast) {
                System.out.println("Sleeping");
                Thread.sleep(3000);
                semaphore.release(3);
            }
            else{
                semaphore.acquire();
            }
        }
    }
}
