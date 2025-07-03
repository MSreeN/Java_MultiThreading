package org.example.Thread_Basics;

public class SynchronizationExample {
    public static void main(String[] args) {
//        InnerThreadClass itc = new InnerThreadClass();
//        Thread t1 = new Thread(itc);
//        Thread t2 = new Thread(itc);
//        t1.start();
//        t2.start();

        InterruptClass i1 = new InterruptClass();
        Thread iThread = new Thread(i1);
        iThread.start();
        iThread.interrupt();
    }

    static class InnerThreadClass implements  Runnable{
        public static int j = 0;
        @Override
        public void run() {
            synchronized (InnerThreadClass.class){
            for (j= 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName() + " " + j);
            }
            }
        }
    }


    static class InterruptClass implements Runnable{

        @Override
        public void run() {
            while(true){
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException ex){

                }
                System.out.println("hello");
            }
        }
    }
}
