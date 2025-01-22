package com.one2one.sms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id

    @Column(name = "id", nullable = false)
    private String subjectId;

    @Column(name = "subject_name", nullable = false, unique = true, length = 100)
    private String subjectName;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private Set<ClassStandard> classes;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Marksheet> markSheets;
}