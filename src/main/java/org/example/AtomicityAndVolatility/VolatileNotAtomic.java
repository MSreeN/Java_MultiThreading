package org.example.AtomicityAndVolatility;

public class VolatileNotAtomic {
    volatile long counter;

    public void increment(){
        System.out.println(counter++ + " by "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        VolatileNotAtomic obj = new VolatileNotAtomic();

        Runnable task = ()->{
          for(int i = 0; i<50; i++){
              obj.increment();
          }
        };

        for(int i = 0; i < 100; i++){
            Thread t = new Thread(task, "t"+i);
            t.start();
        }

    }
}
