package com.one2one.sms.repository;

import com.one2one.sms.entity.MarkSheet;
import com.one2one.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarkSheetRepository extends JpaRepository<MarkSheet, String> {


    @Query("Select M From MarkSheet M Join M.student S Join M.classStd C where S.studentId=:studentId And C.standardId=:classId")
    List<MarkSheet> findAllByStudentIdAndClassId(@Param("studentId")String studentId, @Param("classId")String classId);

    List<MarkSheet> findByStudent(Student student);
}