package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.UserQuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestionSubmissionRepository extends JpaRepository<UserQuestionSubmission,Long>, JpaSpecificationExecutor<UserQuestionSubmission> {


    @Query(value = "SELECT us FROM UserQuestionSubmission us " +
            "WHERE us.user.id = :userId " +
            "AND us.exam.id = :examId " +
            "AND us.question.id = :questionId")
    public UserQuestionSubmission findByUserAndExamAndQuestion(@Param("userId") Long userId,
                                                               @Param("examId") Long examId,
                                                               @Param("questionId")Long questionId);


    @Query(value = "SELECT us from UserQuestionSubmission us  " +
            "WHERE us.user.id = :userId " +
            "AND us.exam.id = :examId ")
    public List<UserQuestionSubmission> findByExamAndUser(@Param("userId")Long userId,
                                                          @Param("examId") Long examId);



    @Query(value = "SELECT SUM(us.gainedMarks) from UserQuestionSubmission us " +
            "WHERE us.user.id=:userId " +
            "AND us.exam.id = :examId " +
            "AND us.question.questionType.id = :questionTypeId ")
    public  Integer getTotalMarksByQuestionType(@Param("userId")Long userId, @Param("examId")Long examId,
                                                @Param("questionTypeId")Long questionTypeId);

    @Query(value = "SELECT SUM(us.gainedMarks) from UserQuestionSubmission us " +
            "WHERE us.user.id=:userId " +
            "AND us.exam.id = :examId ")
    public Integer getTotalMarks(@Param("userId")Long userId, @Param("examId")Long examId);
}
