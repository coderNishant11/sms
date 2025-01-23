package com.one2one.sms.repository;

import com.one2one.sms.entity.ClassStandard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassStandardRepository extends JpaRepository<ClassStandard, Long> {
    Optional<ClassStandard> findByClassName(String standard);
}