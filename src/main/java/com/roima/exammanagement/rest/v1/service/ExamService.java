package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.*;
import com.roima.exammanagement.repository.*;
import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.ExamMapper;
import com.roima.exammanagement.rest.v1.mapper.QuestionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final UserRepository userRepository;
    private final ExamEnrollementRepository examEnrollementRepository;
    private final QuestionRepository questionRepository;
    private final UserExamStatusRepository userExamStatusRepository;
    private final QuestionMapper questionMapper;

    public ExamDTO findExamById(Long id) throws ChangeSetPersister.NotFoundException {
        Exam exam = examRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return examMapper.toDTO(exam);
    }

    public Boolean assignExamToUserById(@NonNull Long userId, @NonNull Long examId){
        try{
            Exam exam = examRepository.findById(examId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
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

    public List<QuestionDTO> findQuestionByExamId(@NonNull Long examId){
        List<Question> questions = questionRepository.findMcqByExams(examId);
        return questions.stream().map(questionMapper::toDTO).collect(Collectors.toList());
    }
}
