package com.one2one.sms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "marksheet")
public class Marksheet {
    @Id

    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id", nullable= false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="class_id", nullable = false)
    private ClassStandard classStd;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;


    private Double marksObtained;

    private Double totalMarks;

    private String grade;

    private String remarks;
}
