package com.example.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gaopengtao
 * @version 测试接口
 */
@RestController
@RequestMapping("/practice")
public class TestController {
    @GetMapping("/test")
    public String test(String name) {
        return name + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
