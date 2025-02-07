package com.one2one.sms.service;

import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.entity.Subject;
import com.one2one.sms.exception.ResourceNotFound;
import com.one2one.sms.repository.ClassStandardRepository;
import com.one2one.sms.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private ClassStandardRepository classStandardRepository;

    public SubjectService(SubjectRepository subjectRepository, ClassStandardRepository classStandardRepository) {
        this.subjectRepository = subjectRepository;
        this.classStandardRepository = classStandardRepository;
    }

    public Subject addSubject(String subjectName) {
        String string = UUID.randomUUID().toString();
        String collect = Arrays.stream(string.split("-")).map(e -> e).collect(Collectors.joining());
        String sub = collect.substring(0, 7);
        String subjectId = sub + "-" + subjectName;


        Optional<Subject> bySubjectName = subjectRepository.findBySubjectName(subjectName);
        if(bySubjectName.isPresent()){
            return bySubjectName.get();
        }

        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setSubjectName(subjectName);

        Subject save = subjectRepository.save(subject);


        return save;

    }

    public List<Subject> addAll(List<String> subjects){

        for (String subject : subjects) {
            addSubject(subject);
        }

        return getAll();
    }

    public List<Subject> getAll() {

        List<Subject> all = subjectRepository.findAll();

        return all;

    }

    public void deleteSubject(Long subjectId) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(
                () -> new ResourceNotFound("Subject not found with given id")
        );
        Set<ClassStandard> classes = subject.getClasses();

        for( ClassStandard c: classes){
            c.getSubjects().remove(subject);
            classStandardRepository.save(c);

        }

        subjectRepository.deleteById(subjectId);


    }

    public Subject updateSubject(String oldName, String updatedName) {

        Subject subject = subjectRepository.findBySubjectName(oldName).orElseThrow(
                () -> new ResourceNotFound("given subject is not available in database")
        );
        subject.setSubjectName(updatedName);
        Subject save = subjectRepository.save(subject);
        return save;
    }
}
