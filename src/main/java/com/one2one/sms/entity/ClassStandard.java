package com.one2one.sms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class_standard")
public class ClassStandard {
    @Id

    @Column(name = "id", nullable = false)
    private String standardId;

    @Column(name = "class_name", nullable = false, unique = true, length = 20)
    private String className;

    @OneToMany(mappedBy = "standard" , cascade = CascadeType.ALL)
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "class_subject",
            joinColumns = @JoinColumn(name = "standardId"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set <Subject> subjects;

    @OneToMany(mappedBy = "classStd" ,cascade = CascadeType.ALL)
    private List<Marksheet> markSheets;

}