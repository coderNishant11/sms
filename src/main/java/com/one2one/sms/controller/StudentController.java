package com.one2one.sms.controller;

import com.one2one.sms.entity.Student;
import com.one2one.sms.payload.ErrorDto;
import com.one2one.sms.payload.StudentDto;
import com.one2one.sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

 private StudentService studentService;

  public StudentController(StudentService studentService){
      this.studentService = studentService;
 }

 public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto,
                                              BindingResult result){

      if(result.hasErrors()){

          return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.CONFLICT);
      }
     Student student =studentService.addStudent(studentDto);

      return new ResponseEntity<>(student ,HttpStatus.CREATED);

 }
}
