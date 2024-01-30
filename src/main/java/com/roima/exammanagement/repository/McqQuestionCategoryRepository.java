package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.McqQuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface McqQuestionCategoryRepository extends JpaRepository<McqQuestionCategory,Long> {
}
