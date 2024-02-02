package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.UserQuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionSubmissionRepository extends JpaRepository<UserQuestionSubmission,Long>, JpaSpecificationExecutor<UserQuestionSubmission> {
}
