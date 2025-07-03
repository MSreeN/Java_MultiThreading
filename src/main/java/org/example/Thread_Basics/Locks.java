package org.example.Thread_Basics;

public class Locks {
    public static void main(String[] args) throws InterruptedException{
        Calculation c = new Calculation();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Started t1");
                c.sleep();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Started t2");
                c.sleep();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

    }
}

class Calculation{

    public void sleep() throws InterruptedException {
        synchronized (this) {
            System.out.println("Current thread is " + Thread.currentThread().getName());
            Thread.sleep(5000);
        }
    }

    public static void sleepStatic() throws InterruptedException {
        synchronized (Calculation.class){
        System.out.println("Current thread is "+ Thread.currentThread().getName());
        Thread.sleep(5000);
        }
    }
}
