package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.ExamEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamEnrollementRepository extends JpaRepository<ExamEnrollment,Long> {
}
