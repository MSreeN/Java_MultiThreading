package org.example.Java.Executors;

public class ExecutorThread extends Thread{

    ExecutorThread(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println("---"+Thread.currentThread().getName());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
