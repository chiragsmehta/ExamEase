package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.*;
import com.roima.exammanagement.rest.v1.service.QuestionsAddingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Question> {

//    public List<Question> findQuestionByExam(Long examId);

    public List<Question> findByQuestionCategoryAndQuestionTypeAndDifficultyLevel(QuestionCategory questionCategory,
                                                                            QuestionType questionType,
                                                                                  DifficultyLevel difficultyLevel);


    @Query(value = "SELECT q from Question q " +
            "WHERE :exam NOT MEMBER OF q.exams " +
            "AND q.questionType = :questionType " +
            "AND q.questionCategory = :questionCategory " +
            "AND q.difficultyLevel = :difficultyLevel " +
            "AND q.isActive = true " +
            "ORDER BY FUNCTION('RAND')")
    public List<Question> findByQuestionAddingRequest(@Param("questionCategory") QuestionCategory questionCategory,
                                                      @Param("questionType") QuestionType questionType,
                                                      @Param("difficultyLevel") DifficultyLevel difficultyLevel,
                                                      @Param("exam") Exam exam);


    @Query(value = "SELECT q FROM Question q " +
            "JOIN q.exams e " +
            "WHERE e.id = :examId")
    public  List<Question> findByExamId(@Param("examId") Long examId);


    @Query(value = "SELECT q FROM Question q " +
            "JOIN q.exams e " +
            "WHERE e.id = :examId " +
            "AND q.questionType.id = :questionTypeId")
    public List<Question> findByExamAndQuestionType(@Param("examId") Long examId,
                                                    @Param("questionTypeId") Long questionTypeId);
}
