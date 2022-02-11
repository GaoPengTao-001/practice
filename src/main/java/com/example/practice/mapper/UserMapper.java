package com.example.practice.mapper;

import com.example.practice.config.datasource.Ds1Dao;
import com.example.practice.dao.RoleDao;
import com.example.practice.dao.UserDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
@Ds1Dao
public interface UserMapper {
    UserDao selectUserByName(@Param("name") String name);

    List<RoleDao> selectRoleByUserId(@Param("userId") String userId);
}
