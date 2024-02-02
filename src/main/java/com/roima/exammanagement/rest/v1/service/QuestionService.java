package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.QuestionRepository;
import com.roima.exammanagement.repository.OptionRepository;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.QuestionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final QuestionMapper questionMapper;
    private final OptionRepository optionRepository;

    public List<QuestionDTO> findAll(){
        return questionRepository.findAll().stream().map(questionMapper::toDTO).collect(Collectors.toList());
    }

    public QuestionDTO save(@NonNull QuestionDTO questionDTO){
        Question question = questionMapper.toEntity(questionDTO);
        question.getOptions().forEach((option -> option.setQuestion(question)));
        questionRepository.save(question);

        return questionMapper.toDTO(question);
    }

    public QuestionDTO findById(@NonNull Long id){
        Question question = questionRepository.findById(id).orElse(null);
        return questionMapper.toDTO(question);
    }

    public Boolean assignMcqQuestionToExamById(@NonNull Long mcqQuestionId, @NonNull Long examId){
        try {
            Question question = questionRepository.findById(mcqQuestionId).orElse(null);
            Exam exam = examRepository.findById(examId).orElse(null);
            List<Question> examQuestions = exam.getQuestions();
            examQuestions.add(question);
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean removeMcqQuestionFromExamById(@NonNull Long mcqQuestionId, @NonNull Long examId){
        try {
            Exam exam = examRepository.findById(examId).orElse(null);
            exam.getQuestions().remove(questionRepository.findById(mcqQuestionId).orElse(null));
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean deleteMcqQuestionById(@NonNull Long mcqQuestionId){
       try {
           questionRepository.deleteById(mcqQuestionId);
           return true;
       }catch (Exception e){
           return false;
       }
    }


}
