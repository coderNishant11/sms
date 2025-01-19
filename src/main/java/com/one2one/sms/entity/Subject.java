package com.one2one.sms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "subject_name", nullable = false, unique = true, length = 100)
    private String subjectName;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    public Set<ClassStandard> classes;

}