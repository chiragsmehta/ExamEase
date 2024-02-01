package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.*;
import com.roima.exammanagement.repository.*;
import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.ExamMapper;
import com.roima.exammanagement.rest.v1.mapper.McqQuestionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final UserRepository userRepository;
    private final ExamEnrollementRepository examEnrollementRepository;
    private final McqQuestionRepository mcqQuestionRepository;
    private final UserExamStatusRepository userExamStatusRepository;
    private final McqQuestionMapper mcqQuestionMapper;

    public ExamDTO findExamById(Long id) throws ChangeSetPersister.NotFoundException {
        Exam exam = examRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return examMapper.toDTO(exam);
    }

    public Boolean assignExamToUserById(@NonNull Long userId, @NonNull Long examId){
        try{
            Exam exam = examRepository.findById(examId).orElseThrow();
            User user = userRepository.findById(userId).orElseThrow();
            ExamEnrollment examEnrollment = new ExamEnrollment(user,exam,null);
            examEnrollementRepository.save(examEnrollment);
            UserExamStatus userExamStatus = UserExamStatus.builder()
                    .examEnrollment(examEnrollment)
                    .hasSubmitted(false)
                    .remainingDuration(exam.getDuration())
                    .build();
            userExamStatusRepository.save(userExamStatus);
            examEnrollment.setUserExamStatus(userExamStatus);
            examEnrollementRepository.save(examEnrollment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean save(@NonNull ExamDTO examDTO) {
        Exam exam = examMapper.toEntity(examDTO);
        try{
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<McqQuestionDTO> findMcqQuestionByExamId(@NonNull Long examId){
        List<McqQuestion> mcqQuestions = mcqQuestionRepository.findMcqByExamId(examId);
        return mcqQuestions.stream().map(mcqQuestionMapper::toDTO).toList();
    }
}
