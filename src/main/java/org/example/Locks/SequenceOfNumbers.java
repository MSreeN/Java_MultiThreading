package org.example.Locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SequenceOfNumbers {
  public static void main(String[] args) {
    PrintMessage pm = new PrintMessage(3, 20);

    Thread t1 = new Thread(() -> {
      try {
        pm.printMessage(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread 1");
    Thread t2 = new Thread(() -> {
      try {
        pm.printMessage(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread 2");
    Thread t3 = new Thread(() -> {
      try {
        pm.printMessage(0);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "Thread 3");
    t1.start();
    t2.start();
    t3.start();

  }
}

class PrintMessage {

  Lock lock = new ReentrantLock(true);
  int noOfThreads;
  int endNumber;
  int currentNumber = 1;
  Condition[] conditions;

  public PrintMessage(int noOfThreads, int endNumber) {
    this.noOfThreads = noOfThreads;
    this.endNumber = endNumber;
    this.conditions = new Condition[noOfThreads];
    for (int i = 0; i < noOfThreads; i++) {
      conditions[i] = lock.newCondition();
    }
  }

  public void printMessage(int remainder) throws InterruptedException{
      while(currentNumber < endNumber -1){
        try{
        lock.lock();
        while(currentNumber % noOfThreads != remainder) conditions[remainder].await();
        System.out.println(Thread.currentThread().getName() +" " +currentNumber);
        int nextThread = ++currentNumber % noOfThreads;
        conditions[nextThread].signal();
      }finally{
        lock.unlock();
      }
    }
  }
}
