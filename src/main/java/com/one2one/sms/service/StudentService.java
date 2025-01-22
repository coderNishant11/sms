package com.one2one.sms.service;

import com.one2one.sms.entity.Student;
import com.one2one.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student){
        String studentId = Arrays.stream( UUID.randomUUID().toString().split("-")).map(e -> e).collect(Collectors.joining()).substring(0, 7)+ "-" + student.getStudentName();

    }
}
