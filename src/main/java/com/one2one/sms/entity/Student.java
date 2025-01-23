package com.one2one.sms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    private String studentId;

    @Column(name = "st_name", length = 100, nullable=false)
    private String stName;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "d_o_b", nullable = false)
    private Date dOB;

    @Column(name="address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name="standard_id",nullable = false)
    private ClassStandard standard;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade=CascadeType.ALL,orphanRemoval = true)
    private List<MarkSheet> markSheets;



}