package com.one2one.sms.service;

import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.entity.Student;
import com.one2one.sms.exception.ResourceNotFound;
import com.one2one.sms.payload.StudentDto;
import com.one2one.sms.repository.ClassStandardRepository;
import com.one2one.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private ClassStandardRepository classStandardRepository;

    public StudentService(StudentRepository studentRepository, ClassStandardRepository classStandardRepository) {
        this.studentRepository = studentRepository;
        this.classStandardRepository = classStandardRepository;
    }


    public Student addStudent(StudentDto studentDto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String studentId = Arrays.stream( UUID.randomUUID().toString().split("-")).map(e -> e).collect(Collectors.joining()).substring(0, 7)+ "-" + studentDto.getStName();
        Date date = formatter.parse(studentDto.getDOB1());
        System.out.println("Date: " + date);

        Optional<Student> studentByEmailAndDOB = studentRepository.getStudentByEmailAndDateOfBirth(studentDto.getEmail(), date);

        if(studentByEmailAndDOB.isPresent()){
          throw new RuntimeException("Student already available with given details");
        }

        ClassStandard classStandard = classStandardRepository.findByClassName(studentDto.getClassName()).orElseThrow(
                () -> new ResourceNotFound("Please Enter valid class name")
        );



        Student student = new Student();
        student.setStudentId(studentId);
        student.setAddress(studentDto.getAddress());
        student.setDateOfBirth(date);
        student.setEmail(studentDto.getEmail());
        student.setMobile(studentDto.getMobile());
        student.setStandard(classStandard);
        student.setStName(studentDto.getStName());
        Student save = studentRepository.save(student);



        return save;

    }
}
