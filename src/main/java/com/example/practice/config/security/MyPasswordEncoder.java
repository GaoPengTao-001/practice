package com.example.practice.config.security;

import com.example.practice.util.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author gaopengtao
 * @version 校验密码
 */
public class MyPasswordEncoder implements PasswordEncoder {
    /**
     * 加密
     *
     * @param charSequence 需要加密的密码
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return MD5Util.encode((String) charSequence);
    }

    /**
     * 判断加密后的密码是否一致
     *
     * @param rawPassword     需要加密的密码
     * @param encodedPassword 数据库中加密后的密码，权限框架会直接传入
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.encode((String) rawPassword));
    }
}
