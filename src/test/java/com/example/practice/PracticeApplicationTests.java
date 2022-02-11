package com.example.practice;

import com.example.practice.dao.RoleDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
class PracticeApplicationTests {

    @Test
    void contextLoads() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        List<Integer> ints = Arrays.asList(1, 2, 3, 4);

        // 筛选出不是空的
        filtered(strings);

        // 生成随机数
        printRandom(10);

        // 计算每一项并且得到结果
        squares(ints);

        // 排序
        sort(10);

        // :: 使用
        doSomeThing();

    }

    /**
     * filter筛选功能
     *
     * @param strings
     */
    private void filtered(List<String> strings) {
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    /**
     * foreach迭代数据
     * limit 获取指定数量的流
     *
     * @param i
     */
    private void printRandom(int i) {
        Random random = new Random();
        random.ints().limit(i).forEach(System.out::println);
    }

    /**
     * map 处理每一项
     *
     * @param ints
     */
    private void squares(List<Integer> ints) {
        List<Integer> rstInts = ints.stream().map(i -> i * i).collect(Collectors.toList());
        rstInts.stream().forEach(System.out::println);
    }

    /**
     * sorted 排序
     *
     * @param i
     */
    private void sort(int i) {
        Random random = new Random();
        random.ints().limit(i).sorted().forEach(System.out::println);
    }

    /**
     * :: 访问类的构造方法，对象方法，静态方法
     */
    private void doSomeThing() {
        List<RoleDao> roleDaos = new ArrayList<>();
        RoleDao dao = new RoleDao();
        dao.setId("1");
        dao.setRoleName("admin");
        roleDaos.add(dao);
        List<String> ss = roleDaos.stream().map(RoleDao::getRoleName).distinct().collect(Collectors.toList());
        ss.stream().forEach(System.out::println);
    }
}
