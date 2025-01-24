package com.one2one.sms.controller;


import com.one2one.sms.entity.MarkSheet;
import com.one2one.sms.payload.MarkSheetDto;
import com.one2one.sms.service.MarkSheetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/markSheet")
public class MarkSheetController {

    private MarkSheetService markSheetService;

    public MarkSheetController(MarkSheetService markSheetService) {
        this.markSheetService = markSheetService;
    }



    @PostMapping
    public ResponseEntity<?> addMarkSheet(@Valid @RequestBody MarkSheetDto markSheetDto,
    BindingResult  result){


        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
       MarkSheet addedMarkSheet = markSheetService.addMarkSheet(markSheetDto);

       return new ResponseEntity<>(addedMarkSheet, HttpStatus.CREATED);
    }

    @GetMapping("/markSheetByStudent")
    public ResponseEntity<List<MarkSheet>> getAllMarkSheetByStudent(@RequestParam String studentId){

       List<MarkSheet> studentMarkSheet = markSheetService.getALlByStudent(studentId);
       return new ResponseEntity<>(studentMarkSheet,HttpStatus.OK);
    }



}
