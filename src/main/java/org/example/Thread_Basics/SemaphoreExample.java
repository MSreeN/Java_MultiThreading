package org.example.Thread_Basics;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
            Semaphore s = new Semaphore(1);
        Thread t = new Thread(() -> {
            int i = 0;
            while(true){
                s.tryAcquire();
                System.out.println("count" + i++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    Thread t2 = new Thread(()-> {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("releasing lock");
        s.release();
    });
        t.start();
        t2.start();
    }

}
