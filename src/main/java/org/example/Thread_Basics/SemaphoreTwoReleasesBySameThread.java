package org.example.Thread_Basics;

import java.util.concurrent.Semaphore;

public class SemaphoreTwoReleasesBySameThread {
        Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) {
        SemaphoreTwoReleasesBySameThread obj = new SemaphoreTwoReleasesBySameThread();
        Thread t1 = new Thread( ()-> {
            try {
                obj.criticalSection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread( ()-> {
            try {
                obj.criticalSection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void criticalSection() throws InterruptedException {
        System.out.println("Waiting for access, "+Thread.currentThread().getName());
        semaphore.acquire();
        System.out.println("Accesses by "+Thread.currentThread().getName());
        semaphore.release();
    }
}
