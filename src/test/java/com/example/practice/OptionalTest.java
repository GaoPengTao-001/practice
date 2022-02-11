package com.example.practice;

import cn.hutool.core.convert.Convert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class OptionalTest {
    @Test
    void contextLoads() throws Exception {
        String aa = "aaaa";

        String bb = Optional.ofNullable(aa).filter(res -> "11".equals(res)).map(res -> "非空").orElse("空");

        System.out.println(bb);

        System.out.println(Convert.toDouble(123));

    }

}
