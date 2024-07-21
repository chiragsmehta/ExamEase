package com.roima.exammanagement.rest.v1.service;

import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.QuestionTypeRepository;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import com.roima.exammanagement.rest.v1.mapper.QuestionTypeMapper;
import com.roima.exammanagement.rest.v1.mapper.UserMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionTypeService {
    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionTypeMapper questionTypeMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public QuestionTypeDTO save(QuestionTypeDTO questionTypeDTO){
        QuestionType questionType = questionTypeMapper.toEntity(questionTypeDTO);
        questionType.setCreatedAt(LocalDateTime.now());
        questionType.setUpdatedAt(LocalDateTime.now());
        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        questionType.setCreatedBy(admin);
        questionType.setUpdatedBy(admin);
//        System.out.println("created by: " + questionType.getCreatedBy());
        return questionTypeMapper.toDTO(questionTypeRepository.save(questionType));
    }
    public List<QuestionTypeDTO> find( @SearchSpec Specification<QuestionType> spec) {
        return questionTypeRepository.findAll(spec).stream().map(questionTypeMapper::toDTO).collect(Collectors.toList());
    }
    public List<QuestionTypeDTO> findAll(){
        return questionTypeRepository.findAll().stream().map(questionTypeMapper::toDTO).collect(Collectors.toList());
    }

    public QuestionTypeDTO findById(Long id){
        return questionTypeMapper.toDTO(questionTypeRepository.findById(id).orElse(null));
    }

    public QuestionTypeDTO update(QuestionTypeDTO questionTypeDTO){
        QuestionType questionType = questionTypeRepository.findById(questionTypeDTO.getId()).orElse(null);
        if(questionType == null){
            throw new NoSuchElementException("Question Type does not exist");
        }
        questionTypeMapper.updateSourceFromTarget(questionTypeDTO,questionType);
        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        questionType.setUpdatedAt(LocalDateTime.now());
        questionType.setUpdatedBy(admin);
        questionTypeRepository.save(questionType);
        return questionTypeMapper.toDTO(questionType);
    }

    public Boolean delete(Long id){
            questionTypeRepository.deleteById(id);
            return true;
    }

}