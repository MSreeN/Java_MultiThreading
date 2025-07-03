package org.example.Thread_Basics;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {
    public static final int UPPER_LIMIT = 5;
    public static final int LOWER_LIMIT = 0;
    static List<Integer> list = new ArrayList<>();
    final Object lock = new Object();
    ReentrantLock reentrantLock = new ReentrantLock(true);
    Condition condition = reentrantLock.newCondition();

    public void producer() throws InterruptedException {
//        synchronized (lock) {
        while (true) {
        reentrantLock.lock();
            while (list.size() == UPPER_LIMIT) {
//                    lock.wait();
                condition.await();
//            reentrantLock.unlock();
            }
            int num = new Random().nextInt(0, 100);
            list.add(num);
            System.out.println("produced " + num);
//                lock.notify();
            condition.signal();
        }
//        }
    }

    public void consumer() throws InterruptedException {
//        synchronized (lock) {
        while (true) {
        reentrantLock.lock();
            while (list.isEmpty()) {
//                    lock.wait();
                condition.await();
//            reentrantLock.unlock();
            }
            int num = list.remove(0);
            System.out.println("removed " + num);
//                lock.notify();
            condition.signal();
        }

    }
}

class App {

    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
