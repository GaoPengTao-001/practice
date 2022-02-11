package com.example.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class Date1 {

    @Test
    void contextLoads() throws Exception {
        // 获取时间加一年或加一月或加一天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        // 设置起时间
        cal.setTime(date);
        // 增加天数
        cal.add(Calendar.DATE, 1);
        System.out.println(cal.getTime());
        String aa = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        System.out.println(aa);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        date = dateFormat.parse(aa);
        System.out.println(date);
    }


}
