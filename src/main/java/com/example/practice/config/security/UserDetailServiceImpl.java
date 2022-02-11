package com.example.practice.config.security;

import com.example.practice.dao.RoleDao;
import com.example.practice.dao.UserDao;
import com.example.practice.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaopengtao
 * @version 用户继承UserDetailsService
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 查询用户，关联角色，然后返回账号信息，给自定义的 MyPasswordEncoder 校验密码
        UserDao user = userMapper.selectUserByName(username);
        if (null != user) {
            // 设置角色
            List<RoleDao> roleDaos = userMapper.selectRoleByUserId(user.getId());
            if (!CollectionUtils.isEmpty(roleDaos)) {
                for (RoleDao dao : roleDaos) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + dao.getRoleName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            // 返回数据库中的账号密码构建的用户类
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            return null;
        }
    }
}
