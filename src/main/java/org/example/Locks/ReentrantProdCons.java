package org.example.Locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantProdCons {
  public static void main(String[] args) {
    Producer producer = new Producer();
    Consumer consumer = new Consumer();
    Thread producerThread = new Thread(() ->{
      while(true){
        try {
          producer.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread consumerThread = new Thread(() ->{
      while(true){
        try {
          consumer.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    producerThread.start();
    consumerThread.start();
  }

}

interface Utility {
  ReentrantLock lock = new ReentrantLock(true);
  Condition condition = lock.newCondition();
  List<Integer> list = new ArrayList<>();
}

class Producer implements Utility {

  public void produce() throws InterruptedException {
      try {
        lock.lock();
        while(!list.isEmpty()){
          condition.await();
        }
        while (list.size() < 5) {
          int val = new Random().nextInt(1, 100);
          list.add(val);
          System.out.println("Produced "+list.get(list.size()-1));
        }
      } finally {
        condition.signalAll();
        lock.unlock();
      }
  }
}

class Consumer implements Utility {

  public void consume() throws InterruptedException {
      try {
        lock.lock();
        while(list.size()<5) condition.await();
        while (!list.isEmpty()) {
          int ele =list.remove(list.size() - 1);
          System.out.println("Consumed "+ele);
        }
      } finally {
        condition.signalAll();
        lock.unlock();
      }
  }
}