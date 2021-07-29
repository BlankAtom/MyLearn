package com.hong.controller;

import com.hong.mapper.StudentMapper;
import com.hong.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/queryList")
    public List<Student> queryStudentList(){
        List<Student> students = studentMapper.queryStudentList();
        for (Student student : students){
            System.out.println(student);

        }
        return students;
    }
}
