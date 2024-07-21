package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.*;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.QuestionRepository;
import com.roima.exammanagement.repository.OptionRepository;
import com.roima.exammanagement.repository.QuestionTypeRepository;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.mapper.QuestionCategoryMapper;
import com.roima.exammanagement.rest.v1.mapper.QuestionMapper;
import com.roima.exammanagement.rest.v1.mapper.QuestionTypeMapper;
import com.roima.exammanagement.rest.v1.mapper.UserMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleOptionMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleQuestionCategoryMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleQuestionTypeMapper;
import com.roima.exammanagement.rest.v1.service.utilitymodels.ExamQuestionsByType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final QuestionMapper questionMapper;
    private final OptionRepository optionRepository;
    private final ConversionService conversionService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final QuestionCategoryMapper questionCategoryMapper;
    private final QuestionTypeMapper questionTypeMapper;
    private final QuestionTypeRepository questionTypeRepository;
    private final SimpleQuestionTypeMapper simpleQuestionTypeMapper;
    private final SimpleQuestionCategoryMapper simpleQuestionCategoryMapper;
    private final SimpleOptionMapper simpleOptionMapper;

    public List<QuestionDTO> findAll(){
        return questionRepository.findAll().stream().filter(Question::getIsActive).map(questionMapper::toDTO).collect(Collectors.toList());
    }

    public QuestionDTO save(@NonNull QuestionDTO questionDTO) throws BadAttributeValueExpException {
//        System.out.println(questionDTO);
        questionDTO.getOptions().removeIf(simpleOptionDTO -> simpleOptionDTO.getValue() == null || simpleOptionDTO.getValue().isEmpty());
//        System.out.println("SIZE" + questionDTO.getOptions());
        if(questionDTO.getOptions().size() == 1){
            throw new BadAttributeValueExpException("There should be at least two options");
        }
        Question question = questionMapper.toEntity(questionDTO);
        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        question.setIsActive(true);
        question.setCreatedAt(LocalDateTime.now());
        question.setCreatedBy(admin);

        question.setUpdatedAt(LocalDateTime.now());
        question.setUpdatedBy(admin);
        if(questionTypeRepository.findById(questionDTO.getQuestionType().getId()).orElse(null).getIsOptionRequired()){
            question.getOptions().forEach((option -> option.setQuestion(question)));
        }

        questionRepository.save(question);

        return questionMapper.toDTO(question);
    }

    public QuestionDTO findById(@NonNull Long id){
        Question question = questionRepository.findById(id).orElse(null);
        return questionMapper.toDTO(question);
    }

    public QuestionDTO update(QuestionDTO questionDTO) throws BadAttributeValueExpException {
        questionDTO.getOptions().removeIf(simpleOptionDTO -> simpleOptionDTO.getValue() == null || simpleOptionDTO.getValue().isEmpty());
        if(questionDTO.getOptions() != null && questionDTO.getOptions().size() == 1){
            throw new BadAttributeValueExpException("There should be at least two options");
        }
        Question question = questionRepository.findById(questionDTO.getId()).orElse(null);
        if(question == null){
            throw new NoSuchElementException("Question Type does not exist");
        }
        questionMapper.updateSourceFromTarget(questionDTO,question);
        question.getOptions().forEach(option -> option.setQuestion(question));

        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        question.setUpdatedAt(LocalDateTime.now());
        question.setUpdatedBy(admin);
        questionRepository.save(question);
        return questionDTO;
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
//            System.out.println(e.getMessage());
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

    public Boolean deleteQuestionById(@NonNull Long questionId){
       try {
           Question question = questionRepository.findById(questionId).orElseThrow(NoSuchElementException::new);
           if(question.getExams().isEmpty()) {
               questionRepository.deleteById(questionId);
           }else{
               throw  new UnsupportedOperationException("Can not delete because question is in some exams");
           }
           return true;
       }catch (Exception e){
           return false;
       }
    }

    public List<Question> findByQuestionAddingRequest(QuestionsAddingRequest questionsAddingRequest,Long examId){
        return questionRepository.
                findByQuestionAddingRequest
                        (questionCategoryMapper.toEntity(questionsAddingRequest.getQuestionCategory())
                                ,questionTypeMapper.toEntity(questionsAddingRequest.getQuestionType())
                                ,questionsAddingRequest.getDifficultyLevel(),examRepository.findById(examId).orElse(null)
                        );
    }

    public Boolean assignQuestionsToExam(QuestionsAddingRequest questionsAddingRequest,Long examId){
        List<Question> questions = findByQuestionAddingRequest(questionsAddingRequest,examId);
        if(questions.size() < questionsAddingRequest.getCount()){
            return false;
        }
        try{
//            System.out.println(examId);
            Exam exam = examRepository.findById(examId).orElseThrow(NoSuchElementException::new);
            Collections.shuffle(questions);
            exam.getQuestions().addAll(questions.subList(0, questionsAddingRequest.getCount())  );
            exam.setIsActive(exam.getCurrentMarks() == exam.getTotalMarks());
            examRepository.save(exam);
            return true;
        }catch (Exception e){
//            System.out.println(e.getMessage());
            return false;
        }
    }

    public  List<ExamQuestionsByType> findExamQuestionsGroupedByType(Long examId){
        Exam exam = examRepository.findById(examId).orElse(null);
        List<ExamQuestionsByType> examQuestionsByTypes = new ArrayList<>();
        List<QuestionType> questionTypes = questionTypeRepository.findAll();
        questionTypes.forEach(type ->{
            ExamQuestionsByType q = new ExamQuestionsByType();
            q.setQuestionType(simpleQuestionTypeMapper.toDTO(type));
            q.setQuestions(questionRepository.findByExamAndQuestionType(examId,type.getId()).stream().map(questionMapper::toDTO).collect(Collectors.toList()));
            examQuestionsByTypes.add(q);
        });
    return  examQuestionsByTypes;
    }

    public  List<ExamQuestionsByType> findExamQuestionsGroupedByTypeWithoutAnswers(Long examId){
        Exam exam = examRepository.findById(examId).orElse(null);
        List<ExamQuestionsByType> examQuestionsByTypes = new ArrayList<>();
        List<QuestionType> questionTypes = questionTypeRepository.findAll();
        questionTypes.forEach(type ->{
            ExamQuestionsByType q = new ExamQuestionsByType();
            q.setQuestionType(simpleQuestionTypeMapper.toDTO(type));
            q.setQuestions(questionRepository.findByExamAndQuestionType(examId,type.getId()).stream().map(questionMapper::toDTO).collect(Collectors.toList()));
            q.getQuestions().forEach(questionDTO -> {
                questionDTO.setAnswer("");
                questionDTO.setCorrectOptions(new ArrayList<>());
                questionDTO.getOptions().forEach(op -> {
                    op.setIsCorrect(false);
                });
            });
            examQuestionsByTypes.add(q);
        });
        return  examQuestionsByTypes;
    }


}
