package org.example.Thread_Basics;

public class ThreadPriority {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                System.out.println("Running "+Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread t1 = new Thread(runnable);
        t1.setPriority(4);
        t1.setName("t1");
        Thread t2 = new Thread(runnable);
        t2.setPriority(9);
        t2.setName("t2");
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
        handlingExceptionInThread();
    }

    public static void handlingExceptionInThread(){
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Unexpected error");
        });
        t1.setUncaughtExceptionHandler((t, e) -> System.out.println(e.getMessage()));
        t1.setDaemon(true);
        t1.start();

//        return t1;
    }

}
