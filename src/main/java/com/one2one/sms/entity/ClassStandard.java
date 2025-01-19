package com.one2one.sms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class_standard")
public class ClassStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long standardId;

    @Column(name = "class_name", nullable = false, unique = true, length = 20)
    private String className;

    @OneToMany(mappedBy = "standard" , cascade = CascadeType.ALL)
    private Set<Student> students;

    @ManyToMany
    private Set <Subject> subjects;

}