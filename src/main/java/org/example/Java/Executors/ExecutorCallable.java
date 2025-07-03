package org.example.Java.Executors;

import java.util.concurrent.Callable;

public class ExecutorCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return Thread.currentThread().getName();
    }
}
