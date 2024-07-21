package com.roima.exammanagement.rest.v1.service;

import com.roima.exammanagement.model.QuestionCategory;
import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.QuestionCategoryRepository;
import com.roima.exammanagement.rest.v1.dto.QuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import com.roima.exammanagement.rest.v1.mapper.QuestionCategoryMapper;
import com.roima.exammanagement.rest.v1.mapper.UserMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionCategoryService {
    private final QuestionCategoryRepository questionCategoryRepository;
    private final QuestionCategoryMapper questionCategoryMapper;
    private final UserService userService;
    private  final UserMapper userMapper;

    public QuestionCategoryDTO save(QuestionCategoryDTO questionCategoryDTO){
        QuestionCategory questionCategory = questionCategoryMapper.toEntity(questionCategoryDTO);
        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        questionCategory.setCreatedAt(LocalDateTime.now());
        questionCategory.setCreatedBy(admin);
        questionCategory.setUpdatedAt(LocalDateTime.now());
        questionCategory.setUpdatedBy(admin);
        return questionCategoryMapper.toDTO(questionCategoryRepository.save(questionCategory));
    }

    public List<QuestionCategoryDTO> find(@SearchSpec Specification<QuestionCategory> specs){
        return questionCategoryRepository.findAll(specs).stream().map(questionCategoryMapper::toDTO).collect(Collectors.toList());
    }

    public QuestionCategoryDTO findById(Long id){
        return questionCategoryMapper.toDTO(questionCategoryRepository.findById(id).orElse(null));
    }

    public QuestionCategoryDTO update(QuestionCategoryDTO questionCategoryDTO){
        QuestionCategory questionCategory = questionCategoryRepository.findById(questionCategoryDTO.getId()).orElse(null);
        if(questionCategory == null){
            throw new NoSuchElementException("Question Type does not exist");
        }
        questionCategoryMapper.updateSourceFromTarget(questionCategoryDTO,questionCategory);
        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
//            System.out.println("ADMIN IS " + admin.getName());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        questionCategory.setUpdatedAt(LocalDateTime.now());
        questionCategory.setUpdatedBy(admin);
        questionCategoryRepository.save(questionCategory);
        return questionCategoryMapper.toDTO(questionCategory);
    }

    public Boolean delete(Long id){
            questionCategoryRepository.deleteById(id);
            return true;

    }
}