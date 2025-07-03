package org.example.Java;

interface A{
    default void doSomething(){
        System.out.println("A");
    }
}

interface B{
    default void doSomething() {
        System.out.println("B");
    }
}

public class DiamondProblem implements A, B{

    //Diamond Problem: DiamondProblem is implementing A, B, both have default doSomething()
    // method, if DiamondProblem doesn't override that method with new body, this causes ambiguity
    // because if doSomething() method is called from DiamondProblem object then compiler wont
    // know which method to call, either from A or B. If you need to call specific method for
    // either A or B then refer below method, if no need to call any method then you can leave
    // the body empty.

    @Override
    public void doSomething() {
        A.super.doSomething();
    }

    public static void main(String[] args) {
        B b = new DiamondProblem();
        b.doSomething();
    }
}
