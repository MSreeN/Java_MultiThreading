package org.example.Java.CustomAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Person {

    @MaxAge(maxVal = 18)
    private int age;

    Person(int age){
        this.age = age;
    }

//    static{
//        System.out.println("this is static block");
//    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Person person = new Person(12);
        Class<Person> properties = Person.class;

        for(Field field: properties.getDeclaredFields()){
            if(field.getAnnotation(MaxAge.class) != null){
                int maxAge = field.getAnnotation(MaxAge.class).maxVal();
                if(person.age < maxAge) System.out.println("Below maxAge");
                else System.out.println("above maxAge");
            }else System.out.println("No Annotation");
        }

//        Class<?> p1 = Class.forName("org.example.Java.CustomAnnotation.Person");
//        System.out.println("Static block is initialized once");
//        System.out.println(p1.isInstance(new Person(5)));


        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> p2 = loader.loadClass("org.example.Java.Reflection");
        System.out.println("Second static must have been printed by now");
        System.out.println(p2.isInstance(new Person(4)));
        p2.getConstructor().newInstance();
    }
}
