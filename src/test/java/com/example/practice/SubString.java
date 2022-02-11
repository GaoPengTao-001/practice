package com.example.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class SubString {

    @Test
    void contextLoads() {
        String aaa = "23:59:59";
        System.out.println(aaa.substring(0, 5));

        Set<String> test = new HashSet<>();
        try {
            test.add("111111");
            test.add("222222");
            test.add("333333");
            test.add("333333");
            System.out.println(test);
        } catch (Exception e) {
            System.out.println("重复");
        }

    }


}
