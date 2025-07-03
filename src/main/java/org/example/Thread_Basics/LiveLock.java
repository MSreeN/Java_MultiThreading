package org.example.Thread_Basics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {
    Lock lock1 = new ReentrantLock(true);
    Lock lock2 = new ReentrantLock(true);

    public void user1(){
        while(true) {
            if (lock1.tryLock()) System.out.println("user 1 acquired lock 1");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (lock2.tryLock()) System.out.println("user 1 acquired lock 2");
            else{
                System.out.println("cannot acquire lock 2 - user1");
                lock1.unlock();
                continue;
            }
            break;
        }
        lock2.unlock();
        lock1.unlock();
}


    public void user2(){
        while(true) {
            if (lock2.tryLock()) System.out.println("user 2 acquired lock 2");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (lock1.tryLock()) System.out.println("user 2 acquired lock 1");
            else{
                System.out.println("cannot acquire lock 1 - user2");
                lock2.unlock();
                continue;
            }
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }

    public static void main(String[] args) {
        LiveLock liveLock = new LiveLock();
        new Thread(liveLock::user1).start();
        new Thread(liveLock::user2).start();
    }
}
