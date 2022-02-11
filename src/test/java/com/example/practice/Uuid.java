package com.example.practice;

import com.example.practice.dao.RoleDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class Uuid {

    @Test
    void contextLoads() {
        System.out.println(UUID.randomUUID().toString());

    }

    
}
