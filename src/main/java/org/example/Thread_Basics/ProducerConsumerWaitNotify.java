package org.example.Thread_Basics;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerWaitNotify {

    private static final int MAX_SIZE = 5;

    Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerWaitNotify obj = new ProducerConsumerWaitNotify();
        Thread consumerThread = new Thread(() ->{
           while(true){
               try {
                   obj.consumer();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        Thread producerThread = new Thread(() -> {
            while (true) {
                try {
                    obj.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//        producerThread.start();
//        consumerThread.start();

        Thread t1 = new Thread(() ->{
            while(true){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();

        Thread.sleep(2000);
        t1.interrupt();
    }

    public synchronized void producer() throws InterruptedException {
        if(!queue.isEmpty()) wait();
        for(int i = 0; i< MAX_SIZE; i++){
            int ele = new Random().nextInt(50);
            queue.add(ele);
            System.out.println("Produced "+ele);
        }
        notify();
    }

    public synchronized void consumer() throws InterruptedException {
        if(queue.size() != MAX_SIZE) wait();
        for(int i = 0; i < MAX_SIZE; i++){
            System.out.println("Consumed "+queue.remove());
        }
        notify();
    }
}
