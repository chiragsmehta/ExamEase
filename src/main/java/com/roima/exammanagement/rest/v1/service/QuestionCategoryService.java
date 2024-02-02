package com.roima.exammanagement.rest.v1.service;

import com.roima.exammanagement.model.QuestionCategory;
import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.repository.QuestionCategoryRepository;
import com.roima.exammanagement.rest.v1.dto.QuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import com.roima.exammanagement.rest.v1.mapper.QuestionCategoryMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionCategoryService {
    private final QuestionCategoryRepository questionCategoryRepository;
    private final QuestionCategoryMapper questionCategoryMapper;

    public QuestionCategoryDTO save(QuestionCategoryDTO questionCategoryDTO){
        QuestionCategory questionCategory = questionCategoryMapper.toEntity(questionCategoryDTO);
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
        questionCategoryRepository.save(questionCategory);
        return questionCategoryMapper.toDTO(questionCategory);
    }

    public Boolean delete(Long id){
        try {
            questionCategoryRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}