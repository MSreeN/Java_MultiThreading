package org.example;

public class Override {
    public static void main(String[] args) {
        B a = new B();
        a.override();

//        B.override();
    }
}

class A{
    public static void override(){
        System.out.println("this is from A");
    }
}

class B extends A{
    public static void override(){
        System.out.println("this is from B");
    }

    public void dislpay(){

    }

}
