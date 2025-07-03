package org.example.AtomicityAndVolatility;

import java.util.ArrayList;
import java.util.List;

//This class is to check if volatility ensures atomicity
//the last number sometimes is 500 which should be when condition is < 500
public class CheckVolatility {

    volatile long counter = 0;

    public void increment(){
//        synchronized (this) {
        counter++;
            System.out.println(counter);
//        }
    }

    public static void main(String[] args) {
        CheckVolatility cv = new CheckVolatility();

        for (int i = 0; i < 500; i++) {
            new Thread(cv::increment).start();
        }
    }
}
