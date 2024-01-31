package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.ProgrammingQuestion;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.ProgrammingQuestionRepository;
import com.roima.exammanagement.rest.v1.dto.ProgrammingQuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.ProgrammingQuestionMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgrammingQuestionService {
    private final ProgrammingQuestionRepository programmingQuestionRepository;
    private final ProgrammingQuestionMapper programmingQuestionMapper;
    private final ExamRepository examRepository;

    public ProgrammingQuestionDTO saveProgrammingQuestion(@NonNull  ProgrammingQuestionDTO programmingQuestionDTO){
        ProgrammingQuestion programmingQuestion = programmingQuestionMapper.toEntity(programmingQuestionDTO);
        if(programmingQuestion != null){
            programmingQuestionRepository.save(programmingQuestion);
        }else{
            throw new DataIntegrityViolationException("can not save the programming question");
        }
        return  programmingQuestionDTO;
    }

    public Boolean addProgrammingQuestionToExamById(@NonNull Long examId, @NonNull Long programmingQuestionId){
        try {
            Exam exam = examRepository.findById(examId).orElseThrow();
            ProgrammingQuestion programmingQuestion = programmingQuestionRepository.findById(programmingQuestionId).orElseThrow();
            exam.getProgrammingQuestions().add(programmingQuestion);
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean removeProgrammingQuestionFromExamById(@NonNull Long programmingQuestionId, @NonNull Long examId){
        try {
            Exam exam = examRepository.findById(examId).orElseThrow();
            exam.getProgrammingQuestions().remove(programmingQuestionRepository.findById(programmingQuestionId).orElseThrow());
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean deleteProgrammingQuestionById(@NonNull Long programmingQuestionId){
        try {
            programmingQuestionRepository.deleteById(programmingQuestionId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
