package com.one2one.sms.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @Size(min = 3, max = 25, message = "Name should be between 3 and 25 characters")
    private String stName;

    @Pattern(regexp = "\\d{10}", message = "Mobile number should be exactly 10 digits")
    private String mobile;

    @Email(message = "Please provide a valid email address")
    private String email;

    @Past(message = "Date of birth must be in the past")
    private Date dOB;

    @Size(max = 100, message = "Address should not exceed 100 characters")
    private String address;

    @Size(min = 1, max = 50, message = "Class name should be between 1 and 50 characters")
    private String className;
}
