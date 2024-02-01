package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.model.Option;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.McqQuestionRepository;
import com.roima.exammanagement.repository.OptionRepository;
import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.OptionDTO;
import com.roima.exammanagement.rest.v1.mapper.McqQuestionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class McqQuestionService {
    private final McqQuestionRepository mcqQuestionRepository;
    private final ExamRepository examRepository;
    private final McqQuestionMapper mcqQuestionMapper;
    private final OptionRepository optionRepository;

    public List<McqQuestionDTO> findAll(){
        mcqQuestionRepository.findAll().forEach((item -> {
            log.debug(String.valueOf(item.getExam().size()));
        }));
        return mcqQuestionRepository.findAll().stream().map(mcqQuestionMapper::toDTO).toList();
    }

    public McqQuestionDTO save(@NonNull McqQuestionDTO mcqQuestionDTO){
        McqQuestion mcqQuestion = mcqQuestionMapper.toEntity(mcqQuestionDTO);
        mcqQuestion.getOptions().forEach((option -> option.setMcqQuestion(mcqQuestion)));
        mcqQuestionRepository.save(mcqQuestion);

        return mcqQuestionMapper.toDTO(mcqQuestion);
    }

    public McqQuestionDTO findById(@NonNull Long id){
        McqQuestion mcqQuestion = mcqQuestionRepository.findById(id).orElseThrow();
        return mcqQuestionMapper.toDTO(mcqQuestion);
    }

    public Boolean assignMcqQuestionToExamById(@NonNull Long mcqQuestionId, @NonNull Long examId){
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
