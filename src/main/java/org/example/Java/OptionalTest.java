package org.example.Java;

import java.util.List;

public class OptionalTest {
    public static void main(String[] args) {
        List<String> list = List.of("A", "C");
        String res = list.stream().filter(ele -> ele.equals("B")).findFirst().orElse("hello");
        System.out.println(res);
    }
}
