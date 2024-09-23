package com.example.demo2024zh.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo2024zh.dto.StudentDTO;
import com.example.demo2024zh.service.IStudentService;
import com.example.demo2024zh.dao.StudentDAO;
import com.example.demo2024zh.entity.Student;
import org.springframework.stereotype.Service;


@Service
public class StudentService extends ServiceImpl<StudentDAO, Student> implements IStudentService {

    @Override
    public IPage<Student> queryAllStudent(Long current, Long size) {
        Page<Student> page = new Page<>(current, size);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<Student> pageListByCondition(StudentDTO condition) {
        Page<Student> page = new Page<>(condition.getCurrent(), condition.getSize());
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (condition.getName() != null && !condition.getName().isEmpty()) {
            queryWrapper.eq("name", condition.getName());
        }
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Integer insert(Student student) {
        return this.baseMapper.insert(student);
    }

    @Override
    public Integer update(Student student) {
        return this.baseMapper.updateById(student);
    }

    @Override
    public Integer delete(Integer id) {
        return this.baseMapper.deleteById(id);
    }
}



