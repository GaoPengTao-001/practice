package com.example.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.regex.Pattern;

@SpringBootTest
class IntegerEquels {

    private static final Integer a = 100;

    private static final Integer b = 100;

    private static final Integer c = 999;

    private static final Integer d = 999;

    @Test
    void contextLoads() {
        System.out.println(a == b);

        System.out.println(c == d);


        Pattern pattern = Pattern.compile("[0-9]*");
        System.out.println(pattern.matcher("000").matches());
    }


}
