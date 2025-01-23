package com.one2one.sms.controller;

import com.one2one.sms.entity.Subject;
import com.one2one.sms.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

     private SubjectService subjectService;


    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    
    
    
    @PostMapping("/addAllSubjects")
    public ResponseEntity<List<Subject>> addAllSubjects(@RequestBody List<String> subjects){
        List<Subject> allSubjects = subjectService.addAll(subjects);

        return new ResponseEntity<>(allSubjects, HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<Subject> addSubject(@RequestParam String subjectName){
        Subject subject = subjectService.addSubject(subjectName);

        return new ResponseEntity<>( subject, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects(){
        List<Subject> all = subjectService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSubject(@RequestParam Long subjectId){
        subjectService.deleteSubject(subjectId);


        return new ResponseEntity<>("Subject Deleted Sucessfully", HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Subject> updateSubject(@RequestParam String oldName, @RequestParam String updatedName){
       Subject subject = subjectService.updateSubject(oldName, updatedName);

       return  new ResponseEntity<>(subject,HttpStatus.OK);
    }

}
