package com.example.practice.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

@SpringBootTest
public class HuToolsTest {
    @Test
    void contextLoads() throws Exception {
        InputStream in = FileUtil.getInputStream("C:\\Users\\gaopengtao\\Desktop\\买X份A产品，A产品打折.txt");

        BufferedReader reader = IoUtil.getReader(in, Charset.defaultCharset());

        System.out.println(reader.readLine());

    }
}
