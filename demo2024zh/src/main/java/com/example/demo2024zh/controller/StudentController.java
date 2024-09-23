package com.example.demo2024zh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo2024zh.dto.StudentDTO;
import com.example.demo2024zh.entity.Student;
import com.example.demo2024zh.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentService studentService;

    // 查询学生列表
    @GetMapping("/queryStudents")
    public IPage<Student> queryStudents(@RequestParam Long current, @RequestParam Long size) {
        return studentService.queryAllStudent(current, size);
    }

    // 根据条件查询学生列表（使用 POST 传递复杂的 DTO）
    @PostMapping("/querybycondition")
    public IPage<Student> queryByCondition(@RequestBody StudentDTO studentDTO) {
        return studentService.pageListByCondition(studentDTO);
    }

    // 插入学生信息（使用 POST 传递学生数据）
    @PostMapping("/insert")
    public void insert(@RequestBody Student student) {
        studentService.insert(student);
    }

    // 更新学生信息（使用 PUT）
//    @PutMapping("/update")
//    public void update(@RequestBody Student student) {
//        studentService.update(student);
//    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Student student) {
        try {
            studentService.update(student); // 调用 service 层的更新方法
            return ResponseEntity.ok("Update successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Integer id) {
        try {
            studentService.delete(id); // 调用 service 层的删除方法
            return ResponseEntity.ok("Delete successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed");
        }
    }

}
