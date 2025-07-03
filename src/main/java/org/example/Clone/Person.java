package org.example.Clone;

public class Person implements Cloneable{

    String name;
    String age;
    Address address;

    Person(String name, String age, Address address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address.toString() +
                '}';
    }

    public static void main(String[] args) {
        Address a1 = new Address("bng");
        Person p1 = new Person("sree", "2", a1);
        Person p2 = p1.clone();
        p1.address.setCity("bza");
        p1.age = "55";
        System.out.println(p1.toString());
    }

    @Override
    public Person clone() {
        try {
            Person clone = (Person) super.clone();
            clone.address = this.address.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
