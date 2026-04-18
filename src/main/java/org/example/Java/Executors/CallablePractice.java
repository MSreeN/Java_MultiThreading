package org.example.Java.Executors;

import java.util.concurrent.Callable;

public class CallablePractice implements Callable<Integer> {
  

  @Override
  public Integer call() throws Exception{
    try{
      Thread.sleep(1000);
    }catch(InterruptedException e){}
    throw new Exception("Wanted exception");
  }
  
}
