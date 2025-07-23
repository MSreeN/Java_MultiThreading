package org.example.Locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTryInterrupted extends Thread {

   ReentrantLock lock = new ReentrantLock();
   private volatile boolean isLockAcquired = Boolean.FALSE;

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
        Thread.sleep(1000);
//        obj.lockWithTimeout();

        Thread t2 = new Thread(obj::lockWithInterrupted);
        t2.start();

        if(!obj.isLockAcquired) t2.interrupt();
    }

    public void lockWithTimeout(){
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

    public void lockWithInterrupted(){
        try{
            lock.lockInterruptibly();
            isLockAcquired = true;
            System.out.println("Lock has been acquired");
        } catch (InterruptedException e) {
            System.out.println("Lock has been interrupted");
        } finally{}
    }
}
