package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.McqQuestionRepository;
import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.McqQuestionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class McqQuestionService {
    private final McqQuestionRepository mcqQuestionRepository;
    private final ExamRepository examRepository;
    private final McqQuestionMapper mcqQuestionMapper;


    public List<McqQuestionDTO> findAll(){
        return mcqQuestionRepository.findAll().stream().map(mcqQuestionMapper::toDTO).toList();
    }

    public McqQuestionDTO saveMcqQuestion(@NonNull McqQuestionDTO mcqQuestionDTO){
        McqQuestion mcqQuestion = mcqQuestionMapper.toEntity(mcqQuestionDTO);
        mcqQuestionRepository.save(mcqQuestion);
        return mcqQuestionDTO;
    }

    public Boolean addMcqQuestionToExamById(@NonNull Long mcqQuestionId, @NonNull Long examId){
        try {
            McqQuestion mcqQuestion = mcqQuestionRepository.findById(mcqQuestionId).orElseThrow();
            Exam exam = examRepository.findById(examId).orElseThrow();
            List<McqQuestion> examMcqQuestions = exam.getMcqQuestions();
            examMcqQuestions.add(mcqQuestion);
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean removeMcqQuestionFromExamById(@NonNull Long mcqQuestionId, @NonNull Long examId){
        try {
            Exam exam = examRepository.findById(examId).orElseThrow();
            exam.getMcqQuestions().remove(mcqQuestionRepository.findById(mcqQuestionId).orElseThrow());
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean deleteMcqQuestionById(@NonNull Long mcqQuestionId){
       try {
           mcqQuestionRepository.deleteById(mcqQuestionId);
           return true;
       }catch (Exception e){
           return false;
       }
    }


}
