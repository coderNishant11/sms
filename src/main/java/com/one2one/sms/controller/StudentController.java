package com.one2one.sms.controller;

import com.one2one.sms.entity.Student;
import com.one2one.sms.payload.ErrorDto;
import com.one2one.sms.payload.StudentDto;
import com.one2one.sms.repository.StudentRepository;
import com.one2one.sms.service.StudentService;
import com.one2one.sms.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

 private StudentService studentService;


    public StudentController(StudentService studentService,
                             StudentRepository studentRepository){
      this.studentService = studentService;

    }



 @PostMapping
 public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto,
                                              BindingResult result) throws ParseException {

      if(result.hasErrors()){

          return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.CONFLICT);
      }
     Student student =studentService.addStudent(studentDto);

      return new ResponseEntity<>(student ,HttpStatus.CREATED);

 }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){

      List<Student> students =  studentService.getAll();

      return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @GetMapping("/byClass")
    public ResponseEntity<List<Student>> getStudentsByClass(@RequestParam String className){
      List<Student>students =studentService.getAllByClass(className);

      return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping("/updateStudentDetail")
    public ResponseEntity<?>  updateStudent(@Valid @RequestBody StudentDto studentDto,
                                            @RequestParam String studentId,
                                                  BindingResult result){
      if(result.hasErrors()){
          return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
      }

     Student updatedStudent = studentService.updateStudent(studentDto, studentId);
      return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }


    @PutMapping("/updateClass")
    public  ResponseEntity<?> updateStudentClass(@Valid @RequestBody StudentDto studentDto,
                                                 @RequestParam String studentId,
                                                 BindingResult result){
      if(result.hasErrors()){
          return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
      }
      Student updatedStudent =studentService.updateStudentClass(studentDto, studentId);

      return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam String studentId){
      studentService.deleteStudent(studentId);
      return new ResponseEntity<>("Student deleted with given id", HttpStatus.OK);
    }
}

