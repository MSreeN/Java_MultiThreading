package org.example.Thread_Basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreProdConsumer {
    static Semaphore s = new Semaphore(1);

    public static void produce(List<Integer> list) throws InterruptedException {
        s.acquire();
        if(!list.isEmpty()) {
            System.out.println("List is still full");
            s.release();
            Thread.sleep(100);
            return;
        }
        for(int i = 0; i < 5; i++){
            int j = new Random().nextInt(1, 50);
            list.add(j);
            System.out.println("produced "+j);
        }
        s.release();
    }

    public static void consume(List<Integer> list) throws InterruptedException {
        s.acquire();
        if(list.isEmpty()) {
            System.out.println("List is empty to consume");
            s.release();
            return;
        }
        for(int i = 4; i >= 0; i--){
            System.out.println("consumed "+list.get(i));
            list.remove(i);
        }
        s.release();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Thread producer = new Thread(() -> {
            while(true) {
                try {
//                    s.acquire();
                    produce(list);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
//                    s.release();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while(true) {
                try {
//                    s.acquire();
                    consume(list);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
//                    s.release();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        producer.start();
        consumer.start();
    }
}
