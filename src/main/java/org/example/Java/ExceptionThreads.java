package org.example.Java;

public class ExceptionThreads {
    public static void main(String[] args) {
//        try {
//            new Thread(() -> {
//                throw new RuntimeException();
//            }).start();
//        }catch (RuntimeException ex){
//            System.out.println("runtime exception from thread");
//        }

        Thread t1 = new Thread(() -> {
            if(!Thread.interrupted()){
                System.out.println("Thread is not interrupted yet");
            }
            Thread.currentThread().interrupt();
            if(Thread.interrupted()){
                throw new RuntimeException();
            }
        });
        try{
        t1.start();}
        catch(Exception ex){
            System.out.println("Exception");
        }
        t1.interrupt();
    }
}
