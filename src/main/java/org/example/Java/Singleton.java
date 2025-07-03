package org.example.Java;

public class Singleton {
    private static Singleton obj = null;
     Singleton(){
        System.out.println("Instance created");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Singleton s = Singleton.getSingletonObject();
            System.out.println(s);
        });

        Thread t2 = new Thread(() -> {
            Singleton s = Singleton.getSingletonObject();
            System.out.println(s);
        });

        t1.start();
        t2.start();
    }

    static{
        System.out.println("hello");
    }

    private static Singleton getSingletonObject(){
        if(obj == null){
            synchronized (Singleton.class) {
                obj = new Singleton();
            }
            return obj;
        };
        return obj;
    }
}



