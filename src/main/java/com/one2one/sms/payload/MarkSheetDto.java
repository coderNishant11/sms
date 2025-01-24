package com.one2one.sms.payload;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkSheetDto {

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Class name is required")
    @Size(max = 50, message = "Class name cannot exceed 50 characters")
    private String className;

    @NotBlank(message = "Subject name is required")
    @Size(max = 100, message = "Subject name cannot exceed 100 characters")
    private String subjectName;


    @DecimalMin(value = "0.0", inclusive = true, message = "Marks obtained must be at least 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Marks obtained cannot exceed 100")
    private Double marksObtained;

    @NotNull(message = "Total marks is required")
    @DecimalMin(value = "1.0", inclusive = true, message = "Total marks must be at least 1")
    private Double totalMarks;

    @NotBlank(message = "Grade is required")
    @Pattern(regexp = "^[A-F]$", message = "Grade must be a single letter between A and F")
    private String grade;

    @Size(max = 255, message = "Remarks cannot exceed 255 characters")
    private String remarks;

    @NotBlank(message = "Date is required")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "Date must be in the format YYYY-MM-DD"
    )
    private String date;
}

