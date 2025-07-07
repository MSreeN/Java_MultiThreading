package org.example.Locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockProducerConsumer {

    public static void main(String[] args) {

        ReentrantLockProducerConsumer obj = new ReentrantLockProducerConsumer();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        new Thread(() -> {
            while(true){
                try {
                obj.produce(list);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while(true){
                try {
                obj.consumer(list);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private Lock lock = new ReentrantLock(true);
    private static final List<Integer> list = new ArrayList<>();
    private volatile int count = 0;

    public void produce(List<Integer> list){
        if(lock.tryLock()){
            try {
                System.out.println("producing");

                for (int i = 0; i < 5; i++) {
                    list.add(++count);
                    System.out.println("produced "+count);
                }
            }finally{
                lock.unlock();
            }
        }
    }

    public void consumer(List<Integer> list){
        if(lock.tryLock()){
            try{
                if (!list.isEmpty()) {
                System.out.println("consuming");
                for (int i = list.size() - 1; i >=0 ; i--) {
                    System.out.println("consumed "+list.get(i));
                    list.remove(i);
                }}
            }finally {
                lock.unlock();
            }
        }
    }
}
