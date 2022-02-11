package com.example.practice.controller;

import com.example.practice.mapper.RoleMapper;
import com.example.practice.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gaopengtao
 * @version redis的请求
 */
@RestController
@RequestMapping("/practice")
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @Resource
    RoleMapper roleMapper;

    @GetMapping("/set")
    public boolean redisset(String key, String value) {
        return redisUtil.set(key, value);
    }

    @GetMapping("/get")
    public Object redisget(String key) {
        return redisUtil.get(key);
    }

    @GetMapping("/expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }

    @GetMapping("/setCity")
    public boolean setCity() {
        return redisUtil.set("setCity", roleMapper.selectCity());
    }

    @GetMapping("/getBydb")
    public List<Map> getBydb() {
        return roleMapper.selectCity();
    }

}
