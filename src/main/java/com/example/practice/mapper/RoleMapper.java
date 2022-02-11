package com.example.practice.mapper;

import com.example.practice.config.datasource.Ds2Dao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@Ds2Dao
public interface RoleMapper {
    Map selectTest();
    
    List<Map> selectCity();
}
