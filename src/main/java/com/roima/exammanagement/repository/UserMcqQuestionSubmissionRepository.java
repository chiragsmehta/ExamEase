package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.UserMcqQuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMcqQuestionSubmissionRepository extends JpaRepository<UserMcqQuestionSubmission,Long> {
}
