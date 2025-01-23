package com.one2one.sms.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("dOB1")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of Birth should be in yyyy-MM-dd format")
    private String dOB1;



    @Size(max = 100, message = "Address should not exceed 100 characters")
    private String address;

    @Size(min = 1, max = 50, message = "Class name should be between 1 and 50 characters")
    private String className;
}
