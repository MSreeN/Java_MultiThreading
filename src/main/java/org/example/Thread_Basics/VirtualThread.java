package org.example.Thread_Basics;

import java.util.ArrayList;
import java.util.List;

public class VirtualThread {
  static final int THREAD_COUNT = 10;
  public static void main(String[] args) throws InterruptedException {
    
    List<Thread> threads = new ArrayList<>();
    for(int i = 0; i< THREAD_COUNT; i++){
       threads.add(Thread.ofVirtual().name("Virtual Thread "+(i+1)).start(new Runner()));
    }

    for(Thread t: threads){
      t.join();
    }
  }
  
}


class Runner implements Runnable{

  @Override
  public void run() {
    System.out.println("Before " + Thread.currentThread());
    try{
      Thread.sleep(500);
    }catch(InterruptedException e){

    }
    System.out.println("After "+ Thread.currentThread());
  }
} 
