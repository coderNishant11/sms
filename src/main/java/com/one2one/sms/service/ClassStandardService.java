package com.one2one.sms.service;


import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.entity.Subject;
import com.one2one.sms.exception.ResourceNotFound;
import com.one2one.sms.payload.ClassDto;
import com.one2one.sms.repository.ClassStandardRepository;
import com.one2one.sms.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClassStandardService {

    private ClassStandardRepository classStandardRepository;

    private SubjectRepository subjectRepository;
    public ClassStandardService(ClassStandardRepository classStandardRepository, SubjectRepository subjectRepository) {
        this.classStandardRepository = classStandardRepository;
        this.subjectRepository = subjectRepository;
    }

    public ClassStandard addClassStand(ClassDto classDto) {

        Set<String> subjects = classDto.getSubjects();

        Set<Subject> collect = subjects.stream().map(e -> subjectRepository.findBySubjectName(e).orElseThrow(
                () -> new ResourceNotFound("this subject " + e + "not found in database")
        )).collect(Collectors.toSet());

        ClassStandard classStandard = new ClassStandard();
        classStandard.setClassName(classDto.getClassName());
        classStandard.setSubjects(collect);

        return classStandardRepository.save(classStandard);
    }

    public List<ClassStandard> getAllClass() {



        return classStandardRepository.findAll();
    }

    public ClassStandard updateClass(ClassDto classDto, Long classId) {

        ClassStandard classStandard = classStandardRepository.findById(classId).orElseThrow(
                () -> new ResourceNotFound("class is not available with this id")
        );

        classStandard.setClassName(classDto.getClassName());
        Set<String> subjects = classDto.getSubjects();
        Set<Subject> collect = subjects.stream().map(
                e -> subjectRepository.findBySubjectName(e).orElseThrow(
                        () -> new ResourceNotFound("This subject" + e + "is not available in database")
                )
        ).collect(Collectors.toSet());

        classStandard.setSubjects(collect);
         return classStandardRepository.save(classStandard);


    }


    public void deletaClass(Long classId) {

        classStandardRepository.findById(classId).orElseThrow(
                ()->new ResourceNotFound("Class not available with this given id")
        );

        classStandardRepository.deleteById(classId);
    }
}