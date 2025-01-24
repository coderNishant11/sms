package com.one2one.sms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_standard")
public class ClassStandard {
    @Id
    @Column(name = "id", nullable = false)
    private String standardId;

    @Column(name = "class_name", nullable = false, unique = true, length = 20)
    private String className;

    @Column(name="stream", nullable=false)
    private String stream;

    @JsonIgnore
    @OneToMany(mappedBy = "standard" , cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "class_subject",
            joinColumns = @JoinColumn(name = "standardId"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set <Subject> subjects= new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "classStd" ,cascade = CascadeType.ALL)
    private List<MarkSheet> markSheets;



}