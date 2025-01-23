package com.one2one.sms.controller;


import com.one2one.sms.repository.MarkSheetService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkSheetController {

    private MarkSheetService markSheetService;

    public MarkSheetController(MarkSheetService markSheetService) {
        this.markSheetService = markSheetService;
    }
}
