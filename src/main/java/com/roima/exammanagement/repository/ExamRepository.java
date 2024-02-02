package com.roima.exammanagement.repository;

import  com.roima.exammanagement.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>, JpaSpecificationExecutor<Exam> {
}
