package org.example.Java;

public class MainClass {
    public static void main(String[] args) {
        SubClass s = new SubClass();
        s.display();
    }
}

class SuperClass{

    SuperClass(){
        System.out.println("Super class constructor");
    }

    public static void sample(){
        System.out.println("Super class");
    }
}

interface SuperInter{
    int a = 10;
}

class SubClass extends  SuperClass{

    public SubClass(){
    }

    SubClass(String name){

    }

    public static void sample(){
        System.out.println("Sub class");
    }

    public void display(){
        System.out.println(SuperInter.a);
        System.out.println("Display");
    }
}
