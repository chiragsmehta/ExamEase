package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.ProgrammingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammingQuestionRepository extends JpaRepository<ProgrammingQuestion,Long> {
}
