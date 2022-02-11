package com.example.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.regex.Pattern;

@SpringBootTest
class Matcher {

    @Test
    void contextLoads() {
        String content = "24:59";

        String pattern = "([01][0-9]|[2][0-3]):[012345][0-9]";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("能否校验到：" + isMatch);

    }
}
