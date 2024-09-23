package com.example.demo2024zh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo2024zh.dto.StudentDTO;

import com.example.demo2024zh.entity.Student;



public interface IStudentService extends IService<Student>{

    public IPage<Student> queryAllStudent(Long current, Long size);
    IPage<Student> pageListByCondition(StudentDTO condition);



    Integer insert(Student student);
    Integer update(Student student);

    Integer delete(Integer id);

}
