package com.example.demo2024zh.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.demo2024zh.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@Repository
@Mapper
public interface StudentDAO extends BaseMapper<Student> {
}
