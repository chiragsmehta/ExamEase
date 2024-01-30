package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.ExamEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamEnrollementRepository extends JpaRepository<ExamEnrollment,Long> {
}
