package org.example.Locks;

public class SequenceOfNumbers {
  public static void main(String[] args) {
    PrintMessage pm = new PrintMessage(3, 10);
    
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
    },"Thread 3");
    t1.start();
    t2.start();
    t3.start();

  }
}


class PrintMessage{

  

  public PrintMessage(int noOfThreads, int endNumber) {
    this.noOfThreads = noOfThreads;
    this.endNumber = endNumber;
  }
  int noOfThreads;
  int endNumber;
  int currentNumber = 1;
  public synchronized void printMessage(int remainder) throws InterruptedException{

    while(currentNumber < endNumber - 1){
      while(currentNumber % noOfThreads != remainder) wait();
      System.out.println(Thread.currentThread().getName() +" " +currentNumber);
      currentNumber++;
      notifyAll();
    }
  }
}
