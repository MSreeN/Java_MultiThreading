package org.example.Java.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JavaOptional {
    public static void main(String[] args) {
        List<List<String>> strings = new ArrayList<>();
        var value = Optional.ofNullable(strings).map(res -> res.get(1));
        System.out.println(value.get());
    }
}
