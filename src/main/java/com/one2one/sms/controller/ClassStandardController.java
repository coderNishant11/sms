package com.one2one.sms.controller;

import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.payload.ClassDto;
import com.one2one.sms.service.ClassStandardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassStandardController {


    private ClassStandardService classStandardService;

    public ClassStandardController(ClassStandardService classStandardService) {
        this.classStandardService = classStandardService;
    }


    @PostMapping
    public ResponseEntity<ClassStandard> addClassStandard(@RequestBody ClassDto classDto){

       ClassStandard classStandard = classStandardService.addClassStand(classDto);

       return new ResponseEntity<>(classStandard, HttpStatus.CREATED);
    }

        @PostMapping("/addAll")
    public ResponseEntity<List<ClassStandard>> addAllStandard(@RequestBody List<ClassDto> classDtos){
       List<ClassStandard>allClasses=classStandardService.addAllClass(classDtos);

       return new ResponseEntity<>(allClasses, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ClassStandard>> getAllClasses(){
      List<ClassStandard>  allClass = classStandardService.getAllClass();

      return new ResponseEntity<>(allClass,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClassStandard> updateClass(@RequestBody ClassDto classDto,
                                                    @RequestParam String classId ){
       ClassStandard classStandard = classStandardService.updateClass(classDto, classId);

       return new ResponseEntity<>(classStandard, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteClass(@RequestParam String classId){
        classStandardService.deleteClass(classId);

        return new ResponseEntity<>("Class deleted successfully", HttpStatus.OK);
    }
}
