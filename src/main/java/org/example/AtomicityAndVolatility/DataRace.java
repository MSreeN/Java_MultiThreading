package org.example.AtomicityAndVolatility;

public class DataRace {

    int x = 0;
    volatile int y = 0;

    public void increment(){
        x++;
        y++;
    }

    public void checkValues(){
        if(y > x){
            System.out.println("Data Breach detected");
        }
    }

    public static void main(String[] args) {
        DataRace dr = new DataRace();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i<Integer.MAX_VALUE; i++){
                dr.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i< Integer.MAX_VALUE; i++){
                dr.checkValues();
            }
        });

        t1.start();
        t2.start();
    }
}
