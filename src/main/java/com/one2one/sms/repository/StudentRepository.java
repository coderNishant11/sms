package com.one2one.sms.repository;

import com.one2one.sms.entity.ClassStandard;
import com.one2one.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {


    Optional<Student> getStudentByEmailAndDateOfBirth(String email, Date dateOfBirth);

    List<Student> findByStandard(ClassStandard classStandard);


}