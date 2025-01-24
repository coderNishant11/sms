package com.one2one.sms.service;

import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.entity.MarkSheet;
import com.one2one.sms.entity.Student;
import com.one2one.sms.exception.ResourceNotFound;
import com.one2one.sms.payload.StudentDto;
import com.one2one.sms.repository.ClassStandardRepository;
import com.one2one.sms.repository.MarkSheetRepository;
import com.one2one.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private ClassStandardRepository classStandardRepository;

    private MarkSheetRepository markSheetRepository;

    public StudentService(StudentRepository studentRepository, ClassStandardRepository classStandardRepository, MarkSheetRepository markSheetRepository) {
        this.studentRepository = studentRepository;
        this.classStandardRepository = classStandardRepository;
        this.markSheetRepository = markSheetRepository;
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

    public List<Student> getAll() {
        List<Student> all = studentRepository.findAll();

        if(all.isEmpty()){
            throw new ResourceNotFound("Students are not available ");
        }
        return all;
    }

    public List<Student> getAllByClass(String  className) {

        ClassStandard classStandard = classStandardRepository.findByClassName(className).orElseThrow(
                () -> new ResourceNotFound("ClassName not found in database ")
        );

        List<Student> byStandard = studentRepository.findByStandard(classStandard);
        if(byStandard.isEmpty()){
            throw new ResourceNotFound("Student not available in this class");


        }
        return byStandard;
    }

    public Student updateStudent(StudentDto studentDto, String studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFound("Enter Valid student id")
        );
        student.setEmail(studentDto.getEmail());
        student.setMobile(studentDto.getMobile());
        return studentRepository.save(student);
    }

    public Student updateStudentClass(StudentDto studentDto, String studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFound("Enter Valid student id")
        );
       ClassStandard classStandard= student.getStandard();


        List<MarkSheet> allMarkSheet = markSheetRepository.findAllByStudentIdAndClassId(student.getStudentId(), classStandard.getStandardId());
        if(allMarkSheet.isEmpty()){
            throw new ResourceNotFound("Student did not appear in the exams");
        }

        for(MarkSheet markSheet: allMarkSheet){

          if((markSheet.getMarksObtained()/ markSheet.getTotalMarks())<0.33){
              throw new RuntimeException("Student did not qualify in one or more subject");
          }

        }
        if(Objects.equals(studentDto.getClassName(), updateStandard(classStandard.getClassName()))){
        ClassStandard updatedClassStandard = classStandardRepository.findByClassName(studentDto.getClassName()).orElseThrow(
                () -> new ResourceNotFound("Please Enter valid class name")
        );

        student.setStandard(updatedClassStandard);
       return studentRepository.save(student);

        }



        throw new RuntimeException("Student can only promoted by one class standard ");
    }

    public static String updateStandard(String standard) {

        String[] split = standard.split("-");
        String s = (Integer.parseInt(split[0]) + 1) + "-"+split[1];
        return s;
    }

    public void deleteStudent(String studentId) {
        studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFound("Student not available in database with given id")
        );
        studentRepository.deleteById(studentId);

    }
}
