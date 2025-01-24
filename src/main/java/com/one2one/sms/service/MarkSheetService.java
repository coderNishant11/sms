package com.one2one.sms.service;


import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.entity.MarkSheet;
import com.one2one.sms.entity.Student;
import com.one2one.sms.entity.Subject;
import com.one2one.sms.exception.ResourceNotFound;
import com.one2one.sms.payload.MarkSheetDto;
import com.one2one.sms.repository.ClassStandardRepository;
import com.one2one.sms.repository.MarkSheetRepository;
import com.one2one.sms.repository.StudentRepository;
import com.one2one.sms.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MarkSheetService {
private MarkSheetRepository markSheetRepository;

private StudentRepository studentRepository;

private ClassStandardRepository classStandardRepository;

private SubjectRepository subjectRepository;

    public MarkSheetService(MarkSheetRepository markSheetRepository, StudentRepository studentRepository, ClassStandardRepository classStandardRepository, SubjectRepository subjectRepository) {
        this.markSheetRepository = markSheetRepository;
        this.studentRepository = studentRepository;
        this.classStandardRepository = classStandardRepository;
        this.subjectRepository = subjectRepository;
    }

    public MarkSheet addMarkSheet(MarkSheetDto markSheetDto) {
        String markSheetId = Arrays.stream( UUID.randomUUID().toString().split("-")).map(e -> e).collect(Collectors.joining()).substring(0, 7)+ "-" + markSheetDto.getSubjectName();


        Student student = studentRepository.findById(markSheetDto.getStudentId()).orElseThrow(
                () -> new ResourceNotFound("Student not available with given id")
        );

        ClassStandard standard = student.getStandard();

        Set<Subject> subjects = standard.getSubjects();

        for(Subject subject: subjects){

            if(Objects.equals(subject.getSubjectName(), markSheetDto.getSubjectName())){
                MarkSheet markSheet = new MarkSheet();
                markSheet.setMarkSheetId(markSheetId);
                markSheet.setStudent(student);
                markSheet.setClassStd(standard);
                markSheet.setSubject(subject);
                markSheet.setMarksObtained(markSheetDto.getMarksObtained());
                markSheet.setTotalMarks(markSheetDto.getTotalMarks());
                markSheet.setGrade(markSheetDto.getGrade());
                markSheet.setRemarks(markSheetDto.getRemarks());
                markSheet.setDate(markSheetDto.getDate());

               return markSheetRepository.save(markSheet);

            }
        }

        throw new ResourceNotFound("Subject is not related to class standard");


    }

    public List<MarkSheet> getALlByStudent(String studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFound("Enter valid Student id")
        );

        List<MarkSheet> byStudent = markSheetRepository.findByStudent(student);

        if(byStudent.isEmpty()){
            throw new ResourceNotFound("MarkSheets are not available for given student");

        }

        return byStudent;
    }

    public List<MarkSheet> getALlByStudentAndClass(String studentId, String classId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFound("Enter valid Student id")
        );
        ClassStandard classStandard = classStandardRepository.findById(classId).orElseThrow(
                () -> new ResourceNotFound("Enter valid class id")
        );

        List<MarkSheet> allByStudentIdAndClassId = markSheetRepository.findAllByStudentIdAndClassId(studentId, classId);
        if(allByStudentIdAndClassId.isEmpty()){
            throw new ResourceNotFound("markSheet not available for given student and class");
        }

        return allByStudentIdAndClassId;

    }

    public MarkSheet updateMarkSheet(String markSheetId, double updatedMarks) {

        MarkSheet markSheet = markSheetRepository.findById(markSheetId).orElseThrow(
                () -> new ResourceNotFound("MarkSheet not available with given id")
        );

        markSheet.setMarksObtained(updatedMarks);
      return  markSheetRepository.save(markSheet);


    }

    public void deleteMarkSheet(String markSheetId) {

        markSheetRepository.deleteById(markSheetId);

    }
}
