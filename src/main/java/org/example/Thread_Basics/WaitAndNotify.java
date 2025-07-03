package org.example.Thread_Basics;

public class WaitAndNotify {
    public static void main(String[] args) throws InterruptedException {
        new WaitAndNotify().secondMain();
    }

    public void secondMain() throws InterruptedException {
        WaitAndNotify w = new WaitAndNotify();
        Thread t1 = new Thread(() -> {
            try {
                w.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(w::consume);
        Thread t3 = new Thread(w::consume);

        t1.start();
        t2.start();
        t3.start();
        t3.setName("t3");
        t1.join();
    }



    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("produce method");
            wait();
//            notify();
            System.out.println("after produce");
//            Thread.sleep(5000);
        }
    }

    public void consume(){
        synchronized(this){
            System.out.println("consume method");
            if(Thread.currentThread().getName().equals("t3")) {
                notify();
            }
            System.out.println("consumer after notify");
        }
    }
}
