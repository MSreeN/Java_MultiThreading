package org.example.Thread_Basics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    Lock lock1 = new ReentrantLock(true);
    Lock lock2 = new ReentrantLock(true);

    public void user1() {
        lock1.lock();
        System.out.println("lock1 acquired - user1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock2.lock();
    }

    public void user2() {
        lock2.lock();
        System.out.println("lock2 acquired - user 2");
        if (lock1.tryLock()) {
            lock2.unlock();
        }else{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("waiting to acquire lock1 - user2");
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();

            new Thread(deadLock::user1).start();
            new Thread(deadLock::user2).start();
    }
}
