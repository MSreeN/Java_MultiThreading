package org.example.Locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryInterrupted extends Thread {

   ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        ReentrantTryInterrupted obj = new ReentrantTryInterrupted();
        new Thread(() -> {
            try {
                obj.lock.lock();
                System.out.println("Thread1 got the lock");
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        Thread.sleep(2000);
        obj.lockWithTryInterrupted();

    }

    public void lockWithTryInterrupted(){
        try{
            if(!lock.tryLock(3, TimeUnit.SECONDS)){
                System.out.println("Lock not has been acquired");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        } finally{
            System.out.println("From finally");
        }
    }
}
