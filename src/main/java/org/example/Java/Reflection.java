package org.example.Java;

import java.lang.reflect.Field;

public class Reflection {

    static {
        System.out.println("this is static block");
    }

    public static void main(String[] args) throws IllegalAccessException {
        SecReflection obj = new SecReflection();


        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field: fields){
            field.setAccessible(true);
            if(field.getName().equals("a")){
                field.set(obj, 5);
            }
        }
        for(Field field: fields){
            Object fVal = field.get(obj);
            System.out.println(fVal);
        }
    }
}
