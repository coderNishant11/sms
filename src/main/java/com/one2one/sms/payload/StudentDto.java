package com.one2one.sms.payload;

import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.entity.Marksheet;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class StudentDto {

    private String stName;
    private String mobile;
    private String email;
    private Date dOB;


    private String address;


    private ClassStandard standard;


    private List<Marksheet> markSheets;
}
